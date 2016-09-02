package br.com.assinador.agente.gui.popup;

import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import br.com.assinador.agente.Contexto;

public class SelecaoDispositivoPrompter extends Prompter {
	
	private static final long serialVersionUID = 6887204756477351472L;
	
	private JLabel label = new JLabel("Dispositivo:");
	private JComboBox<String> comboDispositivo = new JComboBox<String>();
	private static String[] opcoes = new String[]{"OK", "Cancelar"};
	
	private String dispositivoSelecionado;
	private boolean acaoCancelada;
	
	public SelecaoDispositivoPrompter(List<String> options) {
		super(Contexto.getMainWindow(), "Selecione o certificado", opcoes);
		add(label);
		add(comboDispositivo);
		for (String opt: options)
			comboDispositivo.addItem(opt);
	}
	
	public void open(){
		int option = super.open(JOptionPane.INFORMATION_MESSAGE, comboDispositivo);
		
		if(option != -1 && opcoes[option].equals("OK")){
			dispositivoSelecionado = comboDispositivo.getSelectedItem().toString();
		}else{
			acaoCancelada = true;
		}
	}

	public String getDispositivoSelecionado() {
		return dispositivoSelecionado;
	}

	public boolean isAcaoCancelada() {
		return acaoCancelada;
	}
}
