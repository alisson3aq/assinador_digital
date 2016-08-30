package br.com.assinador.agente.gui.panel.documento.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import br.com.assinador.agente.Constantes;
import br.com.assinador.agente.Contexto;
import br.com.assinador.agente.gui.panel.documento.tarefa.ExtratorDocumentoAsync;
import br.com.assinador.agente.gui.popup.ProgressDialog;
import br.com.assinador.agente.multithread.Tarefa;
import br.com.assinador.agente.notification.Notificador;

public class ExtratorDocumentoActionListener implements ActionListener{

	private Notificador notificador = Contexto.getNotificador();
	
	public ExtratorDocumentoActionListener() {
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		File destino = Contexto.getAtributo(Constantes.DIRETORIO_EXTRACAO_DESTINO_SELECIONADO);
		if (destino == null || !destino.exists()){
			notificador.notificarErro(Contexto.getMainWindow(), "O diretório de destino não existe", "Assinador Digital");
			return;
		}
		
		Tarefa<Void> at = new ExtratorDocumentoAsync(new ProgressDialog("Progresso extração"));
		
		Contexto.getExecutor().executarTarefa(at);
	}
}
