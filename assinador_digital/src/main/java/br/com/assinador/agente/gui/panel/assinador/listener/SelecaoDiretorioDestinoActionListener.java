package br.com.assinador.agente.gui.panel.assinador.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import br.com.assinador.agente.Constantes;
import br.com.assinador.agente.Contexto;
import br.com.assinador.agente.config.ConfiguracaoManager;
import br.com.assinador.agente.gui.FileChooser;
import br.com.assinador.agente.gui.panel.assinador.AssinadorComponentsVO;

public class SelecaoDiretorioDestinoActionListener implements ActionListener {

	private AssinadorComponentsVO componentsVO;

	public SelecaoDiretorioDestinoActionListener(AssinadorComponentsVO componentsVO) {
		this.componentsVO = componentsVO;
	}

	public void actionPerformed(ActionEvent e) {
		File confDir = new File(ConfiguracaoManager.getConfiguracao().getDiretorioDocumentosAssinadosPreferido());
		
		File selectedFile = FileChooser
								.create()
								.defaultDir(confDir)
								.directoriesOnly()
								.getSelectedFile(Contexto.getMainWindow());
		if (selectedFile != null) {
			componentsVO.getTxtDestino().setText(selectedFile.getAbsolutePath());
			Contexto.putAtributo(Constantes.DIRETORIO_ASSINATURA_DESTINO_SELECIONADO, selectedFile);
		}
	}

}
