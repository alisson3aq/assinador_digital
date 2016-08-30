package br.com.assinador.agente.gui.panel.menu;

import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import br.com.assinador.agente.gui.panel.menu.listener.LookAndFeelActionListener;

public class SubMenuAparencia extends JMenu{

	private static final long serialVersionUID = 396078116834775148L;

	public SubMenuAparencia(){
		super("Aparência");
		
		ActionListener lfAC = new LookAndFeelActionListener();
		
		LookAndFeelInfo[] installedLookAndFeels = UIManager.getInstalledLookAndFeels();
		ButtonGroup lfMenuItemGroup = new ButtonGroup();
		
		for (LookAndFeelInfo lfInfo: installedLookAndFeels){
			JCheckBoxMenuItem lfMenuItem = new JCheckBoxMenuItem(lfInfo.getName());
			if (lfInfo.getClassName().equals(UIManager.getLookAndFeel().getClass().getName()))
				lfMenuItem.setSelected(true);
			
			lfMenuItem.addActionListener(lfAC);
			lfMenuItemGroup.add(lfMenuItem);
			add(lfMenuItem);
		}
	}
}
