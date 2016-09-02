package br.com.assinador.bc.exception;

public class TokenException extends RuntimeException {

	private static final long serialVersionUID = 6265545975154452755L;

	public TokenException() {
		super();
	}

	public TokenException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public TokenException(String arg0) {
		super(arg0);
	}

	public TokenException(Throwable arg0) {
		super(arg0);
	}

}
