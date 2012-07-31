package by.minsler.skarnik.beans;

public class Node {

	private int id;
	private static int nextId;

	private String text;

	private int tagId;

	private static int lineId;

	public static int getLineId() {
		return lineId;
	}

	public static void setLineId(int lineId) {
		Node.lineId = lineId;
	}

	public Node() {
	}

	public Node(boolean autoId) {
		id = nextId++;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getTagId() {
		return tagId;
	}

	public void setTagId(int tagId) {
		this.tagId = tagId;
	}

}
