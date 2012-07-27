<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!doctype html>
<html>
<head>
	<title>Abbreviation: new</title>
	<meta charset="utf-8">
	<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:include page="/template/header.jsp" />
	<div id="container">

		<div id="content">
			<h1>New</h1>
			<form action="abbreviation" method="post">
				Abberivation info:
				<br>Short Name: <input type="text" name="shortName">
				<br>Full Name: <input type="text" name="fullName">
				<br><input type="submit" name="createButton" >
			</form>
		</div>
	</div>
	<jsp:include page="/template/footer.jsp" />
</body>
</html>