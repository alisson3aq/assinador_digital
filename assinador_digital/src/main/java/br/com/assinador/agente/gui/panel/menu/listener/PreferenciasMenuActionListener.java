package br.com.assinador.agente.gui.panel.menu.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.com.assinador.agente.Contexto;
import br.com.assinador.agente.config.Configuracao;
import br.com.assinador.agente.config.ConfiguracaoManager;
import br.com.assinador.agente.gui.panel.conf.PreferenciasComponentsVO;
import br.com.assinador.agente.gui.panel.conf.PreferenciasDialog;

public class PreferenciasMenuActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		PreferenciasDialog prefsDialog = new PreferenciasDialog(Contexto.getMainWindow());
		PreferenciasComponentsVO componentsVO = prefsDialog.getComponentsVO();
		Configuracao conf = ConfiguracaoManager.getConfiguracao();
		
		componentsVO.setDefaultValues(conf);
		
		prefsDialog.setVisible(true);
	}

}
