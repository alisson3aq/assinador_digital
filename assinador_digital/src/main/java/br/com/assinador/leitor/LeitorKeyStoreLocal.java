package br.com.assinador.leitor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;


/**
 * Leitor de KeyStore local
 * utilizado para testes
 * com um arquivo jks
 * localizado no file system 
 */
public class LeitorKeyStoreLocal implements LeitorKeyStore {

	@Override
	public KeyStore getKeystore(char[] password) throws GeneralSecurityException, IOException {
	    KeyStore keystore = KeyStore.getInstance("JKS");
    	File f = new File("src/main/resources/keystore.jks");
    	FileInputStream fis = new FileInputStream(f);		
    	keystore.load(fis, password);
	    return keystore;
	  }
	
}
