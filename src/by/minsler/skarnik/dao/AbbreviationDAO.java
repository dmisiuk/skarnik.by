package by.minsler.skarnik.dao;

import by.minsler.skarnik.beans.Abbreviation;

public interface AbbreviationDAO{

	Abbreviation getAbbreviation(int id);
	Abbreviation getAbbreviation(String shortName);
	boolean addAbbreviation(Abbreviation abbreviation);
}