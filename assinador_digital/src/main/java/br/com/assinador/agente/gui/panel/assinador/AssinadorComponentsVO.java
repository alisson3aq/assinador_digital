package br.com.assinador.agente.gui.panel.assinador;

import java.io.File;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JTextField;

public class AssinadorComponentsVO {

	/*
	 *  Componentes do painel principal
	 */
	private JButton btnAssinar;
	private JCheckBox chckbxRemoverArquivosOriginais;
	
	/*
	 * Componentes do painel superior
	 */
	private JList<File> jListDocSelecionados;
	private JButton btnAdicionar;
	private JButton btnRemover;
	
	/*
	 * Componentes do painel inferior
	 */
	private JButton btnSelecionarDestino;
	private JTextField txtDestino;
	
	public JButton getBtnAssinar() {
		return btnAssinar;
	}
	public void setBtnAssinar(JButton btnAssinar) {
		this.btnAssinar = btnAssinar;
	}
	public JCheckBox getChckbxRemoverArquivosOriginais() {
		return chckbxRemoverArquivosOriginais;
	}
	public void setChckbxRemoverArquivosOriginais(JCheckBox chckbxRemoverArquivosOriginais) {
		this.chckbxRemoverArquivosOriginais = chckbxRemoverArquivosOriginais;
	}
	public JList<File> getjListDocSelecionados() {
		return jListDocSelecionados;
	}
	public void setjListDocSelecionados(JList<File> jListDocSelecionados) {
		this.jListDocSelecionados = jListDocSelecionados;
	}
	public JButton getBtnAdicionar() {
		return btnAdicionar;
	}
	public void setBtnAdicionar(JButton btnAdicionar) {
		this.btnAdicionar = btnAdicionar;
	}
	public JButton getBtnRemover() {
		return btnRemover;
	}
	public void setBtnRemover(JButton btnRemover) {
		this.btnRemover = btnRemover;
	}
	public JButton getBtnSelecionarDestino() {
		return btnSelecionarDestino;
	}
	public void setBtnSelecionarDestino(JButton btnSelecionarDestino) {
		this.btnSelecionarDestino = btnSelecionarDestino;
	}
	public JTextField getTxtDestino() {
		return txtDestino;
	}
	public void setTxtDestino(JTextField txtDestino) {
		this.txtDestino = txtDestino;
	}
}
