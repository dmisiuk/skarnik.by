<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!doctype html>
<html>
<head>
	<title>Skarnik.by search</title>
	<meta charset="utf-8">
	<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:include page="/template/header.jsp" />
	<div id="container">
		<div id="content">
			<div id="search_tab">
				<form  action="result.jsp"> 
					<span id="logo"> SKARNIK</span>
					<input type="text" name="text" value="" size="70">
					<input type="submit" value="перевести">
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="/template/footer.jsp" />
</body>
</html>