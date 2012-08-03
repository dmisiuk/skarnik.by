package by.minsler.skarnik.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.minsler.skarnik.parser.ArticleParser;

public class ArticleController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String fileName = getServletContext().getInitParameter("xdxffile");
		String homeDir = getServletContext().getRealPath("/");
		File file = new File(homeDir, fileName);
		// risk. uncomment for initialize database
		ArticleParser parser = new ArticleParser(file);
		parser.initDB();

		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.println("Inited artciles");

	}
}
