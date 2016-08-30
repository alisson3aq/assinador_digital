package br.com.assinador.agente.gui.panel.conf;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTextField;

import br.com.assinador.agente.config.Configuracao;
import br.com.assinador.agente.gui.LookAndFeelUtil;
import br.com.assinador.agente.gui.LookAndFeelUtil.WrapperLookAndFeelInfo;
import br.com.assinador.agente.gui.panel.JListUtil;
import br.com.assinador.agente.gui.popup.Dialog;

public class PreferenciasComponentsVO {

	private JTextField txtPadraoDoc = new JTextField();
	private JTextField txtPadraoDocAssinado = new JTextField();
	private JComboBox<LookAndFeelUtil.WrapperLookAndFeelInfo> lookAndFeelInfoComboBox;
	private JButton btnAdicionarTipo;
	private JButton btnRemoverTipo;
	private JList<String> jListTiposArquivos;
	private Dialog dialog;
	
	public JComboBox<LookAndFeelUtil.WrapperLookAndFeelInfo> getLookAndFeelInfoComboBox() {
		return lookAndFeelInfoComboBox;
	}
	public void setLookAndFeelInfoComboBox(JComboBox<LookAndFeelUtil.WrapperLookAndFeelInfo> lookAndFeelInfoComboBox) {
		this.lookAndFeelInfoComboBox = lookAndFeelInfoComboBox;
	}
	public JTextField getTxtPadraoDoc() {
		return txtPadraoDoc;
	}
	public void setTxtPadraoDoc(JTextField txtPadraoDoc) {
		this.txtPadraoDoc = txtPadraoDoc;
	}
	public JTextField getTxtPadraoDocAssinado() {
		return txtPadraoDocAssinado;
	}
	public void setTxtPadraoDocAssinado(JTextField txtPadraoDocAssinado) {
		this.txtPadraoDocAssinado = txtPadraoDocAssinado;
	}
	public Dialog getDialog() {
		return dialog;
	}
	public void setDialog(Dialog dialog) {
		this.dialog = dialog;
	}
	public JButton getBtnAdicionarTipo() {
		return btnAdicionarTipo;
	}
	public void setBtnAdicionarTipo(JButton btnAdicionarTipo) {
		this.btnAdicionarTipo = btnAdicionarTipo;
	}
	public JButton getBtnRemoverTipo() {
		return btnRemoverTipo;
	}
	public void setBtnRemoverTipo(JButton btnRemoverTipo) {
		this.btnRemoverTipo = btnRemoverTipo;
	}
	public JList<String> getjListTiposArquivos() {
		return jListTiposArquivos;
	}
	public void setjListTiposArquivos(JList<String> jListTiposArquivos) {
		this.jListTiposArquivos = jListTiposArquivos;
	}
	
	public void setDefaultValues(Configuracao conf){
		
		getTxtPadraoDoc().setText(conf.getDiretorioDocumentosPreferido());
		getTxtPadraoDocAssinado().setText(conf.getDiretorioDocumentosAssinadosPreferido());
		
		LookAndFeelUtil lfUtil = new LookAndFeelUtil();
		WrapperLookAndFeelInfo infoByClassName = lfUtil.getInfoByClassName(conf.getAparenciaPreferida());
		getLookAndFeelInfoComboBox().setSelectedItem(infoByClassName);
		
		JListUtil<String> listUtil = new JListUtil<>(getjListTiposArquivos());
		listUtil.addValues(conf.getTiposConhecidos());
		
	}
}
