package br.com.assinador.agente.gui.panel.menu;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import br.com.assinador.agente.gui.panel.menu.listener.PreferenciasMenuActionListener;

public class MenuConfiguracoes extends JMenu{

	private static final long serialVersionUID = 551085758521880937L;

	public MenuConfiguracoes() {
		super("Configurações");
		
		JMenuItem preferenciasItem = new JMenuItem("Preferências");
		preferenciasItem.addActionListener(new PreferenciasMenuActionListener());
		add(preferenciasItem);
		add(new SubMenuAparencia());
	}
}
