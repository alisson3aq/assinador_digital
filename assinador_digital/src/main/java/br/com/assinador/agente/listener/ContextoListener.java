package br.com.assinador.agente.listener;

public interface ContextoListener {

	void novoAtributo(Object chave, Object valor);
	void atributoRemovido(Object chave, Object valor);
}
