package br.com.assinador.agente.gui.panel.conf;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import br.com.assinador.agente.gui.LookAndFeelUtil;
import br.com.assinador.agente.gui.panel.conf.listener.AddRemoveTipoActionListener;
import br.com.assinador.agente.gui.panel.conf.listener.PreferenciaButtonActionListener;
import br.com.assinador.agente.gui.panel.conf.listener.SelecionarDiretorioActionListener;

public class PreferenciasPanel extends JPanel {
	
	private static final long serialVersionUID = -268594371846496592L;
	
	private JTextField txtDiretorioPadraoDocs;
	private JTextField txtDiretorioPadraoDocsAssinados;
	private JComboBox<LookAndFeelUtil.WrapperLookAndFeelInfo> lookAndFeelInfoComboBox;
	
	private PreferenciasComponentsVO componentsVO = new PreferenciasComponentsVO();

	public PreferenciasPanel() {
		
		JButton btnPastaDocumentos = new JButton("Pasta padrão de documentos");
		btnPastaDocumentos.setToolTipText("Diretório padrão para a busca de dumentos elegíveis a serem assinados");
		btnPastaDocumentos.setIcon(new ImageIcon(PreferenciasPanel.class.getResource("/br/com/assinador/agente/gui/icon/folder.png")));
		
		txtDiretorioPadraoDocs = new JTextField();
		txtDiretorioPadraoDocs.setBackground(Color.WHITE);
		txtDiretorioPadraoDocs.setEditable(false);
		txtDiretorioPadraoDocs.setToolTipText("Diretório padrão para a busca de dumentos elegíveis a serem assinados");
		txtDiretorioPadraoDocs.setColumns(10);
		
		JSeparator separator = new JSeparator();
		
		JButton btnPastaDeDocumentosAssinados = new JButton("Pasta padrão de documentos assinados");
		btnPastaDeDocumentosAssinados.setToolTipText("Pasta padrão onde os documentos assinados se encontram");
		btnPastaDeDocumentosAssinados.setIcon(new ImageIcon(PreferenciasPanel.class.getResource("/br/com/assinador/agente/gui/icon/folder.png")));
		
		txtDiretorioPadraoDocsAssinados = new JTextField();
		txtDiretorioPadraoDocsAssinados.setBackground(Color.WHITE);
		txtDiretorioPadraoDocsAssinados.setEditable(false);
		txtDiretorioPadraoDocsAssinados.setToolTipText("Pasta padrão onde os documentos assinados se encontram");
		txtDiretorioPadraoDocsAssinados.setColumns(10);
		
		JSeparator separator_1 = new JSeparator();
		
		JLabel lblAparncia = new JLabel("Aparência");
		
		lookAndFeelInfoComboBox = new JComboBox<>();
		
		JButton btnSalvar = new JButton("Salvar");
		JButton btnAplicar = new JButton("Aplicar");
		
		JButton btnCancelar = new JButton("Cancelar");
		
		JLabel lblTiposDeArquivos = new JLabel("Tipos de arquivos conhecidos");
		
		JLabel lblHelpIcon = new JLabel("");
		lblHelpIcon.setToolTipText("Lista de extensoes dos arquivos mais usados para facilitar o filtro no momento da assinatura.");
		lblHelpIcon.setIcon(new ImageIcon(PreferenciasPanel.class.getResource("/br/com/assinador/agente/gui/icon/help.png")));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnAdicionarTipo = new JButton("");
		btnAdicionarTipo.setName("Adicionar");
		btnAdicionarTipo.setIcon(new ImageIcon(PreferenciasPanel.class.getResource("/br/com/assinador/agente/gui/icon/add-icon.png")));
		
		JButton btnRemoverTipo = new JButton("");
		btnRemoverTipo.setName("Remover");
		btnRemoverTipo.setIcon(new ImageIcon(PreferenciasPanel.class.getResource("/br/com/assinador/agente/gui/icon/remove-icon.png")));
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(separator_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
						.addComponent(separator, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
						.addComponent(txtDiretorioPadraoDocs, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
						.addComponent(txtDiretorioPadraoDocsAssinados, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblAparncia, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
								.addComponent(lookAndFeelInfoComboBox, Alignment.TRAILING, 0, 205, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
								.addComponent(lblTiposDeArquivos, GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblHelpIcon)
									.addGap(8))
								.addComponent(btnAdicionarTipo, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnRemoverTipo, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(btnSalvar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAplicar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCancelar))
						.addComponent(btnPastaDeDocumentosAssinados)
						.addComponent(btnPastaDocumentos))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnPastaDocumentos)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtDiretorioPadraoDocs, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnPastaDeDocumentosAssinados)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtDiretorioPadraoDocsAssinados, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblAparncia)
							.addComponent(lblTiposDeArquivos))
						.addComponent(lblHelpIcon, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
					.addGap(7)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lookAndFeelInfoComboBox, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(btnAdicionarTipo)
								.addGap(8)
								.addComponent(btnRemoverTipo))))
					.addGap(61)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSalvar)
						.addComponent(btnAplicar)
						.addComponent(btnCancelar))
					.addContainerGap())
		);
		
		JList<String> jListTiposArquivos = new JList<String>(new DefaultListModel<String>());
		scrollPane.setViewportView(jListTiposArquivos);
		setLayout(groupLayout);

		btnPastaDocumentos.addActionListener(new SelecionarDiretorioActionListener(txtDiretorioPadraoDocs));
		btnPastaDeDocumentosAssinados.addActionListener(new SelecionarDiretorioActionListener(txtDiretorioPadraoDocsAssinados));
		
		new LookAndFeelUtil().fillComboBox(lookAndFeelInfoComboBox);
		componentsVO.setLookAndFeelInfoComboBox(lookAndFeelInfoComboBox);
		componentsVO.setTxtPadraoDoc(txtDiretorioPadraoDocs);
		componentsVO.setTxtPadraoDocAssinado(txtDiretorioPadraoDocsAssinados);
		componentsVO.setBtnAdicionarTipo(btnAdicionarTipo);
		componentsVO.setBtnRemoverTipo(btnRemoverTipo);
		componentsVO.setjListTiposArquivos(jListTiposArquivos);
		
		ActionListener preferenciasActionListener = new PreferenciaButtonActionListener(componentsVO);
		btnSalvar.addActionListener(preferenciasActionListener);
		btnAplicar.addActionListener(preferenciasActionListener);
		btnCancelar.addActionListener(preferenciasActionListener);

		AddRemoveTipoActionListener addRemoveAL = new AddRemoveTipoActionListener(componentsVO);
		btnAdicionarTipo.addActionListener(addRemoveAL);
		btnRemoverTipo.addActionListener(addRemoveAL);
	}
	
	public PreferenciasComponentsVO getComponentsVO() {
		return componentsVO;
	}
}
