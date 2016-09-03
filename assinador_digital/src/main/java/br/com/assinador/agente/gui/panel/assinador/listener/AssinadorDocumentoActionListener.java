package br.com.assinador.agente.gui.panel.assinador.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import br.com.assinador.agente.Contexto;
import br.com.assinador.agente.gui.panel.assinador.AssinadorPanel;
import br.com.assinador.agente.gui.panel.assinador.tarefa.AssinadorDocumentoAsync;
import br.com.assinador.agente.gui.panel.assinador.tarefa.ExcluirArquivosOriginaisAsync;
import br.com.assinador.agente.gui.popup.ProgressDialog;
import br.com.assinador.agente.multithread.Executor;
import br.com.assinador.agente.multithread.Protocolo;
import br.com.assinador.agente.multithread.Tarefa;
import br.com.assinador.agente.notification.Notificador;
import br.com.assinador.agente.vo.AssinadorVO;
import br.com.mvp.Controller;

public class AssinadorDocumentoActionListener implements ActionListener{

	private Notificador notificador = Contexto.getNotificador();
	private Controller<AssinadorPanel, AssinadorVO> controller;
	
	public AssinadorDocumentoActionListener(Controller<AssinadorPanel, AssinadorVO> controller) {
		this.controller = controller;
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
		
		AssinadorVO assinadorVO = controller.getModel();
		File destino = assinadorVO.getDestino();
		if (destino == null || !destino.exists()){
			notificador.notificarErro(Contexto.getMainWindow(), "O diretório de destino não existe", "Assinador Digital");
			return;
		}
		
		Executor executor = Contexto.getExecutor();
		
		Tarefa<Void> at = new AssinadorDocumentoAsync(senha, assinadorVO.getDocumentos(), new ProgressDialog("Progresso Assinatura"));
		Protocolo<Void> protocoloAssinatura = executor.executarTarefa(at);
		
		if (assinadorVO.isRemoverDocumentos()){
			Tarefa<Void> deleteFiles = new ExcluirArquivosOriginaisAsync(protocoloAssinatura);
			executor.executarTarefa(deleteFiles);
		}
			
		
	}
}
