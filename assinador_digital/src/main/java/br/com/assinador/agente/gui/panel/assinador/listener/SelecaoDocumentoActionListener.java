package br.com.assinador.agente.gui.panel.assinador.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.swing.JList;

import br.com.assinador.agente.Contexto;
import br.com.assinador.agente.config.ConfiguracaoManager;
import br.com.assinador.agente.gui.FileChooser;
import br.com.assinador.agente.vo.Configuracao;
import br.com.mvp.util.JListUtil;

public class SelecaoDocumentoActionListener implements ActionListener {
	
	private JList<File> jListDocumentosSelecionados;
	
	public SelecaoDocumentoActionListener(JList<File> jListDocumentosSelecionados) {
		this.jListDocumentosSelecionados = jListDocumentosSelecionados;
	}

	public void actionPerformed(ActionEvent e) {
		Configuracao configuracao = ConfiguracaoManager.getConfiguracao();
		File confDir = new File(configuracao.getDiretorioDocumentosPreferido());
		
		File[] selectedFiles = FileChooser
								.create()
								.filesOnly()
								.defaultDir(confDir)
								.withMultipleSelection()
								.choosableExtensions("Tipos Conhecidos", configuracao.getTiposConhecidos(), true)
								.getSelectedFiles(Contexto.getMainWindow());
		if (selectedFiles != null){
			JListUtil<File> listUtil = new JListUtil<>(jListDocumentosSelecionados);
			
			Set<File> arquivosSelecionados = new LinkedHashSet<>(Arrays.asList(selectedFiles));
			listUtil.addValues(arquivosSelecionados);
		}
	}
	

}
