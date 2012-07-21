package by.minsler.skarnik.dao;

import by.minsler.skarnik.beans.Abbreviation;
import by.minsler.skarnik.dao.AbbreviationDAO;
import by.minsler.skarnik.db.ConnectionInit;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import java.util.ArrayList;


public class AbbreviationDAOPostgres implements AbbreviationDAO{

	private static AbbreviationDAOPostgres inst;
	private static Connection connection;
	private static Logger logger = Logger.getLogger(AbbreviationDAOPostgres.class);

	private AbbreviationDAOPostgres(){
		connection = ConnectionInit.getConnection();
		logger.info("instantiate AbbreviationDAOPostgres");
	}

	public static AbbreviationDAOPostgres getInstance(){
		if(inst == null){
			inst = new AbbreviationDAOPostgres();
		}
		return inst;
	}


	public Abbreviation getAbbreviation(int id){

		return new Abbreviation();
	}
	public Abbreviation getAbbreviation(String shortName){
		return new Abbreviation();
	}
	public boolean addAbbreviation(Abbreviation abbreviation){
		return false;
	}

	public ArrayList<Abbreviation> getAbbreviations(){
		ArrayList<Abbreviation> list = new ArrayList<Abbreviation>();
		try{
			Statement getAllStatement = connection.createStatement();
			ResultSet result = getAllStatement.executeQuery("select * from abbreviations");
			while(result.next()){
				Abbreviation abbr = new Abbreviation();
				abbr.setId(result.getInt("id"));
				abbr.setShortName(result.getString("short_name"));
				abbr.setFulltName(result.getString("full_name"));
				list.add(abbr);
			}
		} catch(SQLException e){
			logger.error("sql exception: " + e);
		} 
		return list;
	}
}