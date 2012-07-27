package by.minsler.skarnik.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import org.apache.log4j.Logger;

public class AdminController extends HttpServlet {

	private static Logger logger = Logger.getLogger(AdminController.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		logger.info("get adminController");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String urlView = "admin/index.jsp";
		logger.info("set view for admin controller: " + urlView);
		RequestDispatcher view = request.getRequestDispatcher(urlView);
		view.forward(request,response);
		logger.info("forwarded to " + urlView);
	}
}