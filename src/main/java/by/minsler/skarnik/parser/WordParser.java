package by.minsler.skarnik.parser;

import org.apache.log4j.Logger;

import by.minsler.skarnik.beans.Word;
import by.minsler.skarnik.dao.WordDAO;
import by.minsler.skarnik.dao.WordDAOPostgres;

public class WordParser {

	private static Logger logger = Logger.getLogger(WordParser.class);

	public static int getWordId(String line) {
		int result = -1;
		String text = line.trim();
		Word word = new Word(true);
		word.setText(text);
		WordDAO wordDao = WordDAOPostgres.getInstance();
		wordDao.addWord(word);
		return word.getId();
	}

}
