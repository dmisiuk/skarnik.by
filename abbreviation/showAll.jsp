<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page import="java.util.ArrayList, by.minsler.skarnik.dao.AbbreviationDAOPostgres, by.minsler.skarnik.beans.Abbreviation" %>
<%@ page import="java.util.Iterator" %>

<!doctype html>
<html>
<head>
	<title>Abbreviation: show all</title>
	<meta charset="utf-8">
	<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:include page="/template/header.jsp" />
	<div id="container">

		<div id="content">
			<h1>Show all</h1>
			<%

			AbbreviationDAOPostgres abbrdao = AbbreviationDAOPostgres.getInstance();
			ArrayList<Abbreviation> list = abbrdao.getAbbreviations();

			Iterator it = list.iterator();
			%>
			<ul>
				<% while(it.hasNext()){ 
				Abbreviation abbr = (Abbreviation) it.next(); %>
				<li>ID: <%=  abbr.getId() %> ShortName: <%=  abbr.getShortName() %> FullName: <%=  abbr.getFullName() %> </li>
				<% }%>
			</ul>
			


		</div>
	</div>
	<jsp:include page="/template/footer.jsp" />
</body>
</html>