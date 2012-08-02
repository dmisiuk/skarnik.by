package by.minsler.skarnik.parser;

import java.util.ArrayList;

import by.minsler.skarnik.beans.Node;
import by.minsler.skarnik.dao.NodeDAO;
import by.minsler.skarnik.dao.NodeDAOPostgres;

public class NodeParser {

	private NodeDAO ndao;

	public NodeParser() {
		ndao = NodeDAOPostgres.getInstance();
	}

	public NodeParser(boolean withoutDao) {

	}

	public void parse(String text, int lineId) {

		text = text.replaceAll("\\[/lang\\]\\[/com\\] \\[c\\]\\[p]",
				"[/lang][/com][/i] [i][c][p]");
		text = text.replaceAll("\\[/!trs\\]\\[/com\\] \\[c\\]\\[p\\]",
				"[/!trs][/com][/i] [i][c][p]");
		text = text.replaceAll("\\[/p\\]\\[/c\\] \\[com\\]\\[lang id=2\\]",
				"[/p][/c][/i] [i][com][lang id=2]");
		for (int i = 1; i < 6; i++) {
			text = text.replaceAll("\\[com\\]" + i + "\\) \\[lang id=2\\]",
					"[com][lang id=2]" + i + ") ");
		}
		text = text.replaceAll("\\[/p\\], \\[p\\]", "[/p][/i][/c], [c][i][p]");
		text = text.replaceAll("\\[/p\\] \\[p\\]", "[/p][/i][/c] [c][i][p]");
		// System.out.println(text);
		ArrayList<StringBuilder> nodeList = parseNode(text);
		// System.out.println(nodeList);

		createNode(nodeList, lineId);

	}

	private ArrayList<StringBuilder> parseNode(String text) {

		// не определяет [/lang][/com] [c][p]

		ArrayList<StringBuilder> nodes = new ArrayList<StringBuilder>();
		StringBuilder s = new StringBuilder();
		int number = 0;
		boolean tag = false;
		char[] chars = text.toCharArray();

		for (int i = 0; i < chars.length; i++) {

			if (chars[i] == '[') {
				if (s.length() > 0 && number == 0) {
					nodes.add(s);
					s = new StringBuilder();
				}
				tag = true;
				if (chars[i + 1] != '/') {
					number++;
				}
			}

			if (tag) {
				if (chars[i] == '/') {
					number--;
				}
			}

			s.append(chars[i]);

			if (chars[i] == ']' && number == 0) {
				nodes.add(s);
				s = new StringBuilder();
				tag = false;
			}

		}

		if (s.length() > 0) {
			nodes.add(s);
		}

		return nodes;
	}

	private void createNode(ArrayList<StringBuilder> nodeList, int lineId) {

		for (StringBuilder strb : nodeList) {

			Node node = new Node(true);
			String str = new String(strb);

			if (str.startsWith("[b][lang id=2]")) {
				node.setTagId(0);
				node.setText(str.substring(14, str.length() - 11));
			} else if (str.startsWith("[b]")) {
				node.setTagId(0);
				node.setText(str.substring(3, str.length() - 4));
			} else if (str.startsWith("[i][com][lang id=2]")) {
				node.setTagId(2);
				node.setText(str.substring(19, str.length() - 17));
			} else if (str.startsWith("[i][com][!trs]")) {
				node.setTagId(6);
				node.setText(str.substring(14, str.length() - 17));
			} else if (str.startsWith("[i][com]")) {
				node.setTagId(2);
				node.setText(str.substring(8, str.length() - 10));
			} else if (str.startsWith("[trn]")) {
				node.setTagId(3);
				node.setText(str.substring(5, str.length() - 6));
			} else if (str.startsWith("[ref]")) {
				node.setTagId(5);
				node.setText(str.substring(5, str.length() - 6));
			} else if (str.startsWith("[i][c][p]")
					|| str.startsWith("[c][i][p]")) {
				node.setTagId(1);
				node.setText(str.substring(9, str.length() - 12));
			} else {
				node.setTagId(4);
				node.setText(str);
			}

			node.setLineId(lineId);

			if (ndao != null) {
				ndao.addNode(node);
			}
		}

	}
}
