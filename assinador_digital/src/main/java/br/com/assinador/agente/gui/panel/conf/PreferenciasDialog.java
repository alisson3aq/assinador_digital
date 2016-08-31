package br.com.assinador.agente.gui.panel.conf;

import java.awt.BorderLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.assinador.agente.gui.popup.Dialog;

public class PreferenciasDialog extends Dialog {

	private static final long serialVersionUID = -8272815760464391421L;

	private final JPanel contentPanel = new JPanel();
	
	public PreferenciasDialog(JFrame parent) throws Exception{
		super(parent, "Preferências", true);
		
		setBounds(100, 100, 479, 351);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		PreferenciasPanel preferenciasPanel = new PreferenciasPanel();
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(preferenciasPanel, GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(preferenciasPanel, GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
		);
		contentPanel.setLayout(gl_contentPanel);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(parent);
		pack();
	}
}
