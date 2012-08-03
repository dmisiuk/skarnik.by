package by.minsler.skarnik.parser.handler;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import by.minsler.skarnik.beans.Article;
import by.minsler.skarnik.beans.Def;
import by.minsler.skarnik.beans.Key;
import by.minsler.skarnik.dao.ArticleKeyDefDAO;
import by.minsler.skarnik.dao.ArticleKeyDefDAOPostgres;
import by.minsler.skarnik.parser.Replacer;

public class XdxfHandler extends DefaultHandler {

	private ArticleKeyDefDAO akd;
	private List<Key> list;
	private Def def;
	private StringBuilder defText;
	private Key key;
	private boolean inArticle = false;
	private boolean inKey = false;
	private boolean inDef = false;
	private Replacer replacer;

	public XdxfHandler() {
		this.akd = ArticleKeyDefDAOPostgres.getInstance();
		replacer = new Replacer();
	}

	public XdxfHandler(boolean withoutDao) {
		replacer = new Replacer();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (qName.equals("article")) {
			list = new ArrayList<Key>();
			inArticle = true;
			return;
		}

		if (qName.equals("key")) {
			key = new Key(true);
			inKey = true;
			return;
		}

		if (qName.equals("def")) {
			def = new Def(true);
			defText = new StringBuilder();
			inDef = true;
			return;
		}

		if (inDef) {
			defText.append(replacer.replaceStart(qName, attributes));
		}

	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (qName.equals("article")) {
			akd.addDef(def);
			for (Key key : list) {
				akd.addKey(key);
				Article article = new Article(true);
				article.setDefId(def.getId());
				article.setKeyId(key.getId());
				akd.addArticle(article);
				// System.out.println("key.text: " + key.getText()
				// + "; article.id: " + article.getDefId() + "; def: "
				// + def.getText());
			}
			inArticle = false;
		}

		if (qName.equals("key")) {
			list.add(key);
			inKey = false;
		}

		if (qName.equals("def")) {
			def.setText(new String(defText));
			inDef = false;
		}

		if (inDef) {
			defText.append(replacer.replaceEnd(qName));
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if (inKey) {
			key.setText(new String(ch, start, length));
		}
		if (inDef) {
			defText.append(new String(ch, start, length));
		}
	}
}
