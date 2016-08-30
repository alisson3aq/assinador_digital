package br.com.assinador.agente.gui.panel.assinador.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Set;

import javax.swing.JList;

import br.com.assinador.agente.gui.panel.JListUtil;
import br.com.assinador.agente.gui.panel.assinador.AssinadorComponentsVO;

public class RemoverSelecionadosActionListener implements ActionListener {

	
	private AssinadorComponentsVO componentes;

	public RemoverSelecionadosActionListener(AssinadorComponentsVO componentes) {
		this.componentes = componentes;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JList<File> listDocSelecionados = componentes.getjListDocSelecionados();
		JListUtil<File> listUtil = new JListUtil<>(listDocSelecionados);
		
		Set<File> selectedElements = listUtil.getSelectedElements();
		listUtil.removeValues(selectedElements);
	}

}
