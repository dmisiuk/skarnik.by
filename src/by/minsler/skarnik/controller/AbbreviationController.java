package by.minsler.skarnik.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import org.apache.log4j.Logger;

public class AbbreviationController extends HttpServlet {

	private static Logger logger = Logger.getLogger(AbbreviationController.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		logger.info("get AbbreviationController");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String urlView = null;
		if(request.getParameter("showButton") != null){
			urlView = "abbreviation/show.jsp";
		} else if(request.getParameter("newButton") != null){
			urlView = "abbreviation/new.jsp";
		} else{
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