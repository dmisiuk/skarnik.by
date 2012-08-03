package by.minsler.skarnik.beans;

public class Def {

	private int id;
	private static int nextID;
	private String text;

	public Def() {
	}

	public Def(boolean autoID) {
		id = nextID++;
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
