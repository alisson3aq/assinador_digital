package br.com.assinador.agente.gui.panel.certificado.listener;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import br.com.assinador.agente.Contexto;
import br.com.assinador.agente.Logger;
import br.com.assinador.agente.config.ConfiguracaoManager;
import br.com.assinador.agente.gui.FileChooser;
import br.com.assinador.agente.gui.panel.JDesktopPaneUtil;
import br.com.assinador.agente.gui.panel.certificado.CertificadoComponentsVO;
import br.com.assinador.agente.io.FileExtension;
import br.com.assinador.agente.notification.Notificador;
import br.com.assinador.bc.AssinaturaDigitalService;

public class AbrirP7SActionListener implements ActionListener {

	private CertificadoComponentsVO componentsVO;
	
	private Notificador notificador = Contexto.getNotificador();
	private Logger logger = Contexto.getLogger();
	
	public AbrirP7SActionListener(CertificadoComponentsVO componentsVO) {
		this.componentsVO = componentsVO;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		File destDirConf = new File(ConfiguracaoManager.getConfiguracao().getDiretorioDocumentosAssinadosPreferido());
		
		File[] selectedFiles = FileChooser.create()
					.filesOnly()
					.defaultDir(destDirConf)
					.withMultipleSelection()
				   .choosableExtension("Arquivos assinados (P7S)", FileExtension.P7S, false)
				   .getSelectedFiles(Contexto.getMainWindow());
		try{
			if (selectedFiles != null){
				JDesktopPane desktopPane = componentsVO.getDesktopPane();
				desktopPane.removeAll();
				for (int i = selectedFiles.length - 1; i >= 0; i--){
					File f = selectedFiles[i];
					JInternalFrame internalFrame = createInternalFrame(f, i);
					desktopPane.add(internalFrame);
					desktopPane.validate();
				}
				JDesktopPaneUtil desktopUtil = new JDesktopPaneUtil(desktopPane);
				desktopUtil.reorganizeFrames(500, 220);
			}
			
			
		}catch (Exception e) {
			notificador.notificarErro(Contexto.getMainWindow(), "Falha na leitura dos dados do certificado \n" + e.getMessage() , "Visualizador de certificado");
			logger.log(e);
		}
	}
	
	private JInternalFrame createInternalFrame(File f, int index) throws Exception{
		
		String dn = AssinaturaDigitalService.obterSubjectDn(Files.readAllBytes(f.toPath()));
		String certInfo = dn.replaceAll("[A-Za-z]*=", "\n");
		
		JTextArea txtAreaCertInfo = new JTextArea(certInfo);
		txtAreaCertInfo.setEditable(false);
		txtAreaCertInfo.setBackground(Color.WHITE);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(txtAreaCertInfo);
		
		JInternalFrame internalFrame = new JInternalFrame(f.getName(), true, true, true, true);
		internalFrame.setLayout(new GridLayout(0, 1));
		internalFrame.setAutoscrolls(true);
		internalFrame.getContentPane().add(scrollPane);
		internalFrame.setVisible(true);
		
		return internalFrame;
	}

}
