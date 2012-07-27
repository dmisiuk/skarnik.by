package by.minsler.skarnik.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.minsler.skarnik.beans.Card;
import by.minsler.skarnik.beans.Translate;
import by.minsler.skarnik.dao.CardDAO;
import by.minsler.skarnik.dao.CardDAOPostgres;

public class CardParser {

	private static Logger logger = Logger.getLogger(CardParser.class);
	private File file;
	BufferedReader br;

	public CardParser(File file) {
		this.file = file;
		init();
	}

	private void init() {
		try {
			FileInputStream fileInput = new FileInputStream(file);
			InputStreamReader reader = new InputStreamReader(fileInput, "utf-8");
			br = new BufferedReader(reader);
			logger.info("create bufferred reader for parse; encoding of filereader is "
					+ reader.getEncoding());
		} catch (FileNotFoundException e) {
			logger.error("init bufferedreader: " + e);
		} catch (IOException e) {
			logger.error("init bufferedreader: " + e);
		}

	}

	public void parse() {
		CardDAO cardDao = CardDAOPostgres.getInstance();

		List<Integer> wordIds = new ArrayList<Integer>();

		String l;
		try {
			while ((l = br.readLine()) != null) {
				Translate translate = new Translate(true);
				int tranlateId = translate.getId();
				if (!(l.trim().equals("") || l.trim().startsWith("#"))) {
					while ((l = br.readLine()) != null && !l.startsWith("\t")) {
						wordIds.add(WordParser.getWordId(l));
					}
					while ((l = br.readLine()) != null && l.startsWith("\t")) {
						LineParser.parse(l, tranlateId);
					}
					for (Integer wordId : wordIds) {
						Card card = new Card(true);
						card.setWordId(wordId);
						card.setTranslateId(tranlateId);
						cardDao.addCard(card);
					}

				}
			}
		} catch (IOException e) {
			logger.error("readLine(): " + e);
		}

	}

	public void destroy() {
		if (br != null) {
			try {
				br.close();
				logger.info("bufferedreader from parser closed");
			} catch (IOException e) {
				logger.error("error closing bufferred: " + e);
			}
		}
	}

}
