package by.minsler.skarnik.dao;

import java.util.ArrayList;

import by.minsler.skarnik.beans.Translate;

public interface TranslateDAO {

	ArrayList<Translate> getTranslates();

	Translate getTranslate(int id);

	boolean addTranslate(Translate translate);

	int deleteTranslate(int id);

	int deleteAllTranslate();

}
