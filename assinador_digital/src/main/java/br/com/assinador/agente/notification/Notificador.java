package br.com.assinador.agente.notification;

import java.awt.Component;

import javax.swing.JOptionPane;

public class Notificador {

	public void notificarErro(String message, String title){
		showMessage(null, message, title, JOptionPane.ERROR_MESSAGE);
	}
	
	public void notificarErro(Component parent, String message, String title){
		showMessage(parent, message, title, JOptionPane.ERROR_MESSAGE);
	}
	
	public void notificarInfo(String message, String title){
		showMessage(null, message, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void notificarInfo(Component parent, String message, String title){
		showMessage(parent, message, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void notificarAlerta(String message, String title){
		showMessage(null, message, title, JOptionPane.WARNING_MESSAGE);
	}
	
	public void notificarAlerta(Component parent, String message, String title){
		showMessage(parent, message, title, JOptionPane.WARNING_MESSAGE);
	}
	
	private void showMessage(Component parent, String message, String title, int type){
		JOptionPane.showMessageDialog(parent, message, title, type);
	}
	
	public int perguntar(String message, String title){
		return perguntar(null, message, title);
	}
	
	public int perguntar(Component parent, String message, String title){
		int decisao = JOptionPane.showConfirmDialog(parent, message, title, JOptionPane.YES_NO_OPTION);
		return decisao;
	}
}
