package by.minsler.skarnik.test;

import java.util.ArrayList;

import by.minsler.skarnik.beans.Node;

//import org.apache.log4j.Logger;

public class Test {

	// private static Logger logger = Logger.getLogger(Test.class);

	public static void main(String[] args) {

		int level = 0;

		String text = "[b]II[/b] [i][com][lang id=2]вопросительная[/lang][/com] [c][p]частица[/p][/c][/i] [trn]га, а[/trn] [i][com][lang id=2](первая обычно при отклике на зов)[/lang][/com][/i]";
		if (text.matches("\\[m\\d\\].*\\[/m\\]")) {
			System.out.println("in if");
			level = Integer.parseInt(text.substring(2, 3));
		}
		System.out.println("level : " + level);
		if (level > 0) {
			text = text.substring(4, text.length() - 4);
		}
		System.out.println(text);

		ArrayList<StringBuilder> nodeList = parseNode(text);

		for (StringBuilder ss : nodeList) {
			System.out.println("NodeString:" + ss);
		}

		createNode(nodeList, 1);

	}

	private static void createNode(ArrayList<StringBuilder> nodeList, int lineId) {

		for (StringBuilder strb : nodeList) {

			Node node = new Node(true);
			String str = new String(strb);

			if (str.startsWith("[b]")) {
				node.setTagId(0);
				node.setText(str.substring(3, str.length() - 4));
			} else if (str.startsWith("[i][com][lang id=2]")) {
				node.setTagId(2);
				node.setText(str.substring(19, str.length() - 17));
			} else if (str.startsWith("[trn]")) {
				node.setTagId(3);
				node.setText(str.substring(5, str.length() - 6));
			} else if (str.startsWith("[ref]")) {
				node.setTagId(5);
				node.setText(str.substring(5, str.length() - 6));
			} else if (str.startsWith("[i][c][p]")) {
				node.setTagId(1);
				node.setText(str.substring(9, str.length() - 12));
			} else {
				node.setTagId(4);
				node.setText(str);
			}

			node.setLineId(lineId);

			System.out.println("Node text:" + node.getText() + ";tag_id:"
					+ node.getTagId());

		}

	}

	public static ArrayList<StringBuilder> parseNode(String text) {

		// не определяет [/lang][/com] [c][p]

		ArrayList<StringBuilder> nodes = new ArrayList<StringBuilder>();
		StringBuilder s = new StringBuilder();
		int number = 0;
		boolean tag = false;
		char[] chars = text.toCharArray();

		for (int i = 0; i < chars.length; i++) {

			s.append(chars[i]);

			if (chars[i] == '[') {
				tag = true;
				if (chars[i + 1] != '/') {
					number++;
				}
			}

			if (chars[i] == ']') {
				tag = false;
			}

			if (tag) {
				if (chars[i] == '/') {
					number--;
				}
			}

			if (!tag && number == 0) {
				nodes.add(s);
				s = new StringBuilder();
			}

		}

		return nodes;
	}
}