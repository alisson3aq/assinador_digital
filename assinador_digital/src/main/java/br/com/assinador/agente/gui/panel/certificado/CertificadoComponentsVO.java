package br.com.assinador.agente.gui.panel.certificado;

import javax.swing.JButton;
import javax.swing.JDesktopPane;

public class CertificadoComponentsVO {

	private JButton btnAbrir;
	private JDesktopPane desktopPane;
	private JButton btnOrganizarFrames;
	private JButton btnFecharFrames;
	
	public JButton getBtnAbrir() {
		return btnAbrir;
	}
	public void setBtnAbrir(JButton btnAbrir) {
		this.btnAbrir = btnAbrir;
	}
	public JDesktopPane getDesktopPane() {
		return desktopPane;
	}
	public void setDesktopPane(JDesktopPane desktopPane) {
		this.desktopPane = desktopPane;
	}
	public void setBtnOrganizarFrames(JButton btnOrganizarFrames) {
		this.btnOrganizarFrames = btnOrganizarFrames;
		
	}
	public JButton getBtnOrganizarFrames() {
		return btnOrganizarFrames;
	}
	public JButton getBtnFecharFrames() {
		return btnFecharFrames;
	}
	public void setBtnFecharFrames(JButton btnFecharFrames) {
		this.btnFecharFrames = btnFecharFrames;
	}
}

