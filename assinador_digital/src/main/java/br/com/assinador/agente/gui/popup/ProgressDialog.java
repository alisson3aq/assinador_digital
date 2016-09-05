package br.com.assinador.agente.gui.popup;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JProgressBar;

import br.com.assinador.agente.Contexto;

public class ProgressDialog extends Dialog{

	private static final long serialVersionUID = -2136306563628830169L;
	private JProgressBar progressBar;
	
	public ProgressDialog(String title){
		super(Contexto.getMainWindow(), title, true);
		setLocationRelativeTo(Contexto.getMainWindow());
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setSize(500, 60);
		setResizable(false);
		
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		
		add(BorderLayout.CENTER, progressBar);
		setLocationRelativeTo(Contexto.getMainWindow());
		start();
	}
	
	public void setLimites(int min, int max){
		progressBar.setMinimum(min);
		progressBar.setMaximum(max);
		progressBar.setValue(min);
	}
	
	public void progredir(String textoProgresso, int progresso){
		progressBar.setString(textoProgresso);
		progredir(progresso);
	}
	
	public void progredir(int progresso){
		int novoProgresso = progressBar.getValue() + progresso;
		if (novoProgresso == progressBar.getMaximum())
			setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		progressBar.setValue(novoProgresso);
	}
}
