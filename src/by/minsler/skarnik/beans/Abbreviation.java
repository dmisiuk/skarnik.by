package by.minsler.skarnik.beans;

public class Abbreviation{

	private int id;
	private String shortName;
	private String fullName;

	public Abbreviation(){
	}

	public void setShortName(String shortName){
		this.shortName = shortName;
	}

	public void setFullName(String fullName){
		this.fullName = fullName;
	}

	public void setId(int id){
		this.id = id;
	}

	public String getShortName(){
		return shortName;
	}

	public String getFullName(){
		return fullName;
	}

	public int getId(){
		return id;
	}

}