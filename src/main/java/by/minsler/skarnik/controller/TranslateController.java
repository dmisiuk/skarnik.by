package by.minsler.skarnik.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import by.minsler.skarnik.beans.Article;
import by.minsler.skarnik.beans.Def;
import by.minsler.skarnik.beans.Key;
import by.minsler.skarnik.dao.ArticleKeyDefDAO;
import by.minsler.skarnik.dao.ArticleKeyDefDAOPostgres;

public class TranslateController extends HttpServlet {

	private static Logger logger = Logger.getLogger(TranslateController.class);

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		logger.info("doGet translate ");
		ArticleKeyDefDAO akd = ArticleKeyDefDAOPostgres.getInstance();
		String text = request.getParameter("text");
		String strict = request.getParameter("strict");
		logger.info("translate for " + text + "; strict: " + strict);
		String letter = request.getParameter("letter");
		if (letter != null && !letter.trim().equals("")) {
			List<Key> list = akd.getKey(letter);
			int size = list.size();
			if (size > 1) {
				request.setAttribute("list", list);
			}

		} else if (text != null && !text.trim().equals("")) {

			Key key = akd.getKeyStrict(text);
			if (key != null) {
				Article article = akd.getArticleByKeyId(key.getId());
				Def def = akd.getDef(article.getDefId());
				request.setAttribute("keyText", key.getText());
				request.setAttribute("defText", def.getText());
			} else {
				if (strict == null) {
					text = text.trim();
					List<Key> list = akd.getKey(text);
					int size = list.size();
					logger.info("size of list of key : " + size);
					if (size == 0) {
					} else if (list.size() == 1) {
						key = list.get(0);
						Article article = akd.getArticleByKeyId(key.getId());
						Def def = akd.getDef(article.getDefId());
						request.setAttribute("keyText", key.getText());
						request.setAttribute("defText", def.getText());
					} else {
						request.setAttribute("list", list);
					}
				}
			}
		}

		RequestDispatcher result = request
				.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
		result.forward(request, response);

	}
}
