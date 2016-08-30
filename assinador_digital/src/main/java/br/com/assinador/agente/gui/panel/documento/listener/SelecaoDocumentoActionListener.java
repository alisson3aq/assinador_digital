package br.com.assinador.agente.gui.panel.documento.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.swing.JList;

import br.com.assinador.agente.Constantes;
import br.com.assinador.agente.Contexto;
import br.com.assinador.agente.config.ConfiguracaoManager;
import br.com.assinador.agente.gui.FileChooser;
import br.com.assinador.agente.gui.panel.JListUtil;
import br.com.assinador.agente.gui.panel.documento.ExtratorComponentsVO;
import br.com.assinador.agente.io.FileExtension;

public class SelecaoDocumentoActionListener implements ActionListener {
	
	private ExtratorComponentsVO componentesVO;

	public SelecaoDocumentoActionListener(ExtratorComponentsVO componentesVO) {
		this.componentesVO = componentesVO;
	}

	public void actionPerformed(ActionEvent e) {
		File confDir = new File(ConfiguracaoManager.getConfiguracao().getDiretorioDocumentosAssinadosPreferido());
		
		File[] selectedFiles = FileChooser
								.create()
								.filesOnly()
								.defaultDir(confDir)
								.withMultipleSelection()
								.choosableExtension("Arquivos assinados (P7S)", FileExtension.P7S, false)
								.getSelectedFiles(Contexto.getMainWindow());
		if (selectedFiles != null){
			JList<File> listDocSelecionados = componentesVO.getjListDocSelecionados();
			JListUtil<File> listUtil = new JListUtil<>(listDocSelecionados);
			
			Set<File> arquivosSelecionados = new LinkedHashSet<>(Arrays.asList(selectedFiles));
			listUtil.addValues(arquivosSelecionados);
			
			Contexto.putAtributo(Constantes.DOCUMENTOS_EXTRACAO_SELECIONADOS, listUtil.getValues());
		}
	}
	

}
