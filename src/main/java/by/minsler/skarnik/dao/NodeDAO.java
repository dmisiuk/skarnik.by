package by.minsler.skarnik.dao;

import java.util.ArrayList;

import by.minsler.skarnik.beans.Node;

public interface NodeDAO {

	ArrayList<Node> getNodes();

	Node getNode(int id);

	boolean addNode(Node Node);

	int deleteNode(int id);

	int deleteAllNode();
}
