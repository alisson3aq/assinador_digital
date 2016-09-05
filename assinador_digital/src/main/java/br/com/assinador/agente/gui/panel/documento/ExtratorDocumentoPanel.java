package br.com.assinador.agente.gui.panel.documento;

import java.io.File;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import br.com.assinador.agente.config.ConfiguracaoManager;
import br.com.assinador.agente.gui.panel.documento.listener.ExtratorDocumentoActionListener;
import br.com.assinador.agente.vo.AssinadorVO;
import br.com.assinador.agente.vo.Configuracao;
import br.com.mvp.Controller;
import br.com.mvp.view.MVPPanel;
import br.com.mvp.view.annotation.View;

@View
public class ExtratorDocumentoPanel extends MVPPanel<ExtratorDocumentoPanel, AssinadorVO> {

	private static final long serialVersionUID = -3129004503863223648L;
	private OrigemUpperPanel upperPanel;
	private DestinoLowerPanel destinoLowerPanel;

	public ExtratorDocumentoPanel() {
		super(AssinadorVO.class);
		
		setBounds(100, 100, 610, 424);
		setLocation(10, 11);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		
		upperPanel = new OrigemUpperPanel();
		upperPanel.setBorder(new TitledBorder(null, "Origem", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_upperPanel = (GroupLayout) upperPanel.getLayout();
		gl_upperPanel.setAutoCreateGaps(true);
		gl_upperPanel.setAutoCreateContainerGaps(true);
		
		JButton btnExtrair = new JButton("Extrair documentos");
		btnExtrair.setIcon(new ImageIcon(ExtratorDocumentoPanel.class.getResource("/br/com/assinador/agente/gui/icon/extract.png")));
		
		destinoLowerPanel = new DestinoLowerPanel();
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
		
		btnExtrair.addActionListener(new ExtratorDocumentoActionListener(getController()));
		
		invokeLater(() -> {
			try{
				Controller<ExtratorDocumentoPanel, AssinadorVO> controller = getController();
				Configuracao conf = ConfiguracaoManager.getConfiguracao();
				
				AssinadorVO assinadorVO = controller.getModel();
				assinadorVO.setDestino(new File(conf.getDiretorioDocumentosPreferido()));
				
				updateView();
			}catch (Exception e) {
			}
		});
	}
}
