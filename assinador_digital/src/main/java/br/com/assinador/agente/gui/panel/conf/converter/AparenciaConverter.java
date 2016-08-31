package br.com.assinador.agente.gui.panel.conf.converter;

import br.com.assinador.agente.gui.LookAndFeelUtil;
import br.com.assinador.agente.gui.LookAndFeelUtil.WrapperLookAndFeelInfo;
import br.com.mvp.view.converter.Converter;

public class AparenciaConverter implements Converter<String, WrapperLookAndFeelInfo> {

	@Override
	public String fromView(WrapperLookAndFeelInfo value) {
		return value.getInfo().getClassName();
	}

	@Override
	public WrapperLookAndFeelInfo fromModel(String value) {
		return new LookAndFeelUtil().getWrapperInfoByClassName(value);
	}

}
