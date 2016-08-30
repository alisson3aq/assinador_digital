package br.com.assinador.agente.multithread;

public class ExecucaoException extends Exception {
	
	private static final long serialVersionUID = 6514620535684496854L;
	
	private boolean notificar;

	public ExecucaoException(boolean notificar) {
	}

	public ExecucaoException(String message, boolean notificar) {
		super(message);
		this.notificar = notificar;
	}

	public ExecucaoException(Throwable cause, boolean notificar) {
		super(cause);
		this.notificar = notificar;
	}

	public ExecucaoException(String message, Throwable cause, boolean notificar) {
		super(message, cause);
		this.notificar = notificar;
	}

	public ExecucaoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public boolean isNotificar() {
		return notificar;
	}

}
