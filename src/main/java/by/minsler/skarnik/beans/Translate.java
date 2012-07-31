package by.minsler.skarnik.beans;

public class Translate {

	private static int nextId;
	private static Object o = new Object();

	private int id;

	public Translate(boolean autoId) {
		// if (autoId) {
		// synchronized (o) {
		id = nextId++;
		// }
		// }
	}

	public Translate() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
