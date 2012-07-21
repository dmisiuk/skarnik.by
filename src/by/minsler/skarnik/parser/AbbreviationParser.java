package by.minsler.skarnik.parser;

import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import by.minsler.skarnik.beans.Abbreviation;
import org.apache.log4j.Logger;

public class AbbreviationParser{

	private static Logger logger = Logger.getLogger(AbbreviationParser.class);
	private BufferedReader br;
	private File file;
	private Abbreviation nextAbbr;
	private boolean endFile = false;

	public AbbreviationParser(File file){
		this.file = file;
		init();
	}

	private void init() {
		try {
			FileInputStream fileInput = new FileInputStream(file);
			InputStreamReader reader = new InputStreamReader(fileInput, "utf-8");
			br = new BufferedReader(reader);
			logger.info("create bufferred reader for parse; encoding of filereader is " + reader.getEncoding());
		} catch(FileNotFoundException e){
			logger.error("init bufferedreader: " + e);
		} catch(IOException e){
			logger.error("init bufferedreader: " + e);
		}
	}

	private void parse(){
		logger.debug("parse next abbreviation");
		
		String l = null;
		String shortName = null;
		String fullName = null;
		try{
			while((l = br.readLine()) != null){
				if(l.trim().equals("") || l.trim().startsWith("#")){
					//skeep;
				} else{
					if(l.startsWith("\t")){
						fullName = l.trim();
						break;
					} else{
						shortName = l.trim();
					}
				}
			}
		} catch(IOException e){
			logger.error("parse file: readLine: "  + e);
		}
		logger.debug("parser: shortName: " + shortName + " , fullName: " + fullName);
		if(shortName != null && fullName != null){
			Abbreviation abbr = new Abbreviation();
			abbr.setShortName(shortName);
			abbr.setFullName(fullName);
			nextAbbr = abbr;
		} else{
			endFile = true;
			nextAbbr = null;
		}
		
	}	

	public boolean next(){
		if(endFile != true){
			parse();
		}
		if(nextAbbr != null){
			return true;
		}
		return false;
	}

	public Abbreviation getAbbreviation(){
		return nextAbbr;
	}

	public void close(){
		if(br != null){
			try{
				br.close();
				logger.info("bufferedreader from parser closed");
			} catch(IOException e){
				logger.error("error closing bufferred: " + e);
			}
		}
	}

}