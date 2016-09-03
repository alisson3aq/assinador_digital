package br.com.assinador.agente.gui.panel.assinador.tarefa;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import br.com.assinador.agente.Constantes;
import br.com.assinador.agente.Contexto;
import br.com.assinador.agente.Logger;
import br.com.assinador.agente.gui.popup.ProgressDialog;
import br.com.assinador.agente.gui.popup.SelecaoDispositivoPrompter;
import br.com.assinador.agente.io.FileExtension;
import br.com.assinador.agente.io.FileHandler;
import br.com.assinador.agente.multithread.ExecucaoException;
import br.com.assinador.agente.multithread.Tarefa;
import br.com.assinador.agente.multithread.TipoTarefa;
import br.com.assinador.bc.AssinaturaDigitalService;
import br.com.assinador.bc.exception.AssinaturaInvalidaException;
import br.com.assinador.bc.exception.TokenException;


public class AssinadorDocumentoAsync extends Tarefa<Void>{

	private String senha; 
	private Logger logger = Contexto.getLogger();
	private ProgressDialog progressDialog;
	private Collection<File> arquivos;
	
	public AssinadorDocumentoAsync(String senha, Collection<File> arquivos, ProgressDialog progressDialog) {
		super(TipoTarefa.ASSINATURA_DOCUMENTO);
		this.senha = senha;
		this.arquivos = arquivos;
		this.progressDialog = progressDialog;
	}

	protected Void executar(Map<Object, Object> contextoTarefa) throws ExecucaoException{
		processar(senha);
		return null;
	}
	
	private void processar(String senha) throws ExecucaoException {
		try{
			Contexto.getMainWindow().setEnabled(false);
			
			FileHandler fh = new FileHandler();
			
			progressDialog.setLimites(0, arquivos.size());
			AssinaturaDigitalService assinador = new AssinaturaDigitalService(senha);
			
			String dispositivo = escolherDispositivo(assinador);
			if (dispositivo != null){
				assinador.setAliasSelecionado(dispositivo);
				for (File f: arquivos){
					assinar(fh, f, assinador);
					progressDialog.progredir(f.getName() + " ...", 1);
				}
			}
			
		}catch(AssinaturaInvalidaException | TokenException e){
			logger.log(e);
			throw new ExecucaoException(e.getMessage(), e, true);
		}catch (Exception e) {
			logger.log("Falha na assinatura", e);
			throw new ExecucaoException("Falha na assinatura do documento \n " +
					"Verifique se o dispositivo está conectado ou se o driver correto está instalado.", true); 
		}finally {
			Contexto.getMainWindow().setEnabled(true);
			progressDialog.dispose(750);
		}
	}
	
	private String escolherDispositivo(AssinaturaDigitalService assinador) {
		List<String> connectedDevices = assinador.getConnectedDevices();
		if (connectedDevices.isEmpty()){
			Contexto.getNotificador().notificarAlerta("Nenhum dispositivo encontrado", "Seleção de dispositivo");
			return null;
		}else{
			SelecaoDispositivoPrompter dispositivo = new SelecaoDispositivoPrompter(connectedDevices);
			dispositivo.open();
			return dispositivo.getDispositivoSelecionado();			
		}
	}

	private void assinar(FileHandler fh, File arquivo, AssinaturaDigitalService assinador) throws Exception{
		byte[] conteudoAssinado = assinador.assinar(fh.readFile(arquivo));
		assinador.verificarAssinatura(conteudoAssinado);
		
		File diretorioSelecionado = Contexto.getAtributo(Constantes.DIRETORIO_ASSINATURA_DESTINO_SELECIONADO);
		fh.writeToFile(diretorioSelecionado, arquivo.getName(), FileExtension.P7S, conteudoAssinado);
	}

}
