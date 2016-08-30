package br.com.assinador.agente.io;

import java.util.Arrays;
import java.util.List;

public enum FileExtension {

	GENERIC("br/com/quality/assinador/agente/gui/icon/file.gif", "file"),
	SER("", "ser"),
	PDF("br/com/quality/assinador/agente/gui/icon/pdf.png", "pdf"),
	DOC("br/com/quality/assinador/agente/gui/icon/word.png", "png"),
	XLS("br/com/quality/assinador/agente/gui/icon/xls.png", "xls"),
	XML("br/com/quality/assinador/agente/gui/icon/xml.png", "xml"),
	JPG("br/com/quality/assinador/agente/gui/icon/jpg.png", "jpg"),
	P7S("br/com/quality/assinador/agente/gui/icon/p7s.jpeg", "p7s"),
	;
	
	private String iconResourcePath;
	private String ext;
	
	private FileExtension(String resourcePath, String ext) {
		this.iconResourcePath = resourcePath;
		this.ext = ext;
	}
	
	public String getIconResourcePath() {
		return iconResourcePath;
	}
	
	public String getExt() {
		return ext;
	}
	
	public static List<FileExtension> getTiposConhecidos(){
		return Arrays.asList(
				PDF,
				DOC,
				XLS,
				XML,
				JPG
				);				
	}
	
	public static FileExtension fromFileName(String fileName){
		if (fileName.contains("pdf"))
			return PDF;
		else if (fileName.contains("doc"))
			return DOC;
		else if (fileName.contains("xls"))
			return XLS;
		else if (fileName.contains("xml"))
			return XML;
		else if (fileName.contains("jpg") || fileName.contains("jpeg"))
			return JPG;
		else 
			return GENERIC;
	}
}
