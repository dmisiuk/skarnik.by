package by.minsler.skarnik.dao;

import by.minsler.skarnik.beans.Abbreviation;
import java.util.ArrayList;

public interface AbbreviationDAO{

	Abbreviation getAbbreviation(int id);
	Abbreviation getAbbreviation(String shortName);
	boolean addAbbreviation(Abbreviation abbreviation);
	ArrayList<Abbreviation> getAbbreviations();
	int deleteAll();
	int delete(int id);
}