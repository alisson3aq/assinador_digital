package br.com.assinador.agente.gui;

public enum FileExtensionIcon {

	GENERIC("br/com/quality/assinador/agente/gui/icon/file.gif"),
	PDF("br/com/quality/assinador/agente/gui/icon/pdf.png"),
	DOC("br/com/quality/assinador/agente/gui/icon/word.png"),
	XLS("br/com/quality/assinador/agente/gui/icon/xls.png"),
	XML("br/com/quality/assinador/agente/gui/icon/xml.png"),
	JPG("br/com/quality/assinador/agente/gui/icon/jpg.png"),
	;
	
	private String resourcePath;
	
	private FileExtensionIcon(String resourcePath) {
		this.resourcePath = resourcePath;
	}
	
	public String getResourcePath() {
		return resourcePath;
	}
	
	public static FileExtensionIcon fromFileName(String fileName){
		int pontoIndex = fileName.lastIndexOf(".");
		
		if (pontoIndex != -1)
			fileName = fileName.toLowerCase().substring(pontoIndex, fileName.length());
		else
			return GENERIC;
			
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
