package br.com.assinador.leitor;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;

public interface LeitorKeyStore {

	KeyStore getKeystore(char[] password) throws GeneralSecurityException,
			IOException;

}