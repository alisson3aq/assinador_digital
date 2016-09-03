package br.com.assinador.agente.gui.panel.documento.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Set;

import javax.swing.JList;

import br.com.assinador.agente.gui.panel.documento.ExtratorComponentsVO;
import br.com.mvp.util.JListUtil;

public class RemoverSelecionadosActionListener implements ActionListener {

	
	private ExtratorComponentsVO componentesVO;

	public RemoverSelecionadosActionListener(ExtratorComponentsVO componentesVO) {
		this.componentesVO = componentesVO;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JList<File> listDocSelecionados = componentesVO.getjListDocSelecionados();
		JListUtil<File> listUtil = new JListUtil<>(listDocSelecionados);
		
		Set<File> selectedElements = listUtil.getSelectedElements();
		listUtil.removeValues(selectedElements);
	}

}
