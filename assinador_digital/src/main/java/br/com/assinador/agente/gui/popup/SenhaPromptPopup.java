package br.com.assinador.agente.gui.popup;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import br.com.assinador.agente.Contexto;

public class SenhaPromptPopup extends Prompter {
	
	private static final long serialVersionUID = 6887204756477351472L;
	
	private JLabel label = new JLabel("Senha:");
	private JPasswordField senhaField = new JPasswordField(20);
	private static String[] opcoes = new String[]{"OK", "Cancelar"};
	
	private String senhaDigitada;
	private boolean acaoCancelada;
	
	public SenhaPromptPopup() {
		super(Contexto.getMainWindow(), "Senha do certificado", opcoes);
		add(label);
		add(senhaField);
	}
	
	public void open(){
		int option = super.open(JOptionPane.QUESTION_MESSAGE, senhaField);
		
		if(option != -1 && opcoes[option].equals("OK")){
			char[] password = senhaField.getPassword();
			if (password != null && password.length > 0){
				setSenhaDigitada(new String(password));
			}
		}else{
			acaoCancelada = true;
		}
	}
	
	public SenhaPromptPopup(String name) {
		this();
		setName(name);
	}
	
	private void setSenhaDigitada(String senhaDigitada) {
		this.senhaDigitada = senhaDigitada;
	}
	
	public String getSenhaDigitada() {
		return senhaDigitada;
	}
	
	public boolean isAcaoCancelada(){
		return acaoCancelada;
	}
}
