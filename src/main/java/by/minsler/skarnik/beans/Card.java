package by.minsler.skarnik.beans;

public class Card {

	private static Object o = new Object();
	private static int nextId;
	private int id;

	private int wordId;

	private int translateId;

	public Card(boolean autoId) {
		// if (autoId) {
		// synchronized (o) {
		id = nextId++;
		// }
		// }
	}

	public Card() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getWordId() {
		return wordId;
	}

	public void setWordId(int wordId) {
		this.wordId = wordId;
	}

	public int getTranslateId() {
		return translateId;
	}

	public void setTranslateId(int translateId) {
		this.translateId = translateId;
	}

}
