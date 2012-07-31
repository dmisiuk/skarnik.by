package by.minsler.skarnik.beans;

public class Line {

	private int id;
	private static int nextId;
	private static Object o = new Object();

	private int level;

	private int translateId;

	public Line() {
	}

	public Line(boolean autoId) {
		// if (autoId) {
		// synchronized (o) {
		id = nextId++;
		// }
		// }

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getTranslateId() {
		return translateId;
	}

	public void setTranslateId(int translateId) {
		this.translateId = translateId;
	}

}
