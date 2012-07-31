package by.minsler.skarnik.beans;

public class Word {

	private static Object o = new Object();
	private static int nextId;

	private int id;

	private String text;

	public Word(boolean autoId) {
		// if (autoId) {
		// synchronized (o) {
		id = nextId++;
		// }
		// }
	}

	public Word() {

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
