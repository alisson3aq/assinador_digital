package br.com.assinador.agente.vo.converter;

import java.io.File;

import br.com.mvp.view.converter.Converter;

public class StringPathToFileConverter implements Converter<File, String> {

	@Override
	public File fromView(String value) {
		File f = new File(value);
		return f;
	}

	@Override
	public String fromModel(File value) {
		return value.getAbsolutePath();
	}

}
