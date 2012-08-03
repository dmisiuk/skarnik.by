package by.minsler.skarnik.parser;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import by.minsler.skarnik.parser.handler.XdxfHandler;

public class ArticleParser {

	private static Logger logger = Logger.getLogger(ArticleParser.class);

	private File file;
	private DefaultHandler handler;
	private SAXParser parser;

	public ArticleParser(File file) {
		this.file = file;
		handler = new XdxfHandler();
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			parser = factory.newSAXParser();
			logger.info("saxparser createad");
		} catch (ParserConfigurationException e) {
			logger.error("create saxparser: configuration: " + e);
		} catch (SAXException e) {
			logger.error("create saxparser: " + e);
		}

	}

	public void initDB() {

		try {
			parser.parse(file, handler);
		} catch (SAXException e) {
			logger.error("parse xml: " + e);
		} catch (IOException e) {
			logger.error("open xml file: " + e);
		}
	}
}
