package br.com.assinador.agente.gui.panel.conf.listener;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import br.com.assinador.agente.config.Configuracao;
import br.com.assinador.agente.config.ConfiguracaoManager;
import br.com.assinador.agente.gui.LookAndFeelUtil;
import br.com.assinador.agente.gui.LookAndFeelUtil.WrapperLookAndFeelInfo;
import br.com.assinador.agente.gui.panel.JListUtil;
import br.com.assinador.agente.gui.panel.conf.PreferenciasComponentsVO;

public class PreferenciaButtonActionListener implements ActionListener{

	private PreferenciasComponentsVO componentsVO;
	
	public PreferenciaButtonActionListener(PreferenciasComponentsVO componentsVO) {
		this.componentsVO = componentsVO;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		final String actionCommand = e.getActionCommand();
		
		switch (actionCommand) {
		case "Cancelar":
			componentsVO.getDialog().dispose();
			break;
		case "Salvar":
			componentsVO.getDialog().dispose();
		case "Aplicar":
			atualziarConfiguracoes(componentsVO);
			aplicarAlteracoes();
			break;
		}
	}

	private void atualziarConfiguracoes(PreferenciasComponentsVO componentsVO) {
		Configuracao conf = ConfiguracaoManager.getConfiguracao();
		WrapperLookAndFeelInfo selectedLookAndFell = (WrapperLookAndFeelInfo) componentsVO.getLookAndFeelInfoComboBox().getSelectedItem();
		
		conf.setAparenciaPreferida(selectedLookAndFell.getInfo().getClassName());
		conf.setDiretorioDocumentosPreferido(componentsVO.getTxtPadraoDoc().getText());
		conf.setDiretorioDocumentosAssinadosPreferido(componentsVO.getTxtPadraoDocAssinado().getText());
		
		JListUtil<String> listUtil = new JListUtil<>(componentsVO.getjListTiposArquivos());
		conf.setTiposConhecidos(listUtil.getValues());
		
		ConfiguracaoManager.update();
	}
	
	private void aplicarAlteracoes() {
		try {
			LookAndFeelUtil.setLookAndFeel();
			 for(Window window : JFrame.getWindows()) {
		        SwingUtilities.updateComponentTreeUI(window);
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
