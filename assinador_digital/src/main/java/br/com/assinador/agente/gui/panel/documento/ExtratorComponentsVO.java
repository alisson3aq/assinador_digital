package br.com.assinador.agente.gui.panel.documento;

import java.io.File;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextField;

public class ExtratorComponentsVO {

	/*
	 *  Componentes do painel principal
	 */
	private JButton btnExtrair;
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
	
	public JButton getBtnExtrair() {
		return btnExtrair;
	}
	public void setBtnExtrair(JButton btnExtrair) {
		this.btnExtrair = btnExtrair;
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
