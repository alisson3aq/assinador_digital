package br.com.assinador.agente.config;

import java.io.File;

import br.com.assinador.agente.io.FileHandler;

public class ConfiguracaoManager {

	private static final String confPath = ".quality" + File.separator + "assinador" + File.separator + "conf";
	private static File configFileDir;
	private static File configFile;
	
	private static Configuracao configuracao;
	
	static{
		String homeDir = System.getProperty("user.home");
		configFileDir = new FileHandler().createDirIfNotExists(homeDir + File.separator + confPath);
		configFile = new File(configFileDir, "conf.ser");
		
		carregar();
	}
	
	public static Configuracao getConfiguracao() {
		return configuracao;
	}
	
	private static void carregar(){
		try{
			FileHandler fh = new FileHandler();
			Configuracao conf = fh.readObjectFile(configFile);
			if (conf == null)
				conf = new Configuracao();
			
			configuracao = conf;
		}catch (Exception e) {
		}
	}

	public static void update() {
		try{
			FileHandler fh = new FileHandler();
			fh.writeToFile(configFileDir, "conf", configuracao);
		}catch (Exception e) {
		}
	}
	
}
