package by.minsler.skarnik.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import by.minsler.skarnik.beans.Line;
import by.minsler.skarnik.db.ConnectionInit;

public class LineDAOPostgres implements LineDAO {

	private static Logger logger = Logger.getLogger(LineDAOPostgres.class);

	private static LineDAOPostgres inst;
	private Connection connection = ConnectionInit.getConnection();
	private String addLineQuery = "INSERT INTO LINES(id, level, translate_id) VALUES(?,?,?)";
	private String getLineQuery = "SELECT * FROM LINES WHERE id = ?";
	private PreparedStatement addLineStatement;
	private PreparedStatement getLineStatement;

	private LineDAOPostgres() {
		connection = ConnectionInit.getConnection();
		logger.info("instantiate LineDaoPostgres");
		createStatements();
		logger.info("created sql statements");
	}

	private void createStatements() {
		try {
			addLineStatement = connection.prepareStatement(addLineQuery);
			getLineStatement = connection.prepareStatement(getLineQuery);
		} catch (SQLException e) {
			logger.error("creating statements: " + e);
		}

	}

	synchronized public static LineDAOPostgres getInstance() {
		if (inst == null) {
			inst = new LineDAOPostgres();
		}
		return inst;
	}

	@Override
	public ArrayList<Line> getLines() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Line getLine(int id) {
		Line line = null;

		try {
			getLineStatement.setInt(1, id);
			ResultSet result = addLineStatement.executeQuery();
			if (result.next()) {
				line = new Line();
				line.setId(result.getInt("id"));
				line.setTranslateId(result.getInt("translate_id"));
				line.setLevel(result.getInt("level"));
			}

		} catch (SQLException e) {
			logger.error("getLine from db by id: " + e);
		}

		return line;
	}

	@Override
	public boolean addLine(Line line) {

		int result = 0;

		try {
			addLineStatement.setInt(1, line.getId());
			addLineStatement.setInt(2, line.getLevel());
			addLineStatement.setInt(3, line.getTranslateId());
			result = addLineStatement.executeUpdate();

		} catch (SQLException e) {
			logger.error("addLine to db: " + e);
		}
		if (result == 1) {
			return true;
		}
		return false;
	}

	@Override
	public int deleteLine(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAllLine() {
		// TODO Auto-generated method stub
		return 0;
	}

}