package br.com.assinador.agente;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.assinador.agente.io.FileHandler;

public class Logger {

	private static final File tempDir = new File(System.getProperty("java.io.tmpdir"));
	private static final String LINE_BREAK = "\n";
	private static final String LOG_DIR = "quality/assinador/log";
	private static final Object LOCK = new Object();
	
	private long maxFileSize = (long) Math.pow(1000.0, 2);
	private DateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm");

	Logger(){
		log("Start");
	}
	
	public void log(Throwable e){
		StringWriter sWriter = new StringWriter();
		e.printStackTrace(new PrintWriter(sWriter));
		
		log(sWriter.toString());
	}
	
	public void log(String message, Throwable e){
		log(message);
		log(e);
	}
	
	public void log(String message){
		try {
			File logFile = validateFile();
			Writer fWriter = new FileWriter(logFile, true);
			fWriter.write(message + LINE_BREAK);
			fWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private File validateFile() throws IOException{
		File logDir = new File(tempDir, LOG_DIR);
		synchronized (LOCK) {
			
			if (!logDir.exists())
				logDir.mkdirs();
			
			File logFile = new File(logDir, "agente.log");
			
			if (!logFile.exists())
				logFile.createNewFile();
			
			if (logFile.length() >= maxFileSize){
				File oldLog = new File(logDir, "agente_" + sdf.format(new Date()) + ".log");
				
				FileHandler fh = new FileHandler();
				fh.copyFiles(logFile, oldLog);
				
				logFile.delete();
			}
		}
		return new File(logDir, "agente.log");
	}
	
	
}
