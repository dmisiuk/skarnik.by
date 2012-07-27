package by.minsler.skarnik.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import by.minsler.skarnik.beans.Card;
import by.minsler.skarnik.db.ConnectionInit;

public class CardDAOPostgres implements CardDAO {

	private static Logger logger = Logger.getLogger(CardDAOPostgres.class);

	private static CardDAOPostgres inst;
	private Connection connection = ConnectionInit.getConnection();
	private String addCardQuery = "INSERT INTO CARDS(id, word_id, translate_id) VALUES(?, ?,?)";
	private String getCardQuery = "SELECT * FROM CARDS WHERE id = ?";
	private PreparedStatement addCardStatement;
	private PreparedStatement getCardStatement;

	private CardDAOPostgres() {
		connection = ConnectionInit.getConnection();
		logger.info("instantiate CardDaoPostgres");
		createStatements();
		logger.info("created sql statements");
	}

	private void createStatements() {
		try {
			addCardStatement = connection.prepareStatement(addCardQuery);
			getCardStatement = connection.prepareStatement(getCardQuery);
		} catch (SQLException e) {
			logger.error("creating statements: " + e);
		}

	}

	synchronized public static CardDAOPostgres getInstance() {
		if (inst == null) {
			inst = new CardDAOPostgres();
		}
		return inst;
	}

	@Override
	public ArrayList<Card> getCards() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Card getCard(int id) {
		Card card = null;

		try {
			getCardStatement.setInt(1, id);
			ResultSet result = addCardStatement.executeQuery();
			if (result.next()) {
				card = new Card();
				card.setId(result.getInt("id"));
				card.setTranslateId(result.getInt("translate_id"));
				card.setWordId(result.getInt("word_id"));
			}

		} catch (SQLException e) {
			logger.error("getCard from db by id: " + e);
		}

		return card;
	}

	@Override
	public boolean addCard(Card card) {

		int result = 0;

		try {
			addCardStatement.setInt(1, card.getId());
			addCardStatement.setInt(2, card.getWordId());
			addCardStatement.setInt(3, card.getTranslateId());
			result = addCardStatement.executeUpdate();

		} catch (SQLException e) {
			logger.error("addCard to db: " + e);
		}
		if (result == 1) {
			return true;
		}
		return false;
	}

	@Override
	public int deleteCard(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAllCard() {
		// TODO Auto-generated method stub
		return 0;
	}

}
