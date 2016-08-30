package br.com.assinador.leitor;

public class LeitorKeyStoreFactory {

	public static LeitorKeyStore getLeitorKeyStore() {
		if (getNomeSistemaOperacional().toLowerCase().indexOf("indows") > 0) {
                return new LeitorKeyStoreMS();
        } else {
            return new LeitorKeyStoreLocal();
        }
	}
	
	private static String getNomeSistemaOperacional() {
		return System.getProperty("os.name");
	}
	
}
