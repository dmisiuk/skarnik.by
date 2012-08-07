package by.minsler.skarnik.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.minsler.skarnik.beans.Article;
import by.minsler.skarnik.beans.Def;
import by.minsler.skarnik.beans.Key;
import by.minsler.skarnik.db.ConnectionInit;

public class ArticleKeyDefDAOPostgres implements ArticleKeyDefDAO {

	private static Logger logger = Logger
			.getLogger(ArticleKeyDefDAOPostgres.class);

	private static ArticleKeyDefDAOPostgres inst;
	private Connection connection = null;
	private String addArticleQuery = "INSERT INTO article(id, key_id, def_id) VALUES(?,?,?)";
	private String addKeyQuery = "INSERT INTO key(id, text) VALUES(?,?)";
	private String addDefQuery = "INSERT INTO def(id, text) VALUES(?,?)";
	private String getArticleQuery = "SELECT * FROM article WHERE id = ?";
	private String getArticleByKeyIdQuery = "SELECT * FROM article WHERE key_id = ?";
	private String getKeyQuery = "SELECT * FROM key WHERE id = ?";
	private String getKeyByNameQuery = "SELECT * FROM key WHERE text ilike ?";
	private String getKeyByNameLimitQuery = "SELECT * FROM key WHERE text ilike ? limit 10 ";
	private String getKeyStrictQuery = "SELECT * FROM key WHERE text=?";
	private String getDefQuery = "SELECT * FROM def WHERE id = ?";
	private PreparedStatement addArticleStatement;
	private PreparedStatement getArticleStatement;
	private PreparedStatement getArticleByKeyIdStatement;
	private PreparedStatement addKeyStatement;
	private PreparedStatement getKeyStatement;
	private PreparedStatement getKeyByNameStatement;
	private PreparedStatement getKeyByNameLimitStatement;
	private PreparedStatement getKeyStrictStatement;
	private PreparedStatement addDefStatement;
	private PreparedStatement getDefStatement;

	private ArticleKeyDefDAOPostgres() {
		connection = ConnectionInit.getConnection();
		logger.info("instantiate ArticleKeyDefDaoPostgres");
		createStatements();
		logger.info("created sql statements");
	}

	private void createStatements() {
		try {
			addArticleStatement = connection.prepareStatement(addArticleQuery);
			getArticleStatement = connection.prepareStatement(getArticleQuery);
			getArticleByKeyIdStatement = connection
					.prepareStatement(getArticleByKeyIdQuery);
			addKeyStatement = connection.prepareStatement(addKeyQuery);
			getKeyStatement = connection.prepareStatement(getKeyQuery);
			getKeyByNameStatement = connection
					.prepareStatement(getKeyByNameQuery);
			getKeyByNameLimitStatement = connection
					.prepareStatement(getKeyByNameLimitQuery);
			getKeyStrictStatement = connection
					.prepareStatement(getKeyStrictQuery);

			addDefStatement = connection.prepareStatement(addDefQuery);
			getDefStatement = connection.prepareStatement(getDefQuery);
		} catch (SQLException e) {
			logger.error("creating statements: " + e);
		}

	}

	synchronized public static ArticleKeyDefDAOPostgres getInstance() {
		if (inst == null) {
			inst = new ArticleKeyDefDAOPostgres();
		}
		return inst;
	}

	@Override
	public int addDef(Def def) {
		int result = 0;

		try {
			addDefStatement.setInt(1, def.getId());
			addDefStatement.setString(2, def.getText());
			result = addDefStatement.executeUpdate();

		} catch (SQLException e) {
			logger.error("addDef to db: " + e);
		}

		return result;

	}

	@Override
	public Def getDef(int id) {

		Def def = null;

		try {
			getDefStatement.setInt(1, id);
			ResultSet result = getDefStatement.executeQuery();
			if (result.next()) {
				def = new Def();
				def.setId(result.getInt("id"));
				def.setText(result.getString("text"));
			}

		} catch (SQLException e) {
			logger.error("getDef from db by id: " + e);
		}

		return def;
	}

