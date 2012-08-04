<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!doctype html>
<html>
<head>
	<title>Русско-белорусский онлайн-словарь | Руска-беларускі анлайн-слоўнік | Skarnik</title>
	<meta charset="utf-8">
	<meta name="description" content="Электронный русско-белорусский словарь Skarnik. Ежедневное обновление словарной базы. Более 100 тысяч слов. Ваш незаменимый переводчик." />
	<meta name="keywords" content="русско-белорусский словарь" />
	<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:include page="/WEB-INF/template/header.jsp" />
	<div id="container">
		<div id="content">
			<div id="search_tab">
				<form  action="translate">
					<a href="index.jsp"><img src="img/logo.gif" /></a>
					<!-- <a id="logo" href="index.jsp">SKARNIK</a>-->
					<br>
					<input type="text" name="text" value="" size="50">
					<input type="submit" value="перевести">
					<br>
					Рус > Бел
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/template/footer.jsp" />
</body>
</html>