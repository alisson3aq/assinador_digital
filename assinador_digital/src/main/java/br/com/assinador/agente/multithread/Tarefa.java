package br.com.assinador.agente.multithread;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import br.com.assinador.agente.Contexto;
import br.com.assinador.agente.Logger;
import br.com.assinador.agente.notification.Notificador;

public abstract class Tarefa<V> implements Callable<V> {
	
	private Map<Object, Object> contextoTarefa = Collections.synchronizedMap(new HashMap<>());
	
	private Notificador notificador = Contexto.getNotificador();
	private Logger logger = Contexto.getLogger();
	private Protocolo<?> dependente;
	
	private TipoTarefa tipo;
	
	public Tarefa(TipoTarefa tipo) {
		this.tipo = tipo;
	}
	
	public Tarefa(Protocolo<?> dependente, TipoTarefa tipo) {
		this(tipo);
		this.dependente = dependente;
		
		Tarefa<?> tarefa = dependente.getTarefa();
		tarefa.contextoTarefa = this.contextoTarefa;
	}
	
	public Tarefa(Protocolo<?> dependente, TipoTarefa tipo, Map<Object, Object> contextoTarefa) {
		this(dependente, tipo);
		this.contextoTarefa = contextoTarefa;
	}

	@Override
	public final V call() throws ExecucaoException {
		try{
			if (dependente != null)
				dependente.getResultado().esperarExecucao();
			
			return executar(contextoTarefa);
		}catch (ExecucaoException e) {
			if (e.isNotificar())
				notificarErroDeExecucao(e);
			throw e;
		}
	}
	
	protected abstract V executar(Map<Object, Object> contextoTarefa) throws ExecucaoException;
	
	protected void notificarTerminoComErro(String message){
		notificador.notificarErro(Contexto.getMainWindow(), message, tipo.getNome());
	}
	
	protected void notificarTerminoComSucesso(String message){
		notificador.notificarInfo(Contexto.getMainWindow(), message, tipo.getNome());
	}
	
	private void notificarErroDeExecucao(Throwable e){
		notificador.notificarErro(Contexto.getMainWindow(), e.getMessage(), tipo.getNome());
		logger.log("Erro na execucao da tarefa " + tipo.getNome(), e);
	}
}
