package by.minsler.skarnik.helper;

import java.util.ArrayList;
import by.minsler.skarnik.beans.Abbreviation;
import javax.servlet.http.HttpServletRequest;
import by.minsler.skarnik.dao.AbbreviationDAOPostgres;
import org.apache.log4j.Logger;

public class AbbreviationHelper{

	private static Logger logger = Logger.getLogger(AbbreviationHelper.class);

	public static boolean create(HttpServletRequest request){
		boolean result;
		AbbreviationDAOPostgres abbrdoa = AbbreviationDAOPostgres.getInstance();
		String shortName = request.getParameter("shortName");
		String fullName = request.getParameter("fullName");
		if(shortName == null || fullName == null || shortName.trim().equals("") || fullName.trim().equals("")){
			logger.info("abbreviation parameter not valid");
			request.setAttribute("flash", "abbreviation parameter not valid" );
			logger.info("added flash to request");
			return false;
		}
		Abbreviation abbr = new Abbreviation();
		abbr.setShortName(shortName);
		abbr.setFullName(fullName);
		result = abbrdoa.addAbbreviation(abbr);
		if(result == true ){
			request.setAttribute("flash", "Success" );
			logger.info("added flash to request");
		}
		logger.info("add abbreviation");
		return result;
	}

	public static void getAll(HttpServletRequest request){
		AbbreviationDAOPostgres abbrdao = AbbreviationDAOPostgres.getInstance();
		ArrayList<Abbreviation> list = abbrdao.getAbbreviations();
		logger.info("get all abbreviation");
		request.setAttribute("listAbbreviation", list);
		logger.info("added list abbreviation to request");
		return;
	}

	public static int deleteAll(HttpServletRequest request){
		int result;
		AbbreviationDAOPostgres abbrdoa = AbbreviationDAOPostgres.getInstance();
		result = abbrdoa.deleteAll();
		request.setAttribute("flash", "Deleted " + result + " abbreviation(s)" );
		logger.info("add flash message to request");
		return result;
	}

	public static int delete(HttpServletRequest request){
		int result;
		int id = Integer.parseInt(request.getParameter("id"));
		AbbreviationDAOPostgres abbrdoa = AbbreviationDAOPostgres.getInstance();
		result = abbrdoa.delete(id);
		request.setAttribute("flash", "Deleted " + result + " abbreviation(s)" );
		logger.info("add flash message to request");
		return result;
	}

}