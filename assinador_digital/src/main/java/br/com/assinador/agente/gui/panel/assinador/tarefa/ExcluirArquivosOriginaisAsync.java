package br.com.assinador.agente.gui.panel.assinador.tarefa;

import java.io.File;
import java.util.Map;
import java.util.Set;

import br.com.assinador.agente.Constantes;
import br.com.assinador.agente.Contexto;
import br.com.assinador.agente.multithread.ExecucaoException;
import br.com.assinador.agente.multithread.Protocolo;
import br.com.assinador.agente.multithread.Tarefa;
import br.com.assinador.agente.multithread.TipoTarefa;

public class ExcluirArquivosOriginaisAsync extends Tarefa<Void> {

	private Protocolo<?> dependente;
	
	public ExcluirArquivosOriginaisAsync(Protocolo<?> dependente) {
		super(dependente, TipoTarefa.IO);
		this.dependente = dependente;
	}

	@Override
	protected Void executar(Map<Object, Object> contextoTarefa) throws ExecucaoException {
		if (!dependente.getResultado().hasErrosExecucao()){
			Set<File> arquivosSelecionados = Contexto.getAtributo(Constantes.DOCUMENTOS_ASSINATURA_SELECIONADOS);
			for (File f: arquivosSelecionados){
				try{
					if (f.canWrite())
						f.delete();
				}catch(Exception e){
					throw new ExecucaoException("Erro ao excluir arquivos \n" + e.getMessage(), e, true);
				}
			}
		}
		return null;
	}

}
