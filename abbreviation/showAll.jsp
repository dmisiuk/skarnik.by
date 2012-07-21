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

			<div id="flash" >
				<% 
				String flash = (String) request.getAttribute("flash");
				if(flash != null){
				%>
				<p><%= flash %>
				<% } %>
			</div>
			<h1>Show all</h1>
			<%

			ArrayList<Abbreviation> list = (ArrayList<Abbreviation>) request.getAttribute("listAbbreviation");
			Iterator it = list.iterator();
			%>
				<table border="1">
					<thead>
						<tr>
							<th>ID</th>
							<th>Short Name</th>
							<th>Full Name</th>
						</tr>
					</thead>
					<tbody>
						<% while(it.hasNext()){ 
						Abbreviation abbr = (Abbreviation) it.next(); %>
						<tr>
							<td><%=  abbr.getId() %></td>
							<td><%=  abbr.getShortName() %> </td>
							<td><%=  abbr.getFullName() %> </td>
							<td>
								<form action="abbreviation" method="post">
								<input type="hidden" name="id" value="<%=  abbr.getId() %>"> 
								<input type="submit" name="deleteButton" value="delete">
								</form>
							</td>
						</tr>
						<% }%>
					</tbody>
				</table>						
				
		
			<form action="abbreviation" method="post">
				<input type="submit" name="newButton" value="New">
			</form>
			


		</div>
	</div>
	<jsp:include page="/template/footer.jsp" />
</body>
</html>