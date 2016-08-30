package br.com.assinador.agente.gui.panel;

import java.awt.Component;
import java.io.File;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

public class FileListCellRenderer extends DefaultListCellRenderer {

	
	private static final long serialVersionUID = -659250615930353336L;

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		File file = (File) value;
		setText(file.getAbsolutePath());
		return this;
	}
}
