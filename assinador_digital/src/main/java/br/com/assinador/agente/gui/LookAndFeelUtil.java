package br.com.assinador.agente.gui;

import javax.swing.JComboBox;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import br.com.assinador.agente.config.Configuracao;
import br.com.assinador.agente.config.ConfiguracaoManager;

public class LookAndFeelUtil {

	public LookAndFeelInfo[] getInstalledLookAndFeel(){
		return UIManager.getInstalledLookAndFeels();
	}
	
	public WrapperLookAndFeelInfo getInfoByClassName(String className){
		for (LookAndFeelInfo lfInfo: getInstalledLookAndFeel())
			if (lfInfo.getClassName().equals(className))
				return new WrapperLookAndFeelInfo(lfInfo, lfInfo.getName());
		
		return null;
	}
	
	public WrapperLookAndFeelInfo[] getWrappedInstalledLookAndFeel(){
		LookAndFeelInfo[] installedLookAndFeel = getInstalledLookAndFeel();
		WrapperLookAndFeelInfo[] wfInfo = new WrapperLookAndFeelInfo[installedLookAndFeel.length];
		
		for (int i=0; i < installedLookAndFeel.length; i++)
			wfInfo[i] = new WrapperLookAndFeelInfo(installedLookAndFeel[i], installedLookAndFeel[i].getName());
		
		return wfInfo;
	}
	
	public void fillComboBox(JComboBox<WrapperLookAndFeelInfo> comboBox){
		for (WrapperLookAndFeelInfo wlfInfo: getWrappedInstalledLookAndFeel())
			comboBox.addItem(wlfInfo);
	}
	
	public static class WrapperLookAndFeelInfo{
		private LookAndFeelInfo info;
		private String name;
		
		public WrapperLookAndFeelInfo(LookAndFeelInfo info, String name) {
			this.info = info;
			this.name = name;
		}

		public LookAndFeelInfo getInfo() {
			return info;
		}
		public void setInfo(LookAndFeelInfo info) {
			this.info = info;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		@Override
		public String toString() {
			return name;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			WrapperLookAndFeelInfo other = (WrapperLookAndFeelInfo) obj;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}
	}

	public static void setLookAndFeel() throws Exception{
		Configuracao conf = ConfiguracaoManager.getConfiguracao();
		UIManager.setLookAndFeel(conf.getAparenciaPreferida());
	}
}
