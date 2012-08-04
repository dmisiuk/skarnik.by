<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!doctype html>
<html>
<head>
	<title>Admin: main page</title>
	<meta charset="utf-8">
	<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:include page="/WEB-INF/template/header.jsp" />
	<div id="container">

		<div id="content">
			<h1>Hello, admin</h1>
			<h2>Abbreviation:</h2>
			<form action="abbreviation" method="post" accept-charset="utf-8">
				<input type="submit" name="newButton" value="new">
				<input type="submit" name="showAllButton" value="Show all">
				<input type="submit" name="deleteAllButton" value="Delete all">
				<input type="submit" name="initButton" value="Init from dsl file">
			</form>
			<br>
			<h2>Card:</h2>
			<form action="card" method="post" accept-charset="utf-8">
				<!-- <input type="submit" name="newButton" value="new">
				<input type="submit" name="showAllButton" value="Show all">
				<input type="submit" name="deleteAllButton" value="Delete all"> -->
				<input type="submit" name="initButton" value="Init from dsl file">
			</form>
			<br>
			<h2>Article:</h2>
			<form action="article" method="post" accept-charset="utf-8">
				<!-- <input type="submit" name="newButton" value="new">
				<input type="submit" name="showAllButton" value="Show all">
				<input type="submit" name="deleteAllButton" value="Delete all"> -->
				<input type="submit" name="initButton" value="Init from xdxf file">
			</form>	
			
		</div>
	</div>
	<%--  --%><jsp:include page="/WEB-INF/template/footer.jsp" />
	<div id="footer">
		<a href="index.jsp" title="на главную">skarnik.by</a>
	</div>
</body>
</html>