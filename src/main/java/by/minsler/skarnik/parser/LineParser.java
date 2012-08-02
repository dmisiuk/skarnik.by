package by.minsler.skarnik.parser;

import by.minsler.skarnik.beans.Line;
import by.minsler.skarnik.dao.LineDAO;
import by.minsler.skarnik.dao.LineDAOPostgres;

public class LineParser {

	private LineDAO ldao = null;

	public LineParser() {
		ldao = LineDAOPostgres.getInstance();
	}

	public void parse(String text, int translateId) {

		// create line and add to db
		// parse node in line and add to db
		//

		text = text.trim();
		Line line = new Line(true);
		NodeParser nodeParser = new NodeParser();
		line.setTranslateId(translateId);
		int level = getLevel(text);
		line.setLevel(level);
		ldao.addLine(line);
		if (level == 1) {
			text = text.substring(4, text.length() - 4);
		} else if (level > 1) {
			text = text.substring(11, text.length() - 13);
		}

		nodeParser.parse(text, line.getId());

	}

	private int getLevel(String text) {
		int level = 0;

		String truncateText = text.trim();
		if (truncateText.matches("\\[m\\d\\].*\\[/m\\]")) {
			return Integer.parseInt(truncateText.substring(2, 3));
		} else {
			return level;
		}

	}
}
