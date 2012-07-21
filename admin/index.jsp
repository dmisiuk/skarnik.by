<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!doctype html>
<html>
<head>
	<title>Admin: main page</title>
	<meta charset="utf-8">
	<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:include page="/template/header.jsp" />
	<div id="container">

		<div id="content">
			<h1>Hello, admin</h1>
			<h2>Abbreviation:</h2>
			<form action="abbreviation" method="post" accept-charset="utf-8">
				<input type="submit" name="newButton" value="new">
				<input type="submit" name="showAllButton" value="Show all">
			</form>	
		</div>
	</div>
	<jsp:include page="/template/footer.jsp" />
</body>
</html>