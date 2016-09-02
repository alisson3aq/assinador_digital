package br.com.assinador.agente.gui.popup;

import java.awt.Component;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public abstract class Prompter extends JPanel {

	private static final long serialVersionUID = -5125943940392612945L;
	private Component parent;
	private String title;
	private String[] options;

	public Prompter(Component parent, String title, String[] options) {
		super();
		this.parent = parent;
		this.title = title;
		this.options = options;
	}

	public abstract void open();
	
	protected int open(int messageType, Object focus){
		int option = JOptionPane.showOptionDialog(
				parent, 
				this, 
				title,
				JOptionPane.NO_OPTION, messageType,
				null, 
				options, 
				focus);
		if (option >= options.length)
			option = -1;
		
		return option;
	}
	
	public void close(){
		removeAll();
		revalidate();
		repaint();
	}
	
	public abstract boolean isAcaoCancelada();
}
