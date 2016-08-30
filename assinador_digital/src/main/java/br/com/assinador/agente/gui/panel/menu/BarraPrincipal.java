package br.com.assinador.agente.gui.panel.menu;

import javax.swing.JMenuBar;

public class BarraPrincipal extends JMenuBar{

	public BarraPrincipal() {
		add(new MenuConfiguracoes());
	}
}
