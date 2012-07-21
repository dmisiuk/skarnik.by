<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!doctype html>
<html>
<head>
	<title>Перевод</title>
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
					<input type="text" name="text" value="${param.text}" size="70">
					<input type="submit" value="перевести">
				</form>
			</div>
		<div id="result">
			<div id="word">a</div>
			<div id="translate">
				<div class="level0" >
					<span class="node">
						<span class="number">I</span>
					</span>
					<span class="node">
						<span class="pomet"><a title="часть речи">союз</a></span>
					</span>
				</div>
				<div class="level1" >
					<span class="node">
						<span class="comment"> 1) (противительный)</span>
					</span>
					<span class="node">
						<span class="translate">a</span>
					</span>
				</div>
				<div class="level1" >
					<span class="node">
						<span class="comment"> (но)</span>
					</span>
					<span class="node">
						<span class="translate">aле</span>
					</span>
				</div>
				<div class="level1" >
					<span class="node">
						<span class="comment"> (да)</span>
					</span>
					<span class="node">
						<span class="translate">ды</span>
					</span>
				</div>
				<div class="example" >
					<div class="level2" >
						<span class="node">
							<span class="origin">на горе дом, а под горой ручей</span>
						</span>
						<span class="node">
							<span class="translate"> — на гары дом, а пад гарой ручай</span>
						</span>
					</div>
				</div>
			</div>
		</div>
		</div>
	</div>
	<jsp:include page="/template/footer.jsp" />
</body>
</html>