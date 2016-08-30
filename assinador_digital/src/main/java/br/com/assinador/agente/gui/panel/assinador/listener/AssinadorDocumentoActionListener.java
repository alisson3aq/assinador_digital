package br.com.assinador.agente.gui.panel.assinador.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import br.com.assinador.agente.Constantes;
import br.com.assinador.agente.Contexto;
import br.com.assinador.agente.gui.panel.assinador.AssinadorComponentsVO;
import br.com.assinador.agente.gui.panel.assinador.tarefa.AssinadorDocumentoAsync;
import br.com.assinador.agente.gui.panel.assinador.tarefa.ExcluirArquivosOriginaisAsync;
import br.com.assinador.agente.gui.popup.ProgressDialog;
import br.com.assinador.agente.multithread.Executor;
import br.com.assinador.agente.multithread.Protocolo;
import br.com.assinador.agente.multithread.Tarefa;
import br.com.assinador.agente.notification.Notificador;

public class AssinadorDocumentoActionListener implements ActionListener{

	private Notificador notificador = Contexto.getNotificador();
	private AssinadorComponentsVO componentes;
	
	public AssinadorDocumentoActionListener(AssinadorComponentsVO componentes) {
		this.componentes = componentes;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		/*String senha = null;
		boolean acaoCancelada = false;
		do{
			SenhaPromptPopup popup = new SenhaPromptPopup();
			popup.open();
			
			senha = popup.getSenhaDigitada();
			acaoCancelada = popup.isAcaoCanceladaO();
			
			popup.close();
			if (!acaoCancelada){
				if (senha == null)
					notificador.notificarErro(Contexto.getMainWindow(), "A senha não pode ser em branco", "Assinatura digital");
				else
					processar(senha);
			}
			
		}while(senha == null && !acaoCancelada);*/
		
		processar("");
	}
	
	private void processar(String senha){
		
		File destino = Contexto.getAtributo(Constantes.DIRETORIO_ASSINATURA_DESTINO_SELECIONADO);
		if (destino == null || !destino.exists()){
			notificador.notificarErro(Contexto.getMainWindow(), "O diretório de destino não existe", "Assinador Digital");
			return;
		}
		
		Executor executor = Contexto.getExecutor();
		
		Tarefa<Void> at = new AssinadorDocumentoAsync(senha, new ProgressDialog("Progresso Assinatura"));
		Protocolo<Void> protocoloAssinatura = executor.executarTarefa(at);
		
		boolean removerArquivosOriginais = componentes.getChckbxRemoverArquivosOriginais().isSelected();
		if (removerArquivosOriginais){
			Tarefa<Void> deleteFiles = new ExcluirArquivosOriginaisAsync(protocoloAssinatura);
			executor.executarTarefa(deleteFiles);
		}
			
		
	}
}
