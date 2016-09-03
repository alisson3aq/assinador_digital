package br.com.assinador.agente.gui.panel.assinador.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Set;

import javax.swing.JList;

import br.com.mvp.util.JListUtil;


public class RemoverSelecionadosActionListener implements ActionListener {

	
	private JList<File> jListDocumentosSelecionados;

	public RemoverSelecionadosActionListener(JList<File> jListDocumentosSelecionados) {
		this.jListDocumentosSelecionados = jListDocumentosSelecionados;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JListUtil<File> listUtil = new JListUtil<>(jListDocumentosSelecionados);
		
		Set<File> selectedElements = listUtil.getSelectedElements();
		listUtil.removeValues(selectedElements);
	}

}
