package br.com.assinador.agente.gui.panel.documento.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JTextField;

import br.com.assinador.agente.Contexto;
import br.com.assinador.agente.config.ConfiguracaoManager;
import br.com.assinador.agente.gui.FileChooser;

public class SelecaoDiretorioDestinoActionListener implements ActionListener {

	private JTextField txtDestino;

	public SelecaoDiretorioDestinoActionListener(JTextField txtDestino) {
		this.txtDestino = txtDestino;
	}

	public void actionPerformed(ActionEvent e) {
		File confDir = new File(ConfiguracaoManager.getConfiguracao().getDiretorioDocumentosPreferido());
		
		File selectedFile = FileChooser
								.create()
								.defaultDir(confDir)
								.directoriesOnly()
								.getSelectedFile(Contexto.getMainWindow());
		if (selectedFile != null)
			txtDestino.setText(selectedFile.getAbsolutePath());
	}

}
