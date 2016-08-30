package br.com.assinador.agente.gui.panel.conf.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.JButton;

import br.com.assinador.agente.gui.panel.JListUtil;
import br.com.assinador.agente.gui.panel.conf.PreferenciasComponentsVO;
import br.com.assinador.agente.gui.popup.NovoTipoArquivoConhecidoPopup;

public class AddRemoveTipoActionListener implements ActionListener {

	private PreferenciasComponentsVO componentsVO;
	
	public AddRemoveTipoActionListener(PreferenciasComponentsVO componentsVO) {
		this.componentsVO = componentsVO;
	}

	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		if (button.getName().equals("Adicionar"))
			adicionarTipos();
		else if (button.getName().equals("Remover"))
			removerTipos();
			
	}
	
	public void adicionarTipos(){
		NovoTipoArquivoConhecidoPopup popup = new NovoTipoArquivoConhecidoPopup(componentsVO.getDialog());
		popup.open();
		
		if (popup.isOk()){
			String tiposDigitados = popup.getTiposDigitados();
			String[] tipos = tiposDigitados.split("[,\\s+|.,]+");
			if (tipos.length != 0 && !(tipos.length == 1 && tipos[0].equals(""))){
				JListUtil<String> listUtil = new JListUtil<>(componentsVO.getjListTiposArquivos());
				listUtil.addValues(tipos);
			}
		}
	}
	
	public void removerTipos(){
		JListUtil<String> listUtil = new JListUtil<>(componentsVO.getjListTiposArquivos());
		Set<String> selectedElements = listUtil.getSelectedElements();
		listUtil.removeValues(selectedElements);
	}

}
