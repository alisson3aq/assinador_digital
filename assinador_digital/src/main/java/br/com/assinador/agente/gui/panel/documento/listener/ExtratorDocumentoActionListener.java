package br.com.assinador.agente.gui.panel.documento.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import br.com.assinador.agente.Contexto;
import br.com.assinador.agente.gui.panel.documento.ExtratorDocumentoPanel;
import br.com.assinador.agente.gui.panel.documento.tarefa.ExtratorDocumentoAsync;
import br.com.assinador.agente.gui.popup.ProgressDialog;
import br.com.assinador.agente.multithread.Tarefa;
import br.com.assinador.agente.notification.Notificador;
import br.com.assinador.agente.vo.AssinadorVO;
import br.com.mvp.Controller;

public class ExtratorDocumentoActionListener implements ActionListener{

	private Notificador notificador = Contexto.getNotificador();
	private Controller<ExtratorDocumentoPanel, AssinadorVO> controller;
	
	public ExtratorDocumentoActionListener(Controller<ExtratorDocumentoPanel, AssinadorVO> controller) {
		this.controller = controller;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		File destino = controller.getModel().getDestino();
		if (destino == null || !destino.exists()){
			notificador.notificarErro(Contexto.getMainWindow(), "O diretório de destino não existe", "Assinador Digital");
			return;
		}
		
		Tarefa<Void> at = new ExtratorDocumentoAsync(controller.getModel(), new ProgressDialog("Progresso extração"));
		
		Contexto.getExecutor().executarTarefa(at);
	}
}
