package br.com.assinador.agente.gui.panel.documento.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import br.com.assinador.agente.Constantes;
import br.com.assinador.agente.Contexto;
import br.com.assinador.agente.config.ConfiguracaoManager;
import br.com.assinador.agente.gui.FileChooser;
import br.com.assinador.agente.gui.panel.documento.ExtratorComponentsVO;

public class SelecaoDiretorioDestinoActionListener implements ActionListener {

	private ExtratorComponentsVO componentesVO;

	public SelecaoDiretorioDestinoActionListener(ExtratorComponentsVO componentesVO) {
		this.componentesVO = componentesVO;
	}

	public void actionPerformed(ActionEvent e) {
		File confDir = new File(ConfiguracaoManager.getConfiguracao().getDiretorioDocumentosPreferido());
		
		File selectedFile = FileChooser
								.create()
								.defaultDir(confDir)
								.directoriesOnly()
								.getSelectedFile(Contexto.getMainWindow());
		if (selectedFile != null) {
			componentesVO.getTxtDestino().setText(selectedFile.getAbsolutePath());
			Contexto.putAtributo(Constantes.DIRETORIO_EXTRACAO_DESTINO_SELECIONADO, selectedFile);
		}
	}

}
