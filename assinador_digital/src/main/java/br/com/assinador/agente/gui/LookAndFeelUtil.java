package br.com.assinador.agente.gui;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.JComboBox;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import br.com.assinador.agente.config.ConfiguracaoManager;
import br.com.assinador.agente.vo.Configuracao;

public class LookAndFeelUtil {

	private static Map<String, LookAndFeelInfo> lfMap = new HashMap<>();
	static{
		for (LookAndFeelInfo lfInfo: UIManager.getInstalledLookAndFeels())
			lfMap.put(lfInfo.getName(), lfInfo);
	}
	
	public Collection<LookAndFeelInfo> getInstalledLookAndFeel(){
		return lfMap.values();
	}
	
	public WrapperLookAndFeelInfo getWrapperInfoByClassName(String className){
		for (LookAndFeelInfo lfInfo: getInstalledLookAndFeel())
			if (lfInfo.getClassName().equals(className))
				return new WrapperLookAndFeelInfo(lfInfo);
		return null;
	}
	
	public WrapperLookAndFeelInfo getWrapperInfoByName(String name){
		LookAndFeelInfo lookAndFeelInfo = lfMap.get(name);
		if (lookAndFeelInfo == null)
			return null;
		else
			return new WrapperLookAndFeelInfo(lookAndFeelInfo);
	}
	
	public Collection<WrapperLookAndFeelInfo> getWrappedInstalledLookAndFeel(){
		return getInstalledLookAndFeel()
					.stream()
					.map(lf -> new WrapperLookAndFeelInfo(lf))
					.collect(Collectors.toList());
	}
	
	public void fillComboBox(JComboBox<WrapperLookAndFeelInfo> comboBox){
		for (WrapperLookAndFeelInfo wlfInfo: getWrappedInstalledLookAndFeel())
			comboBox.addItem(wlfInfo);
	}
	
	public static class WrapperLookAndFeelInfo{
		private LookAndFeelInfo info;
		private String name;
		
		public WrapperLookAndFeelInfo(LookAndFeelInfo info) {
			this.info = info;
			this.name = info.getName();
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
		try{
			Configuracao conf = ConfiguracaoManager.getConfiguracao();
			UIManager.setLookAndFeel(conf.getAparenciaPreferida());
		}catch (Exception e) {
		}
	}
}
