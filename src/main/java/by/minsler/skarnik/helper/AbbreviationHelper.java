package by.minsler.skarnik.helper;

import java.util.ArrayList;
import by.minsler.skarnik.beans.Abbreviation;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletContext;
import by.minsler.skarnik.dao.AbbreviationDAOPostgres;
import org.apache.log4j.Logger;
import by.minsler.skarnik.parser.AbbreviationParser;
import java.io.File;

public class AbbreviationHelper{

	private static Logger logger = Logger.getLogger(AbbreviationHelper.class);

	public static boolean create(HttpServletRequest request){
		boolean result;
		AbbreviationDAOPostgres abbrdao = AbbreviationDAOPostgres.getInstance();
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
		result = abbrdao.addAbbreviation(abbr);
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
		AbbreviationDAOPostgres abbrdao = AbbreviationDAOPostgres.getInstance();
		result = abbrdao.deleteAll();
		request.setAttribute("flash", "table truncated");
		logger.info("add flash message to request");
		return result;
	}

	public static int delete(HttpServletRequest request){
		int result;
		int id = Integer.parseInt(request.getParameter("id"));
		AbbreviationDAOPostgres abbrdao = AbbreviationDAOPostgres.getInstance();
		result = abbrdao.delete(id);
		request.setAttribute("flash", "Deleted " + result + " abbreviation(s)" );
		logger.info("add flash message to request");
		return result;
	}


	public static void init(HttpServletRequest request){
		int count = 0;
		logger.info("tryed init abbreviation table");
		AbbreviationDAOPostgres abbrdao = AbbreviationDAOPostgres.getInstance();
		ServletContext context = request.getSession().getServletContext();
		String flash;
		String homeDir = context.getRealPath("/");
		String abbreviationDSLFile  = context.getInitParameter("abbreviationDSLFile");
		if(abbreviationDSLFile == null){
			flash = "set path to dsl abbreviation file in web.xml";
			return;
		}
		File file = new File(homeDir, abbreviationDSLFile);
		logger.info("create file: abbreviationDSLFile");
		AbbreviationParser parser = new AbbreviationParser(file);
		logger.info("create parser ");
		while(parser.next()){
			if(abbrdao.addAbbreviation(parser.getAbbreviation())){
				logger.debug("added abbreviation " + parser.getAbbreviation().getShortName());
				count++;
			}
		}
		parser.close();
		logger.info("close parser ");
		flash = "Added " + count + " abbreviation(s)";
		request.setAttribute("flash", flash);
		logger.info(flash);
		return;
	}

}