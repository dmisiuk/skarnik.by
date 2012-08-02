package by.minsler.skarnik.test;

import by.minsler.skarnik.parser.NodeParser;

//import org.apache.log4j.Logger;

public class Test {

	// private static Logger logger = Logger.getLogger(Test.class);

	public static void main(String[] args) {

		int level = 0;

		String text = "[b]II[/b] [i][com][lang id=2]вопросительная[/lang][/com] [c][p]частица[/p][/c][/i] [trn]га, а[/trn] [i][com][lang id=2](первая обычно при отклике на зов)[/lang][/com][/i]";
		// text =
		// "[b]II[/b] [i][com][lang id=2](танец, куртка, слива)[/lang][/com][/i] [trn]венгерка[/trn], [i][com][!trs]-кі[/!trs][/com] [c][p]жен.[/p][/c][/i]";
		// text =
		// "	[m1][i][c][p]сущ.[/p][/c] [com][lang id=2](вино)[/lang][/com][/i] [trn]венгерскае[/trn], [i][com][!trs]-кага[/!trs][/com] [c][p]ср.[/p][/c][/i][/m]";
		// text =
		// "[m2][*][ex][b][lang id=2]идти под венец[/lang][/b] — ісці да шлюбу[/ex][/*][/m]";
		// text =
		// "[m2][*][ex][b][lang id=2]что вы сегодня делаете? А завтра? А послезавтра?[/lang][/b] — што вы сягоння робіце? А заўтра? А паслязаўтра?[/ex][/*][/m]";
		text = "	[m1][i][com]1) [lang id=2](противительный)[/lang][/com][/i] [trn]а[/trn][/m]";
		text = "[m1][i][com]3) [lang id=2](присоединительный)[/lang][/com][/i] [trn]а[/trn][/m]";
		text = "[m1][i][com]1)[/com][/i] [trn]а[/trn][/m]";
		text = "	[m1][i][c][p]г.[/p][/c][/i] [trn]Аахен[/trn], [i][com][!trs]-на[/!trs][/com] [c][p]муж.[/p][/c][/i][/m]";
		text = "	[m1][i][c][p]анат.[/p][/c][/i] [trn]абдуктар[/trn], [i][com][!trs]-ра[/!trs][/com] [c][p]муж.[/p][/c][/i][/m]";
		text = " 	[m1][c][i][p]астр.[/p], [p]опт.[/p][/i][/c] [trn]аберацыйны[/trn][/m]";
		text = "	[m1][c][i][p]совер.[/p], [p]несовер.[/p] [p]уст.[/p][/i][/c] [trn]абаніраваць[/trn][/m]";
		text = " 	[m2][*][ex][b][lang id=2]абразионный процесс [i][c][p]геол.[/p][/c][/i][/lang][/b] — абразійны працэс[/ex][/*][/m]";
		text = " 	[m1][trn]абрыкос[/trn], [i][com][!trs]-са[/!trs] [lang id=2]и (о древесине и [c][p]собир.[/p][/c][/i])[/lang] [!trs]-су[/!trs][/com] [c][p]муж.[/p][/c][/m]";
		text = "	[m1][i][com][lang id=2](в, во)[/lang][/com] [c][p]предлог[/p][/c][/i][/m]";

		text = text.trim();
		if (text.matches("\\[m\\d\\].*\\[/m\\]")) {
			System.out.println("in if");
			level = Integer.parseInt(text.substring(2, 3));
		}
		System.out.println("level : " + level);

		if (level == 1) {
			text = text.substring(4, text.length() - 4);
		} else if (level > 1) {
			text = text.substring(11, text.length() - 13);
		}

		new NodeParser(false).parse(text, 1);

	}

}