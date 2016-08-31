package br.com.assinador.agente.gui.panel.conf.listener;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import br.com.assinador.agente.config.Configuracao;
import br.com.assinador.agente.config.ConfiguracaoManager;
import br.com.assinador.agente.gui.LookAndFeelUtil;
import br.com.assinador.agente.gui.panel.conf.PreferenciasPanel;
import br.com.mvp.Controller;

public class PreferenciaButtonActionListener implements ActionListener{

	private Controller<PreferenciasPanel, Configuracao> controller;
	
	public PreferenciaButtonActionListener(Controller<PreferenciasPanel, Configuracao> controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		final String actionCommand = e.getActionCommand();
		
		switch (actionCommand) {
		case "Cancelar":
			dispose();
			break;
		case "Salvar":
			dispose();
		case "Aplicar":
			atualziarConfiguracoes();
			aplicarAlteracoes();
			break;
		}
	}

	private void atualziarConfiguracoes() {
		Configuracao conf = controller.getModel();
		ConfiguracaoManager.update(conf);
	}
	
	private void aplicarAlteracoes() {
		try {
			LookAndFeelUtil.setLookAndFeel();
			 for(Window window : JFrame.getWindows()) {
		        SwingUtilities.updateComponentTreeUI(window);
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void dispose(){
		controller.getView().dispose();
	}

}
