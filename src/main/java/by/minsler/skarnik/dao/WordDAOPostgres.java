package by.minsler.skarnik.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import by.minsler.skarnik.beans.Word;
import by.minsler.skarnik.db.ConnectionInit;

public class WordDAOPostgres implements WordDAO {

	private static Logger logger = Logger.getLogger(WordDAOPostgres.class);

	private static WordDAOPostgres inst;
	private Connection connection = ConnectionInit.getConnection();
	private String addWordQuery = "insert into words(id, text) values(?, ?)";
	private String getWordQuery = "select * from words where id= ?";
	private PreparedStatement addWordStatement;
	private PreparedStatement getWordStatement;

	private WordDAOPostgres() {
		connection = ConnectionInit.getConnection();
		logger.info("instantiate WordDaoPostgres");
		createStatements();
		logger.info("created sql statements");
	}

	private void createStatements() {
		try {
			addWordStatement = connection.prepareStatement(addWordQuery);
			getWordStatement = connection.prepareStatement(getWordQuery);
		} catch (SQLException e) {
			logger.error("creating statements: " + e);
		}

	}

	synchronized public static WordDAOPostgres getInstance() {
		if (inst == null) {
			inst = new WordDAOPostgres();
		}
		return inst;
	}

	@Override
	public ArrayList<Word> getWords() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Word getWord(int id) {
		Word word = null;
		try {
			getWordStatement.setInt(1, id);
			ResultSet result = getWordStatement.executeQuery();
			if (result.next()) {
				word = new Word();
				word.setId(result.getInt("id"));
				word.setText(result.getString("text"));
			}
		} catch (SQLException e) {
			logger.error("get word from db: " + e);
		}

		return word;
	}

	@Override
	public boolean addWord(Word word) {
		int result = 0;

		try {
			addWordStatement.setInt(1, word.getId());
			addWordStatement.setString(2, word.getText());
			result = addWordStatement.executeUpdate();
		} catch (SQLException e) {
			logger.error("added word to db: " + e);
		}

		if (result == 1) {
			return true;
		}
		return false;
	}

	@Override
	public int deleteWord(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAllWord() {
		// TODO Auto-generated method stub
		return 0;
	}

}
