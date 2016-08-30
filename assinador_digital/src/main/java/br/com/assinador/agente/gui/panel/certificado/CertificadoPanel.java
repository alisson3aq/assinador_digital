package br.com.assinador.agente.gui.panel.certificado;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.BevelBorder;

import br.com.assinador.agente.gui.panel.JDesktopPaneUtil;
import br.com.assinador.agente.gui.panel.certificado.listener.AbrirP7SActionListener;
import br.com.assinador.agente.gui.panel.certificado.listener.FramesActionListener;

public class CertificadoPanel extends JPanel {
	
	private static final long serialVersionUID = -514462645073040966L;
	
	private CertificadoComponentsVO componentsVO = new CertificadoComponentsVO();

	public CertificadoPanel() {
		
		JButton btnAbrir = new JButton("Abrir (p7s)");
		btnAbrir.setIcon(new ImageIcon(CertificadoPanel.class.getResource("/br/com/assinador/agente/gui/icon/sign_icon.png")));
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
		desktopPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		JDesktopPaneUtil.setCtrlTabInputMap(desktopPane);
		
		GroupLayout gl_desktopPane = new GroupLayout(desktopPane);
		gl_desktopPane.setHorizontalGroup(
			gl_desktopPane.createParallelGroup(Alignment.LEADING)
				.addGap(0, 447, Short.MAX_VALUE)
				.addGap(0, 447, Short.MAX_VALUE)
		);
		gl_desktopPane.setVerticalGroup(
			gl_desktopPane.createParallelGroup(Alignment.LEADING)
				.addGap(0, 260, Short.MAX_VALUE)
				.addGap(0, 260, Short.MAX_VALUE)
		);
		desktopPane.setLayout(gl_desktopPane);
		
		JButton btnOrganizarFrames = new JButton("");
		btnOrganizarFrames.setName("OrganizarFrames");
		btnOrganizarFrames.setToolTipText("Reorganizar janelas");
		btnOrganizarFrames.setIcon(new ImageIcon(CertificadoPanel.class.getResource("/br/com/assinador/agente/gui/icon/cascade_window.png")));
		
		JButton btnFecharFrames = new JButton("");
		btnFecharFrames.setName("FecharFrames");
		btnFecharFrames.setToolTipText("Fechar todas as janelas");
		btnFecharFrames.setIcon(new ImageIcon(CertificadoPanel.class.getResource("/br/com/assinador/agente/gui/icon/close_window.png")));
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnAbrir)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnOrganizarFrames, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnFecharFrames, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addComponent(btnOrganizarFrames, 0, 0, Short.MAX_VALUE)
							.addComponent(btnAbrir))
						.addComponent(btnFecharFrames, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(desktopPane, GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
					.addContainerGap())
		);
		setLayout(groupLayout);
		
		componentsVO.setBtnAbrir(btnAbrir);
		componentsVO.setDesktopPane(desktopPane);
		componentsVO.setBtnOrganizarFrames(btnOrganizarFrames);
		componentsVO.setBtnFecharFrames(btnFecharFrames);
		
		btnAbrir.addActionListener(new AbrirP7SActionListener(componentsVO));
		FramesActionListener framesAL = new FramesActionListener(componentsVO);
		btnOrganizarFrames.addActionListener(framesAL);
		btnFecharFrames.addActionListener(framesAL);
	}
}
