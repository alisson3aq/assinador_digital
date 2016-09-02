package br.com.assinador.leitor;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;

import br.com.assinador.leitor.exception.LeitorKeyStoreException;

/**
 * Leitor de KeyStore para plataforma windows
 */
public class LeitorKeyStoreMS implements LeitorKeyStore {

	protected static final String MS_PROVIDER = "SunMSCAPI";
	protected static final String MS_TYPE = "Windows-MY";
	protected KeyStore ks;
//	protected static final String MS_ERROR_LOAD = "Error on load a KeyStore from SunMSCAPI";

	@Override
	public KeyStore getKeystore(char[] password)
			throws GeneralSecurityException, IOException {
		try {
			if (ks == null){
				ks = KeyStore.getInstance(MS_TYPE, MS_PROVIDER);
				ks.load(null, null);
			}
			return ks;
		} catch (Exception e) {
			throw new LeitorKeyStoreException("Erro tentando ler KeyStore a partir de SunMSCAPI: " + e.getMessage(), e);
		}
	}
	
/*	@SuppressWarnings("rawtypes")
	private void fixAliases(KeyStore keyStore) {
		Field field;
		KeyStoreSpi keyStoreVeritable;

		try {
			field = keyStore.getClass().getDeclaredField("keyStoreSpi");
			field.setAccessible(true);
			keyStoreVeritable = (KeyStoreSpi) field.get(keyStore);

			if ("sun.security.mscapi.KeyStore$MY".equals(keyStoreVeritable.getClass().getName())) {
				Collection entries;
				String alias, hashCode;
				X509Certificate[] certificates;

				field = keyStoreVeritable.getClass().getEnclosingClass().getDeclaredField("entries");
				field.setAccessible(true);
				entries = (Collection) field.get(keyStoreVeritable);

				for (Object entry : entries) {
					field = entry.getClass().getDeclaredField("certChain");
					field.setAccessible(true);
					certificates = (X509Certificate[]) field.get(entry);

					hashCode = Integer.toString(certificates[0].hashCode());

					field = entry.getClass().getDeclaredField("alias");
					field.setAccessible(true);
					alias = (String) field.get(entry);

					if (!alias.equals(hashCode)) {
						field.set(entry, alias.concat(" - ").concat(hashCode));
					}
				}
			}
		} catch (Exception exception) {
			System.err.println(exception);
			exception.printStackTrace();
		}
	}*/

}
