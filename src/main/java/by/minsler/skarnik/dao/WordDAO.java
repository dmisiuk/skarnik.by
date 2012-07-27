package by.minsler.skarnik.dao;

import java.util.ArrayList;

import by.minsler.skarnik.beans.Word;

public interface WordDAO {

	ArrayList<Word> getWords();

	Word getWord(int id);

	boolean addWord(Word word);

	int deleteWord(int id);

	int deleteAllWord();

}
