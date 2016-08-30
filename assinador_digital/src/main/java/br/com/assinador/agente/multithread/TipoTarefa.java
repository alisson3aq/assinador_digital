package br.com.assinador.agente.multithread;

public enum TipoTarefa {

	ASSINATURA_DOCUMENTO("Assinatura digital de documento"),
	ESTRACAO_DOCUMENTO("EStração de documento de arquivo assinado"),
	IO("File System"),
	CLIPBOARD_OBSERVER("Clipboard Observer"),
	SWING_EVENT("Swing event"),
	START("Start"),
	;
	
	private String nome;
	
	private TipoTarefa(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
}
