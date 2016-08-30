package br.com.assinador.agente.config;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.swing.UIManager;

import br.com.assinador.agente.io.FileExtension;

public class Configuracao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String diretorioDocumentosPreferido;
	private String diretorioDocumentosAssinadosPreferido;
	private String aparenciaPreferida;
	private Set<String> tiposConhecidos = new LinkedHashSet<>();
	
	public Configuracao(){
		createTiposConhecidosSet();
	}
	
	public String getAparenciaPreferida() {
		if (aparenciaPreferida == null)
			this.aparenciaPreferida = UIManager.getSystemLookAndFeelClassName();
		return aparenciaPreferida;
	}
	public void setAparenciaPreferida(String aparenciaPreferida) {
		this.aparenciaPreferida = aparenciaPreferida;
	}
	public String getDiretorioDocumentosPreferido() {
		if (diretorioDocumentosPreferido == null)
			diretorioDocumentosPreferido = getHomeDir();
		return diretorioDocumentosPreferido;
	}
	public void setDiretorioDocumentosPreferido(String diretorioDocumentosPreferido) {
		this.diretorioDocumentosPreferido = diretorioDocumentosPreferido;
	}
	public String getDiretorioDocumentosAssinadosPreferido() {
		if (diretorioDocumentosAssinadosPreferido == null)
			diretorioDocumentosAssinadosPreferido = getHomeDir();
		return diretorioDocumentosAssinadosPreferido;
	}
	public void setDiretorioDocumentosAssinadosPreferido(String diretorioDocumentosAssinadosPreferido) {
		this.diretorioDocumentosAssinadosPreferido = diretorioDocumentosAssinadosPreferido;
	}
	public Set<String> getTiposConhecidos() {
		if (tiposConhecidos == null || tiposConhecidos.isEmpty())
			createTiposConhecidosSet();
		return tiposConhecidos;
	}
	public void setTiposConhecidos(Set<String> tiposConhecidos) {
		this.tiposConhecidos = tiposConhecidos;
	}

	public void addTiposConhecidos(String[] tipos) {
		tiposConhecidos.addAll(Arrays.asList(tipos));
	}
	
	private void createTiposConhecidosSet(){
		tiposConhecidos = new LinkedHashSet<>();
		List<FileExtension> tiposConhecidosList = FileExtension.getTiposConhecidos();
		for (FileExtension fExt: tiposConhecidosList)
			tiposConhecidos.add(fExt.getExt());
	}
	
	private String getHomeDir(){
		return System.getProperty("user.home");
	}
}
