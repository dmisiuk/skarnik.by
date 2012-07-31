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
import by.minsler.skarnik.dao.TranslateDAO;
import by.minsler.skarnik.dao.TranslateDAOPostgres;

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
		TranslateDAO translateDao = TranslateDAOPostgres.getInstance();
		LineParser lineParser = new LineParser();
		WordParser wordParser = new WordParser();

		Translate translate = new Translate(true);
		List<Integer> wordIds = new ArrayList<Integer>();
		boolean end = false;

		String l;
		try {
			while ((l = br.readLine()) != null) {
				if (!(l.trim().equals("") || l.trim().startsWith("#"))) {

					if (!l.startsWith("\t")) {

						if (end) {

							translateDao.addTranslate(translate);
							for (Integer wordId : wordIds) {
								Card card = new Card(true);
								card.setWordId(wordId);
								card.setTranslateId(translate.getId());
								cardDao.addCard(card);
							}

							wordIds.clear();
							translate = new Translate(true);
							end = false;
						}

						wordIds.add(wordParser.getWordId(l));

					} else {
						end = true;
						lineParser.parse(l, translate.getId());
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
