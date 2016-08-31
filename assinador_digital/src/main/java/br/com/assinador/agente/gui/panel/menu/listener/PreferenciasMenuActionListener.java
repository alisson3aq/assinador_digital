package br.com.assinador.agente.gui.panel.menu.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.com.assinador.agente.Contexto;
import br.com.assinador.agente.gui.panel.conf.PreferenciasDialog;

public class PreferenciasMenuActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent event) {
		try{
			PreferenciasDialog prefsDialog = new PreferenciasDialog(Contexto.getMainWindow());
			prefsDialog.setVisible(true);
		}catch (Exception e) {
			Contexto.getNotificador().notificarErro("Falha ao abrir preferências\n" + e.getMessage(), "Preferências");
		}
	}

}
