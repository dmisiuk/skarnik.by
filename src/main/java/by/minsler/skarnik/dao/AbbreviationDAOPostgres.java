package by.minsler.skarnik.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import by.minsler.skarnik.beans.Abbreviation;
import by.minsler.skarnik.db.ConnectionInit;

public class AbbreviationDAOPostgres implements AbbreviationDAO {

	private static AbbreviationDAOPostgres inst;
	private static Connection connection;
	private static Logger logger = Logger
			.getLogger(AbbreviationDAOPostgres.class);

	private AbbreviationDAOPostgres() {
		connection = ConnectionInit.getConnection();
		logger.info("instantiate AbbreviationDAOPostgres");
	}

	synchronized public static AbbreviationDAOPostgres getInstance() {
		if (inst == null) {
			inst = new AbbreviationDAOPostgres();
		}
		return inst;
	}

	public Abbreviation getAbbreviation(int id) {

		return new Abbreviation();
	}

	public Abbreviation getAbbreviation(String shortName) {
		return new Abbreviation();
	}

	public boolean addAbbreviation(Abbreviation abbr) {
		int result = 0;
		try {
			Statement addStatement = connection.createStatement();
			String query = "insert into abbreviations(short_name,full_name) values('"
					+ abbr.getShortName() + "','" + abbr.getFullName() + "')";
			result = addStatement.executeUpdate(query);
			result = 1;
		} catch (SQLException e) {
			logger.error("sql: add abbreviation : " + e);
		}
		if (result == 1) {
			logger.debug("added abbreviation");
			return true;
		}
		return false;
	}

	public ArrayList<Abbreviation> getAbbreviations() {
		ArrayList<Abbreviation> list = new ArrayList<Abbreviation>();
		try {
			Statement getAllStatement = connection.createStatement();
			ResultSet result = getAllStatement
					.executeQuery("select * from abbreviations");
			while (result.next()) {
				Abbreviation abbr = new Abbreviation();
				abbr.setId(result.getInt("id"));
				abbr.setShortName(result.getString("short_name"));
				abbr.setFullName(result.getString("full_name"));
				list.add(abbr);
			}
		} catch (SQLException e) {
			logger.error("sql exception: " + e);
		}
		return list;
	}

	public int deleteAll() {
		int result = 0;
		String query = "truncate table abbreviations";
		try {
			Statement deleteAllStatement = connection.createStatement();
			result = deleteAllStatement.executeUpdate(query);
			logger.info("table truncated");
		} catch (SQLException e) {
			logger.error("sql: delete all abbreviation: " + e);
		}
		return result;
	}

	public int delete(int id) {
		int result = 0;
		String query = "delete from abbreviations where id=" + id;
		try {
			Statement deleteStatement = connection.createStatement();
			result = deleteStatement.executeUpdate(query);
			logger.info("deleted " + result + " abbreviation(s)");
		} catch (SQLException e) {
			logger.error("sql: delete abbreviation: " + e);
		}
		return result;
	}
}