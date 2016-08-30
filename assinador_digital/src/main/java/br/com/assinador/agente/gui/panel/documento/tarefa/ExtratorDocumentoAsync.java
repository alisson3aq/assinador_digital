package br.com.assinador.agente.gui.panel.documento.tarefa;

import java.io.File;
import java.util.Map;
import java.util.Set;

import br.com.assinador.agente.Constantes;
import br.com.assinador.agente.Contexto;
import br.com.assinador.agente.Logger;
import br.com.assinador.agente.gui.popup.ProgressDialog;
import br.com.assinador.agente.io.FileExtension;
import br.com.assinador.agente.io.FileHandler;
import br.com.assinador.agente.multithread.Tarefa;
import br.com.assinador.agente.multithread.TipoTarefa;
import br.com.assinador.agente.notification.Notificador;
import br.com.assinador.bc.AssinaturaDigitalService;

public class ExtratorDocumentoAsync extends Tarefa<Void>{

	private Logger logger = Contexto.getLogger();
	private Notificador notificador = Contexto.getNotificador();
	private ProgressDialog progressDialog;
	
	public ExtratorDocumentoAsync(ProgressDialog progressDialog) {
		super(TipoTarefa.ASSINATURA_DOCUMENTO);
		this.progressDialog = progressDialog;
	}

	protected Void executar(Map<Object, Object> contextoTarefa) {
		processar();
		return null;
	}
	
	private void processar() {
		try{
			Contexto.getMainWindow().setEnabled(false);
			
			FileHandler fh = new FileHandler();
			Set<File> arquivos = Contexto.getAtributo(Constantes.DOCUMENTOS_EXTRACAO_SELECIONADOS);
			
			progressDialog.setLimites(0, arquivos.size());
			for (File f: arquivos){
				extrair(fh, f);
				progressDialog.progredir(f.getName() + " ...", 1);
			}
			
		}catch(Exception e){
			logger.log(e);
			progressDialog.dispose();
			notificador.notificarErro(Contexto.getMainWindow(), "Falha na leitura do(s) documento(s) \n" + e.getMessage(), "Extrator de documentos assinados");
		}finally {
			Contexto.getMainWindow().setEnabled(true);
		}
	}
	
	private void extrair(FileHandler fh, File arquivo) throws Exception{
		
		byte[] conteudoOriginal = AssinaturaDigitalService.extrairConteudoDeP7s(fh.readFile(arquivo));

		String nomeArquivo = arquivo.getName().toLowerCase().replaceAll("\\." + FileExtension.P7S.getExt(), "");
		
		File diretorioSelecionado = Contexto.getAtributo(Constantes.DIRETORIO_EXTRACAO_DESTINO_SELECIONADO);
		fh.writeToFile(diretorioSelecionado, nomeArquivo, conteudoOriginal);
	}

}
