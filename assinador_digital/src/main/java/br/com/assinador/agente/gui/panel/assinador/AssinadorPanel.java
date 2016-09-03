package br.com.assinador.agente.gui.panel.assinador;

import java.io.File;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import br.com.assinador.agente.config.ConfiguracaoManager;
import br.com.assinador.agente.gui.panel.assinador.listener.AssinadorDocumentoActionListener;
import br.com.assinador.agente.vo.AssinadorVO;
import br.com.assinador.agente.vo.Configuracao;
import br.com.mvp.Controller;
import br.com.mvp.view.MVPPanel;
import br.com.mvp.view.annotation.Component;
import br.com.mvp.view.annotation.View;

@View
public class AssinadorPanel extends MVPPanel<AssinadorPanel, AssinadorVO> {

	private static final long serialVersionUID = -3129004503863223648L;

	@Component
	private JCheckBox chckbxRemoverArquivosOriginais;
	private DestinoLowerPanel destinoLowerPanel;
	private OrigemUpperPanel upperPanel;
	
	public AssinadorPanel() throws Exception{
		super(AssinadorVO.class);
		
		setBounds(100, 100, 610, 440);
		setLocation(10, 11);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		
		upperPanel = new OrigemUpperPanel();
		upperPanel.setBorder(new TitledBorder(null, "Origem", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout_1 = (GroupLayout) upperPanel.getLayout();
		groupLayout_1.setAutoCreateGaps(true);
		groupLayout_1.setAutoCreateContainerGaps(true);
		
		JButton btnAssinar = new JButton("Assinar");
		btnAssinar.setIcon(new ImageIcon(AssinadorPanel.class.getResource("/br/com/assinador/agente/gui/icon/sign_icon.png")));
		
		chckbxRemoverArquivosOriginais = new JCheckBox("Remover arquivos originais após o término");
		
		destinoLowerPanel = new DestinoLowerPanel();
		destinoLowerPanel.setBorder(new TitledBorder(null, "Destino", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(upperPanel, GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
				.addComponent(destinoLowerPanel, GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(12)
					.addComponent(btnAssinar, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(chckbxRemoverArquivosOriginais, GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(upperPanel, GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(destinoLowerPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxRemoverArquivosOriginais, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAssinar, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(13))
		);
		setLayout(groupLayout);
		
		btnAssinar.addActionListener(new AssinadorDocumentoActionListener(getController()));
		
		invokeLater(() -> {
			try{
				Controller<AssinadorPanel, AssinadorVO> controller = getController();
				Configuracao conf = ConfiguracaoManager.getConfiguracao();
				
				AssinadorVO assinadorVO = controller.getModel();
				assinadorVO.setDestino(new File(conf.getDiretorioDocumentosAssinadosPreferido()));
				
				updateView();
			}catch (Exception e) {
			}
		});
	}
}