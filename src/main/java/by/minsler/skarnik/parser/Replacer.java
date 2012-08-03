package by.minsler.skarnik.parser;

import org.xml.sax.Attributes;

public class Replacer {

	// private Map<String, String> endTags = new HashMap<String, String>();
	// private Map<String, String> startTags = new HashMap<String, String>();

	public Replacer() {
		// endTags.put("line", "</div>");
		// endTags.put("comment", "</span>");
		// endTags.put("dtrn", "</span>");
		// endTags.put("example", "</span>");
		// endTags.put("abr", "</span>");
		//
		// startTags.put("comment", "<span class=\"comment\">");
		// startTags.put("dtrn", "<span class=\"dtrn\">");
		// startTags.put("example", "<span class=\"example\">");
		// startTags.put("abr", "<span class=\"abr\">");
	}

	public String replaceStart(String qName, Attributes attributes) {

		// String newTag = startTags.get(qName);
		// if (qName.equals("line")) {
		// String level = attributes.getValue("level");
		// newTag = "<div class=\"level" + level + "\">";
		// }
		// if (newTag == null) {
		// newTag = "<" + qName + ">";
		// }
		String newTag = "<" + qName;
		String cssClass = attributes.getValue("class");
		if (cssClass != null) {
			newTag = newTag + " class=\"" + cssClass + "\">";
		} else {
			newTag = newTag + ">";
		}
		return newTag;
	}

	public String replaceEnd(String qName) {
		String newTag = "</" + qName + ">";
		// newTag = endTags.get(qName);
		// if (newTag == null) {
		// newTag = "</" + qName + ">";
		// }

		return newTag;
	}

}
