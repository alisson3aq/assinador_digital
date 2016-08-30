package br.com.assinador.agente.gui.panel.conf.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JTextField;

import br.com.assinador.agente.gui.FileChooser;

public class SelecionarDiretorioActionListener implements ActionListener {

	private JTextField textField;
	
	public SelecionarDiretorioActionListener(JTextField textField) {
		this.textField = textField;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		File selectedDir = FileChooser.create()
				   .directoriesOnly()
				   .getSelectedFile();
		if (selectedDir != null)
			textField.setText(selectedDir.getAbsolutePath());
	}
}
