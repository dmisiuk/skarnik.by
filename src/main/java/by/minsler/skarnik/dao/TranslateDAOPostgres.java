package by.minsler.skarnik.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import by.minsler.skarnik.beans.Translate;
import by.minsler.skarnik.db.ConnectionInit;

public class TranslateDAOPostgres implements TranslateDAO {

	private static Logger logger = Logger.getLogger(TranslateDAOPostgres.class);

	private static TranslateDAOPostgres inst;
	private Connection connection = null;
	private String addTranslateQuery = "insert into translates (id) values(?)";
	private String getTranslateQuery = "select * from translates where id = ?";
	private PreparedStatement addTranslateStatement;
	private PreparedStatement getTranslateStatement;

	private TranslateDAOPostgres() {
		connection = ConnectionInit.getConnection();
		logger.info("instantiate TranslateDaoPostgres");
		createStatements();
		logger.info("created sql statements");
	}

	private void createStatements() {
		try {
			addTranslateStatement = connection
					.prepareStatement(addTranslateQuery);
			getTranslateStatement = connection
					.prepareStatement(getTranslateQuery);
		} catch (SQLException e) {
			logger.error("creating statements: " + e);
		}

	}

	synchronized public static TranslateDAOPostgres getInstance() {
		if (inst == null) {
			inst = new TranslateDAOPostgres();
		}
		return inst;
	}

	@Override
	public ArrayList<Translate> getTranslates() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Translate getTranslate(int id) {
		Translate translate = null;

		try {
			getTranslateStatement.setInt(1, id);
			ResultSet result = getTranslateStatement.executeQuery();
			if (result.next()) {
				translate = new Translate();
				translate.setId(id);
			}
		} catch (SQLException e) {
			logger.error("get translate from db: " + e);
		}
		return translate;
	}

	@Override
	public boolean addTranslate(Translate translate) {
		int result = 0;

		try {
			addTranslateStatement.setInt(1, translate.getId());
			result = addTranslateStatement.executeUpdate();
		} catch (SQLException e) {
			logger.error("add translate to db: " + e);
		}

		if (result == 1) {
			return true;
		}

		return false;
	}

	@Override
	public int deleteTranslate(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAllTranslate() {
		// TODO Auto-generated method stub
		return 0;
	}

}
