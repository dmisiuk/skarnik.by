package by.minsler.skarnik.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import by.minsler.skarnik.beans.Node;
import by.minsler.skarnik.db.ConnectionInit;

public class NodeDAOPostgres implements NodeDAO {

	private static Logger logger = Logger.getLogger(NodeDAOPostgres.class);

	private static NodeDAOPostgres inst;
	private Connection connection = ConnectionInit.getConnection();
	private String addNodeQuery = "INSERT INTO nodes(id,text,tag_id,line_id) VALUES(?, ?, ?, ?)";
	private String getNodeQuery = "SELECT * FROM NODES WHERE id = ?";
	private PreparedStatement addNodeStatement;
	private PreparedStatement getNodeStatement;

	private NodeDAOPostgres() {
		connection = ConnectionInit.getConnection();
		logger.info("instantiate NodeDaoPostgres");
		createStatements();
		logger.info("created sql statements");
	}

	private void createStatements() {
		try {
			addNodeStatement = connection.prepareStatement(addNodeQuery);
			getNodeStatement = connection.prepareStatement(getNodeQuery);
		} catch (SQLException e) {
			logger.error("creating statements: " + e);
		}

	}

	synchronized public static NodeDAOPostgres getInstance() {
		if (inst == null) {
			inst = new NodeDAOPostgres();
		}
		return inst;
	}

	@Override
	public ArrayList<Node> getNodes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node getNode(int id) {
		Node node = null;

		try {
			getNodeStatement.setInt(1, id);
			ResultSet result = addNodeStatement.executeQuery();
			if (result.next()) {
				node = new Node();
				node.setId(result.getInt("id"));
				node.setText(result.getString("text"));
				node.setTagId(result.getInt("tag_id"));
				node.setLineId(result.getInt("line_id"));
			}

		} catch (SQLException e) {
			logger.error("getNode from db by id: " + e);
		}

		return node;
	}

	@Override
	public boolean addNode(Node node) {

		int result = 0;

		try {
			addNodeStatement.setInt(1, node.getId());
			addNodeStatement.setString(2, node.getText());
			addNodeStatement.setInt(3, node.getTagId());
			addNodeStatement.setInt(4, node.getLineId());
			result = addNodeStatement.executeUpdate();

		} catch (SQLException e) {
			logger.error("addNode to db: " + e);
		}
		if (result == 1) {
			return true;
		}
		return false;
	}

	@Override
	public int deleteNode(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAllNode() {
		// TODO Auto-generated method stub
		return 0;
	}

}