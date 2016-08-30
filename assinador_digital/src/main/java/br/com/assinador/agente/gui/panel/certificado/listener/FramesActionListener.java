package br.com.assinador.agente.gui.panel.certificado.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDesktopPane;

import br.com.assinador.agente.gui.panel.JDesktopPaneUtil;
import br.com.assinador.agente.gui.panel.certificado.CertificadoComponentsVO;

public class FramesActionListener implements ActionListener{

	private CertificadoComponentsVO componentsVO;

	public FramesActionListener(CertificadoComponentsVO componentsVO) {
		this.componentsVO = componentsVO;
	}
	
	public void actionPerformed(ActionEvent event) {
		try{
			String buttonName = ((JButton)event.getSource()).getName();
			if (buttonName.equals("OrganizarFrames"))
				organizar();
			else if (buttonName.equals("FecharFrames"))
				fechar();
		}catch (Exception e) {
		}
	}

	private void fechar() throws Exception{
		JDesktopPane desktopPane = componentsVO.getDesktopPane();
		JDesktopPaneUtil desktopUtil = new JDesktopPaneUtil(desktopPane);
		desktopUtil.closeAllFrames();
	}

	public void organizar() {
		JDesktopPane desktopPane = componentsVO.getDesktopPane();
		JDesktopPaneUtil desktopUtil = new JDesktopPaneUtil(desktopPane);
		desktopUtil.reorganizeFrames(500, 220);
	}
}
