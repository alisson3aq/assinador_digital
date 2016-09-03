package br.com.assinador.agente.gui.panel.menu.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import br.com.assinador.agente.Contexto;
import br.com.assinador.agente.Logger;
import br.com.assinador.agente.config.ConfiguracaoManager;
import br.com.assinador.agente.vo.Configuracao;

public class LookAndFeelActionListener implements ActionListener {
	
	private Map<String, LookAndFeelInfo> lfMap = new HashMap<>();
	private Logger logger = Contexto.getLogger();
	
	public LookAndFeelActionListener() {
		for (LookAndFeelInfo lfInfo: UIManager.getInstalledLookAndFeels())
			lfMap.put(lfInfo.getName(), lfInfo);
	}

	public void actionPerformed(ActionEvent event) {
		try{
			JMenuItem jmi = (JMenuItem) event.getSource();
			String lfName = jmi.getText();
			
			String lfClassName = lfMap.get(lfName).getClassName();
			UIManager.setLookAndFeel(lfClassName);
			
			Configuracao configuracao = ConfiguracaoManager.getConfiguracao();
			configuracao.setAparenciaPreferida(lfClassName);
			ConfiguracaoManager.update(configuracao);
			
			SwingUtilities.updateComponentTreeUI(Contexto.getMainWindow());
			Contexto.getMainWindow().validate();
			Contexto.getMainWindow().repaint();
		}catch (Exception e) {
			logger.log(e);
		}
	}

}
