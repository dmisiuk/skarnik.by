package by.minsler.skarnik.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import org.apache.log4j.Logger;
import by.minsler.skarnik.helper.AbbreviationHelper;

public class AbbreviationController extends HttpServlet {

	private static Logger logger = Logger.getLogger(AbbreviationController.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		logger.info("get AbbreviationController");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String urlView = null;
		if(request.getParameter("deleteButton") != null){
			AbbreviationHelper.delete(request);
			logger.info("invoked AbbreviationHelper.delete");
			AbbreviationHelper.getAll(request);
			urlView = "abbreviation/showAll.jsp";
		} else if(request.getParameter("deleteAllButton") != null){
			AbbreviationHelper.deleteAll(request);
			AbbreviationHelper.getAll(request);
			logger.info("invoked AbbreviationHelper.deleteAll");
			urlView = "abbreviation/showAll.jsp";
		} else if(request.getParameter("createButton") != null){
			AbbreviationHelper.create(request);
			logger.info("invoked AbbreviationHelper.create()");
			AbbreviationHelper.getAll(request);
			logger.info("invoked AbbreviationHelper.geetAll()");
			urlView = "abbreviation/showAll.jsp";
		} else if(request.getParameter("initButton") != null){
			AbbreviationHelper.init(request);
			AbbreviationHelper.getAll(request);
			urlView = "abbreviation/showAll.jsp";
		} else if(request.getParameter("newButton") != null){
			urlView = "abbreviation/new.jsp";
		} else{
			AbbreviationHelper.getAll(request);
			urlView = "abbreviation/showAll.jsp";
		}
		 
		logger.info("set view for controller: " + urlView);
		RequestDispatcher view = request.getRequestDispatcher(urlView);
		view.forward(request,response);
		logger.info("forwarded to " + urlView);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		doGet(request,response);
		logger.info("post forwarded to get");
	}

}