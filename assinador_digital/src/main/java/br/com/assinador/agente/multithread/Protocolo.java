package br.com.assinador.agente.multithread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Protocolo<V> {

	private Future<V> future;
	private String id;
	private Tarefa<V> tarefa;
	
	public Protocolo(Future<V> future) {
		this.id = String.valueOf(System.currentTimeMillis());
		this.future = future;
	}
	
	public Protocolo(Future<V> future, Tarefa<V> tarefa) {
		this(future);
		this.tarefa = tarefa;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Resultado<V> getResultado(){
		return this.new Resultado<>(future);
	}
	public Tarefa<V> getTarefa() {
		return tarefa;
	}
	public void setTarefa(Tarefa<V> tarefa) {
		this.tarefa = tarefa;
	}
	
	public class Resultado<R>{
		
		private Future<R> future;
		private R valor;
		private Throwable t;
		
		public Resultado(Future<R> future) {
			this.future = future;
		}
		
		public boolean isPronto(){
			return future.isDone();
		}
		
		public boolean isCancelado(){
			return future.isCancelled();
		}
		
		/**
		 * A chamada a esse método retornará falso, caso a execução ainda não tenha terminado.
		 * Para ter certeza se o retorno está correto, chame isPronto()
		 * @return
		 */
		public boolean hasErrosExecucao(){
			if (isPronto() || isCancelado())
				extractValue();
			
			return t != null;
		}
		
		/**
		 * A chamada deste metodo irá bloquear a execução da thread chamadora até o término
		 * da execução da tarefa, caso ainda não tenha terminado.
		 * @return
		 */
		public R getValor(){
			if (valor == null)
				extractValue();
			
			return valor;
		}
		
		/**
		 * A chamada deste metodo irá bloquear a execução da thread chamadora até o término
		 * da execução da tarefa, caso ainda não tenha terminado.
		 * @return
		 */
		public void esperarExecucao(){
			extractValue();
		}
		
		public Throwable getErro(){
			return t;
		}
		
		private void extractValue(){
			try {
				R valor = future.get();
				this.valor = valor;
			} catch (ExecutionException e){
				t = e.getCause();
			} catch (Exception e){
				t = e;
			}
		}
		
	}
}