	@Override
	public int addKey(Key key) {
		int result = 0;

		try {
			addKeyStatement.setInt(1, key.getId());
			addKeyStatement.setString(2, key.getText());
			result = addKeyStatement.executeUpdate();

		} catch (SQLException e) {
			logger.error("addKey to db: " + e);
		}

		return result;
	}

	@Override
	public Key getKey(int id) {
		Key key = null;

		try {
			getKeyStatement.setInt(1, id);
			ResultSet result = getKeyStatement.executeQuery();
			if (result.next()) {
				key = new Key();
				key.setId(result.getInt("id"));
				key.setText(result.getString("text"));
			}

		} catch (SQLException e) {
			logger.error("getKey from db by id: " + e);
		}

		return key;
	}

	@Override
	public List<Key> getKey(String text) {
		ArrayList<Key> list = new ArrayList<Key>();
		String pattern = text + "%";
		try {
			getKeyByNameStatement.setString(1, pattern);
			ResultSet result = getKeyByNameStatement.executeQuery();
			while (result.next()) {
				Key key = new Key();
				key.setId(result.getInt("id"));
				key.setText(result.getString("text"));
				list.add(key);
			}

		} catch (SQLException e) {
			logger.error("getKey from db by name pattern: " + e);
		}

		return list;

	}

	@Override
	public int addArticle(Article article) {

		int result = 0;

		try {
			addArticleStatement.setInt(1, article.getId());
			addArticleStatement.setInt(2, article.getKeyId());
			addArticleStatement.setInt(3, article.getDefId());
			result = addArticleStatement.executeUpdate();

		} catch (SQLException e) {
			logger.error("addArticle to db: " + e);
		}

		return result;
	}

	@Override
	public Article getArticle(int id) {

		Article article = null;

		try {
			getArticleStatement.setInt(1, id);
			ResultSet result = getArticleStatement.executeQuery();
			if (result.next()) {
				article = new Article();
				article.setId(result.getInt("id"));
				article.setKeyId(result.getInt("key_id"));
				article.setDefId(result.getInt("def_id"));
			}

		} catch (SQLException e) {
			logger.error("getArticle from db by id: " + e);
		}

		return article;
	}

	@Override
	public Article getArticleByKeyId(int keyId) {
		Article article = null;

		try {
			getArticleByKeyIdStatement.setInt(1, keyId);
			ResultSet result = getArticleByKeyIdStatement.executeQuery();
			if (result.next()) {
				article = new Article();
				article.setId(result.getInt("id"));
				article.setKeyId(result.getInt("key_id"));
				article.setDefId(result.getInt("def_id"));
			}

		} catch (SQLException e) {
			logger.error("getArticle from db by id: " + e);
		}

		return article;
	}

	@Override
	public Key getKeyStrict(String text) {
		Key key = null;

		try {
			getKeyStrictStatement.setString(1, text);
			ResultSet result = getKeyStrictStatement.executeQuery();
			if (result.next()) {
				key = new Key();
				key.setId(result.getInt("id"));
				key.setText(result.getString("text"));
			}

		} catch (SQLException e) {
			logger.error("getKey from db by stricttext: " + e);
		}

		return key;
	}

	@Override
	public List<Key> getKeyLimit(String text) {
		ArrayList<Key> list = new ArrayList<Key>();
		String pattern = text + "%";
		try {
			getKeyByNameLimitStatement.setString(1, pattern);
			ResultSet result = getKeyByNameLimitStatement.executeQuery();
			while (result.next()) {
				Key key = new Key();
				key.setId(result.getInt("id"));
				key.setText(result.getString("text"));
				list.add(key);
			}

		} catch (SQLException e) {
			logger.error("getKey from db by name pattern.limit : " + e);
		}

		return list;
	}
}
