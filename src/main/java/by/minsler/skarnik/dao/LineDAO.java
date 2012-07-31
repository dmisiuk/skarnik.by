package by.minsler.skarnik.dao;

import java.util.ArrayList;

import by.minsler.skarnik.beans.Line;

public interface LineDAO {

	ArrayList<Line> getLines();

	Line getLine(int id);

	boolean addLine(Line Line);

	int deleteLine(int id);

	int deleteAllLine();
}
