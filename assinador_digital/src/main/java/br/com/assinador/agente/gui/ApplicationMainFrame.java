package br.com.assinador.agente.gui;

import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import br.com.assinador.agente.gui.panel.assinador.AssinadorPanel;
import br.com.assinador.agente.gui.panel.certificado.CertificadoPanel;
import br.com.assinador.agente.gui.panel.documento.ExtratorDocumentoPanel;
import br.com.assinador.agente.gui.panel.menu.BarraPrincipal;

public class ApplicationMainFrame extends JFrame {

	private static final long serialVersionUID = -8779308878012800106L;

	public ApplicationMainFrame() throws Exception{
		setTitle("Assinador Digital de Documentos (P7S)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);

		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		BarraPrincipal barraMenu = new BarraPrincipal();
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Dialog", Font.BOLD, 10));
		
		AssinadorPanel assinadorPanel = new AssinadorPanel();
		tabbedPane.addTab("Assinador", new ImageIcon(AssinadorPanel.class.getResource("/br/com/assinador/agente/gui/icon/sign_icon.png")), assinadorPanel, null);
		
		ExtratorDocumentoPanel extratorPanel = new ExtratorDocumentoPanel();
		tabbedPane.addTab("Extrair", new ImageIcon(ExtratorDocumentoPanel.class.getResource("/br/com/assinador/agente/gui/icon/extract.png")), extratorPanel, null);
		
		CertificadoPanel certificadoNapel = new CertificadoPanel();
		tabbedPane.addTab("Certificado", new ImageIcon(ExtratorDocumentoPanel.class.getResource("/br/com/assinador/agente/gui/icon/certificate.png")), certificadoNapel, null);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(barraMenu, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(barraMenu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tabbedPane)
					.addGap(2))
		);
		contentPane.setLayout(gl_contentPane);

		validate();
		setLocationRelativeTo(null);
	}
}
