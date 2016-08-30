package br.com.assinador.agente.multithread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Executor {

	private ExecutorService execService;
	
	public Executor(){
		ExecutorService cachedThreadExecutor = Executors.newCachedThreadPool();
		this.execService = cachedThreadExecutor;
	}
	
	public <V> Protocolo<V> executarTarefa(Tarefa<V> tarefa){
		Future<V> future = execService.submit(tarefa);
		Protocolo<V> protocolo = gerarProtocolo(future, tarefa);
		return protocolo;
	}
	
	public void shutdown(){
		execService.shutdownNow();
	}
	
	private <V> Protocolo<V> gerarProtocolo(Future<V> future, Tarefa<V> tarefa){
		Protocolo<V> protocolo = new Protocolo<V>(future, tarefa);
		return protocolo;
	}
}
