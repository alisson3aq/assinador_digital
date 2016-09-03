package br.com.assinador.agente.vo;

import java.io.File;
import java.util.Collection;

import br.com.assinador.agente.vo.converter.StringPathToFileConverter;
import br.com.mvp.view.ModelCollector;
import br.com.mvp.view.annotation.Checkbox;
import br.com.mvp.view.annotation.Model;
import br.com.mvp.view.annotation.Text;
import br.com.mvp.view.annotation.ViewList;

@Model
public class AssinadorVO {

	@ViewList(fieldName="jListDocumentosSelecionados", collectionType=ModelCollector.VALUES)
	private Collection<File> documentos;
	
	@Text(fieldName="txtDestino", converter=StringPathToFileConverter.class)
	private File destino;
	
	@Checkbox(fieldName="chckbxRemoverArquivosOriginais")
	private boolean removerDocumentos;
	
	public Collection<File> getDocumentos() {
		return documentos;
	}
	public void setDocumentos(Collection<File> documentos) {
		this.documentos = documentos;
	}
	public File getDestino() {
		return destino;
	}
	public void setDestino(File destino) {
		this.destino = destino;
	}
	public boolean isRemoverDocumentos() {
		return removerDocumentos;
	}
	public void setRemoverDocumentos(boolean removerDocumentos) {
		this.removerDocumentos = removerDocumentos;
	}
}
