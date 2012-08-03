package by.minsler.skarnik.beans;

public class Article {

	private static int nextId;

	private int id;
	private int keyId;
	private int defId;

	public Article() {
	}

	public Article(boolean autoId) {
		this.id = nextId++;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getKeyId() {
		return keyId;
	}

	public void setKeyId(int keyId) {
		this.keyId = keyId;
	}

	public int getDefId() {
		return defId;
	}

	public void setDefId(int defId) {
		this.defId = defId;
	}

}
