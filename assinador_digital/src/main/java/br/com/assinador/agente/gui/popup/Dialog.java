package br.com.assinador.agente.gui.popup;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public abstract class Dialog extends JDialog {

	private static final long serialVersionUID = -8308742297703370468L;

	public Dialog(JFrame mainWindow, String title, boolean isModal) {
		super(mainWindow, title, isModal);
	}

	public void start(){
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				setVisible(true);
			}
		});
	}
	
	public void dispose(){
		dispose(0);
	}
	
	public void dispose(long delay){
		try{
			Thread.sleep(delay);
		}catch (Exception e){}
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(false);
		super.dispose();
	}
}
