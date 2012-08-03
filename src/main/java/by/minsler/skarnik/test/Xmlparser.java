package by.minsler.skarnik.test;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import by.minsler.skarnik.parser.handler.XdxfHandler;

public class Xmlparser {
	public static void main(String[] args) throws ParserConfigurationException,
			SAXException, IOException {

		File file = new File("/home/minsler/test.xdxf");
		DefaultHandler handler = new XdxfHandler(true);

		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();

		parser.parse(file, handler);

	}
}
