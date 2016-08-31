package br.com.assinador.agente.gui.panel.conf.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JList;

import br.com.assinador.agente.config.Configuracao;
import br.com.assinador.agente.gui.panel.JListUtil;
import br.com.assinador.agente.gui.panel.conf.PreferenciasPanel;
import br.com.assinador.agente.gui.popup.NovoTipoArquivoConhecidoPopup;
import br.com.mvp.Controller;

public class AddRemoveTipoActionListener implements ActionListener {

	private Controller<PreferenciasPanel, Configuracao> controller;
	private JList<String> jList;
	
	public AddRemoveTipoActionListener(Controller<PreferenciasPanel, Configuracao> controller, JList<String> jList) {
		this.controller = controller;
		this.jList = jList;
	}

	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		if (button.getName().equals("Adicionar"))
			adicionarTipos();
		else if (button.getName().equals("Remover"))
			removerTipos();
			
	}
	
	public void adicionarTipos(){
		NovoTipoArquivoConhecidoPopup popup = new NovoTipoArquivoConhecidoPopup(controller.getView());
		popup.open();
		
		if (popup.isOk()){
			String tiposDigitados = popup.getTiposDigitados();
			String[] tipos = tiposDigitados.split("[,\\s+|.,]+");
			if (tipos.length != 0 && !(tipos.length == 1 && tipos[0].equals(""))){
				JListUtil<String> listUtil = new JListUtil<>(jList);
				listUtil.addValues(tipos);
			}
		}
	}
	
	public void removerTipos(){
		JListUtil<String> listUtil = new JListUtil<>(jList);
		Set<String> selectedElements = listUtil.getSelectedElements();
		listUtil.removeValues(selectedElements);
	}

}
