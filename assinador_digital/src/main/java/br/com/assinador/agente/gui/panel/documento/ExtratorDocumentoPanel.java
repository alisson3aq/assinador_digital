package br.com.assinador.agente.gui.panel.documento;

import java.io.File;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import br.com.assinador.agente.Constantes;
import br.com.assinador.agente.Contexto;
import br.com.assinador.agente.config.ConfiguracaoManager;
import br.com.assinador.agente.gui.panel.documento.listener.ExtratorDocumentoActionListener;
import br.com.assinador.agente.vo.Configuracao;

public class ExtratorDocumentoPanel extends JPanel {

	private static final long serialVersionUID = -3129004503863223648L;

	public ExtratorComponentsVO componentsVO = new ExtratorComponentsVO();
	
	public ExtratorDocumentoPanel() {
		
		setBounds(100, 100, 610, 424);
		setLocation(10, 11);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		
		OrigemUpperPanel upperPanel = new OrigemUpperPanel(componentsVO);
		upperPanel.setBorder(new TitledBorder(null, "Origem", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout_1 = (GroupLayout) upperPanel.getLayout();
		groupLayout_1.setAutoCreateGaps(true);
		groupLayout_1.setAutoCreateContainerGaps(true);
		
		JButton btnExtrair = new JButton("Extrair documentos");
		btnExtrair.setIcon(new ImageIcon(ExtratorDocumentoPanel.class.getResource("/br/com/assinador/agente/gui/icon/extract.png")));
		
		DestinoLowerPanel destinoLowerPanel = new DestinoLowerPanel(componentsVO);
		destinoLowerPanel.setBorder(new TitledBorder(null, "Destino", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(upperPanel, GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
				.addComponent(destinoLowerPanel, GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(btnExtrair, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(353, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(upperPanel, GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(destinoLowerPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnExtrair, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addGap(10))
		);
		setLayout(groupLayout);
		
		
		componentsVO.setBtnExtrair(btnExtrair);
		
		btnExtrair.addActionListener(new ExtratorDocumentoActionListener());
		setConfigValues();
	}
	
	private void setConfigValues() {
		Configuracao conf = ConfiguracaoManager.getConfiguracao();
		File destDirConf = new File(conf.getDiretorioDocumentosPreferido());
		componentsVO.getTxtDestino().setText(destDirConf.getAbsolutePath());
		Contexto.putAtributo(Constantes.DIRETORIO_EXTRACAO_DESTINO_SELECIONADO, destDirConf);
	}
	
	public ExtratorComponentsVO getComponentsVO() {
		return componentsVO;
	}
}
