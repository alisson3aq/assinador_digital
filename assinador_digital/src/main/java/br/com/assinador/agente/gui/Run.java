package br.com.assinador.agente.gui;

import java.awt.EventQueue;

import br.com.assinador.agente.Contexto;

public class Run {

	public static void main(String[] args) throws Exception{
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					LookAndFeelUtil.setLookAndFeel();					
					Contexto.inicarContexto();
					ApplicationMainFrame frame = new ApplicationMainFrame();
					frame.setVisible(true);
					Contexto.setAssinadorWindow(frame);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		Thread.sleep(10000);
	}

}
