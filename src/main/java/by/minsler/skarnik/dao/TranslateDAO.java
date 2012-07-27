package by.minsler.skarnik.dao;

import java.util.ArrayList;

import by.minsler.skarnik.beans.Translate;
import by.minsler.skarnik.beans.Word;

public interface TranslateDAO {

	ArrayList<Translate> getTranslates();

	Word getTranslate(int id);

	boolean addTranslate(Translate translate);

	int deleteTranslate(int id);

	int deleteAllTranslate();

}
