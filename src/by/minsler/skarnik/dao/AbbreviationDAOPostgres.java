package by.minsler.skarnik.dao;

import by.minsler.skarnik.beans.Abbreviation;
import by.minsler.skarnik.dao.AbbreviationDAO;
import by.minsler.skarnik.db.ConnectionInit;
import java.sql.Connection;
import org.apache.log4j.Logger;


public class AbbreviationDAOPostgres implements AbbreviationDAO{

	private static AbbreviationDAOPostgres inst;
	private static Connection connection;
	private static Logger logger = Logger.getLogger(AbbreviationDAOPostgres.class);

	private AbbreviationDAOPostgres(){
		connection = ConnectionInit.getConnection();
		logger.info("instantiate AbbreviationDAOPostgres");
	}

	public AbbreviationDAOPostgres getInstance(){
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
}