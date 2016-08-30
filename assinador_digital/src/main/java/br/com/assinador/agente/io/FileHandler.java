package br.com.assinador.agente.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;

@SuppressWarnings("unchecked")
public class FileHandler {

	public void writeToFile(File dir, String nomeArquivo, FileExtension extEnum, byte[] conteudo) throws IOException{
		String ext = extEnum == null ? "" : "." + extEnum.getExt();
		
		File destino = new File(dir, nomeArquivo + ext);
		Files.write(destino.toPath(), conteudo);
	}
	
	public void writeToFile(File dir, String nomeArquivo, byte[] conteudo) throws IOException{
		writeToFile(dir, nomeArquivo, null, conteudo);
	}
	
	public void writeToFile(File dir, String nomeArquivo, Serializable obj) throws IOException{
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(obj);
		oos.flush();
		oos.close();
		
		writeToFile(dir, nomeArquivo, FileExtension.SER, baos.toByteArray());
	}
	
	public byte[] readFile(File file) throws IOException{
		byte[] conteudoArquivo = Files.readAllBytes(file.toPath());
		return conteudoArquivo;
	}
	
	public <T extends Serializable> T readObjectFile(File file) throws IOException{
		try{
			byte[] conteudoArquivo = Files.readAllBytes(file.toPath());
			ByteArrayInputStream bais = new ByteArrayInputStream(conteudoArquivo);
			ObjectInputStream ois = new ObjectInputStream(bais);
			return (T) ois.readObject();
		}catch (Exception e) {
			return null;
		}
	}
	
	public void copyFiles(File src, File dest) throws IOException{
		Path srcPath = src.toPath();
		Path destPath = dest.toPath();
		
		if (!Files.exists(destPath))
			Files.createFile(destPath);
		
		Files.copy(srcPath, destPath);
	}
	
	public File createDirIfNotExists(String path){
		File dir = new File(path);
		if (!dir.exists() 
				|| (dir.exists() && !dir.isDirectory()))
			dir.mkdirs();
		
		return dir;
	}
}
