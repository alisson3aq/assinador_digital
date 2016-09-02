package br.com.assinador.agente.gui.popup;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class NovoTipoArquivoConhecidoPopup extends Prompter {

	private static final long serialVersionUID = 348898221235893396L;
	
	private JLabel label = new JLabel("Tipos:");
	private JTextField txtTipo = new JTextField(30);
	private static String[] opcoes = new String[]{"OK", "Cancelar"};
	private String tiposDigitados;
	private boolean ok;
	
	public NovoTipoArquivoConhecidoPopup(Component parent) {
		super(parent, "Tipos de arquivos conhecidos", opcoes);
		add(label);
		add(txtTipo);
	}

	public void open() {
		int opcao = super.open(JOptionPane.PLAIN_MESSAGE, txtTipo);
		ok = opcao != -1 && opcoes[opcao].equals("OK");
		
		if (ok){
			tiposDigitados = txtTipo.getText();
		}
	}
	
	public String getTiposDigitados() {
		return tiposDigitados;
	}
	
	public boolean isAcaoCancelada() {
		return ok;
	}
}
