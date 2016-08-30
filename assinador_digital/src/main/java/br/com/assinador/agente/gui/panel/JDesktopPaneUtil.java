package br.com.assinador.agente.gui.panel;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.KeyStroke;

public class JDesktopPaneUtil {

	private JDesktopPane desktopPane;

	public JDesktopPaneUtil(JDesktopPane desktopPane) {
		this.desktopPane = desktopPane;
	}
	
	public void reorganizeFrames(int width, int height){
		JInternalFrame[] frames = desktopPane.getAllFrames();
		for (int i = 0; i < frames.length; i++){
			JInternalFrame f = frames[i];
			f.setSize(width, height);
			f.setLocation(i * 20, i * 20);
			f.toFront();
		}
	}
	
	public void closeAllFrames() throws Exception{
		for (JInternalFrame f: desktopPane.getAllFrames())
			f.setClosed(true);
		
		desktopPane.removeAll();
	}
	
	public static void setCtrlTabInputMap(JDesktopPane desktopPane){
		KeyStroke frameTabForward = KeyStroke.getKeyStroke(
			KeyEvent.VK_T,
			KeyEvent.CTRL_DOWN_MASK
		);
		KeyStroke frameTabBackward = KeyStroke.getKeyStroke(
			KeyEvent.VK_T,
			KeyEvent.CTRL_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK
		);
		
		InputMap inputMap = desktopPane
			.getInputMap(JDesktopPane.WHEN_IN_FOCUSED_WINDOW);
		inputMap
			.put(frameTabForward, "FrameTabForward");
		inputMap
			.put(frameTabBackward, "FrameTabBackward");
		
		ActionMap actionMap = desktopPane.getActionMap();
		actionMap.put("FrameTabForward", new AltTabAction(desktopPane, true));
		actionMap.put("FrameTabBackward", new AltTabAction(desktopPane, false));
	}
	
	private static class AltTabAction extends AbstractAction{
		private static final long serialVersionUID = -6320715950048015771L;

		private JDesktopPane desktopPane;
		private boolean forward;
		
		public AltTabAction(JDesktopPane desktopPane, boolean forward) {
			this.desktopPane = desktopPane;
			this.forward = forward;
		}

		public void actionPerformed(ActionEvent e) {
			desktopPane.selectFrame(forward);
		}
		
	}
}
