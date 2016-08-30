package br.com.assinador.agente.gui;

import java.awt.Component;
import java.io.File;
import java.util.Collection;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import br.com.assinador.agente.io.FileExtension;

public class FileChooser {

	private JFileChooser jfc = new JFileChooser();
	
	public static FileChooser create(){
		FileChooser fileChooser = new FileChooser();
		return fileChooser;
	}
	
	public FileChooser directoriesOnly(){
		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		return this;
	}
	
	public FileChooser withMultipleSelection(){
		jfc.setMultiSelectionEnabled(true);
		return this;
	}
	
	public FileChooser defaultDir(File file){
		jfc.setCurrentDirectory(file);
		return this;
	}
	
	public FileChooser filesOnly(){
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		return this;
	}
	
	public FileChooser choosableExtension(String description, FileExtension ext, boolean acceptOtherExt){
		jfc.setAcceptAllFileFilterUsed(acceptOtherExt);
		FileFilter fileFilter = new FileNameExtensionFilter(description, ext.getExt());
		jfc.addChoosableFileFilter(fileFilter);
		return this;
	}
	
	public FileChooser choosableExtensions(String description, Collection<String> extensions, boolean acceptOtherExt){
		jfc.setAcceptAllFileFilterUsed(acceptOtherExt);
		
		int i = 0;
		String[] extArray = new String[extensions.size()];
		for (String ext: extensions)
			extArray[i++] = ext;
		
		FileFilter fileFilter = new FileNameExtensionFilter(description, extArray);
		jfc.addChoosableFileFilter(fileFilter);
		return this;
	}
	
	public File getSelectedFile(Component parent){
		jfc.setMultiSelectionEnabled(true);
		File[] selectedFiles = getSelectedFiles(parent);
		if (selectedFiles != null)
			return selectedFiles[0];
		else
			return null;
	}
	
	public File[] getSelectedFiles(Component parent){
		int option = jfc.showOpenDialog(parent);
		if (option == JFileChooser.APPROVE_OPTION)
			return jfc.getSelectedFiles();
		else
			return null;
	}
	
	public File getSelectedFile(){
		return getSelectedFile(null);
	}
	
}
