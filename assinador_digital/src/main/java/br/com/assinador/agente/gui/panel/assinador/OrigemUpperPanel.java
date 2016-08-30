package br.com.assinador.agente.gui.panel.assinador;

import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle.ComponentPlacement;

import br.com.assinador.agente.gui.panel.FileListCellRenderer;
import br.com.assinador.agente.gui.panel.assinador.listener.RemoverSelecionadosActionListener;
import br.com.assinador.agente.gui.panel.assinador.listener.SelecaoDocumentoActionListener;

class OrigemUpperPanel extends JPanel {
	
	private static final long serialVersionUID = -5540154856994635443L;
	
	public OrigemUpperPanel(AssinadorComponentsVO componentsVO) {
		
		JSeparator separator = new JSeparator();
		JLabel lblDocumentos = new JLabel("Documentos");
		JScrollPane scrollPane = new JScrollPane();
		JLabel iconQuality = new JLabel("");
		JLabel iconCRI = new JLabel("");
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setIcon(new ImageIcon(OrigemUpperPanel.class.getResource("/br/com/assinador/agente/gui/icon/file_icon.png")));
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.setIcon(new ImageIcon(OrigemUpperPanel.class.getResource("/br/com/assinador/agente/gui/icon/remove.png")));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(iconCRI, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
						.addComponent(iconQuality, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(separator, GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnAdicionar)
							.addPreferredGap(ComponentPlacement.RELATED, 327, Short.MAX_VALUE)
							.addComponent(btnRemover)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblDocumentos, GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
							.addGap(173))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE)
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDocumentos)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(iconQuality, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(iconCRI, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRemover, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnAdicionar, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addGap(50))
		);
		
		DefaultListModel<File> fileListModel = new DefaultListModel<>();;
		JList<File> jListDocumentosSelecionados = new JList<File>();
		jListDocumentosSelecionados.setModel(fileListModel);
		jListDocumentosSelecionados.setCellRenderer(new FileListCellRenderer());
		scrollPane.setViewportView(jListDocumentosSelecionados);
		setLayout(groupLayout);
		
		componentsVO.setBtnAdicionar(btnAdicionar);
		componentsVO.setBtnRemover(btnRemover);
		componentsVO.setjListDocSelecionados(jListDocumentosSelecionados);

		btnAdicionar.addActionListener(new SelecaoDocumentoActionListener(componentsVO));
		btnRemover.addActionListener(new RemoverSelecionadosActionListener(componentsVO));
	}
}
