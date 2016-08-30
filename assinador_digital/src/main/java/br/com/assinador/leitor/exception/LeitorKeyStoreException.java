package br.com.assinador.leitor.exception;

public class LeitorKeyStoreException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public LeitorKeyStoreException(String message) {
		super(message);
	}

	public LeitorKeyStoreException(String message, Throwable t) {
		super(message, t);
	}
	
}
