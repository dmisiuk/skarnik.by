package by.minsler.skarnik.beans;

public class Key {

	private static int nextId;

	private int id;

	private String text;

	public Key() {
	}

	public Key(boolean autoId) {
		this.id = nextId++;
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

}
