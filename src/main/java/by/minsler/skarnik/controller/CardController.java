package by.minsler.skarnik.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.minsler.skarnik.parser.CardParser;

public class CardController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		ServletContext context = getServletContext();

		String homeDir = context.getRealPath("/");
		String dslFile = context.getInitParameter("DSLFile");
		File file = new File(homeDir, dslFile);
		CardParser parser = new CardParser(file);
		parser.parse();
		parser.destroy();

		PrintWriter out = response.getWriter();
		out.println("dima");
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

}
