package br.com.assinador.bc.exception;

public class AssinaturaInvalidaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AssinaturaInvalidaException(String message) {
		super(message);
	}

	public AssinaturaInvalidaException(String message, Throwable t) {
		super(message, t);
	}
	
}
