package br.com.assinador.agente;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import br.com.assinador.agente.listener.ContextoListener;
import br.com.assinador.agente.multithread.Executor;
import br.com.assinador.agente.notification.Notificador;

@SuppressWarnings("unchecked")
public class Contexto {

	public static Map<String, Object> context = Collections.synchronizedMap(new HashMap<String, Object>());
	public static List<ContextoListener> listeners = new ArrayList<>();
	
	public static Executor getExecutor() {
		return getAtributo(Constantes.EXECUTOR);
	}
	
	public static Notificador getNotificador() {
		return getAtributo(Constantes.NOTIFICADOR);
	}
	
	public static Logger getLogger() {
		return getAtributo(Constantes.LOGGER);
	}
	
	public static void inicarContexto(){
		putAtributo(Constantes.NOTIFICADOR, new Notificador());
		putAtributo(Constantes.EXECUTOR, new Executor());
		putAtributo(Constantes.LOGGER, new Logger());
	}
	
	public static void setAssinadorWindow(JFrame frame){
		putAtributo(Constantes.MAIN_WINDOW, frame);
	}
	
	public static JFrame getMainWindow(){
		return getAtributo(Constantes.MAIN_WINDOW);
	}
	
	public static <T> T getAtributo(String key){
		return (T) context.get(key);
	}
	
	public static <T> T getAtributo(Class<T> clazz, String key){
		return clazz.cast(context.get(key));
	}
	
	public static <T> T remove(String key){
		Object object = context.remove(key);
		notifyListener(key, object, false);
		return (T) object;
	}
	
	public static <T> void putAtributo(String key, T t){
		context.put(key, t);
		notifyListener(key, t, true);
	}
	
	public static void addListener(ContextoListener listener){
		listeners.add(listener);
	}
	
	private static void notifyListener(Object chave, Object valor, boolean novoAtributo){
		for (ContextoListener listener: listeners)
			if (novoAtributo)
				listener.novoAtributo(chave, valor);
			else
				listener.atributoRemovido(chave, valor);
	}
}
