package br.com.assinador.agente.gui.panel.assinador.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.swing.JList;

import br.com.assinador.agente.Constantes;
import br.com.assinador.agente.Contexto;
import br.com.assinador.agente.config.Configuracao;
import br.com.assinador.agente.config.ConfiguracaoManager;
import br.com.assinador.agente.gui.FileChooser;
import br.com.assinador.agente.gui.panel.JListUtil;
import br.com.assinador.agente.gui.panel.assinador.AssinadorComponentsVO;

public class SelecaoDocumentoActionListener implements ActionListener {
	
	private AssinadorComponentsVO componentes;

	public SelecaoDocumentoActionListener(AssinadorComponentsVO componentes) {
		this.componentes = componentes;
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
			JList<File> listDocSelecionados = componentes.getjListDocSelecionados();
			JListUtil<File> listUtil = new JListUtil<>(listDocSelecionados);
			
			Set<File> arquivosSelecionados = new LinkedHashSet<>(Arrays.asList(selectedFiles));
			listUtil.addValues(arquivosSelecionados);
			
			Contexto.putAtributo(Constantes.DOCUMENTOS_ASSINATURA_SELECIONADOS, listUtil.getValues());
		}
	}
	

}
