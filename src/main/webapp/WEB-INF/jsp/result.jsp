<%@page import="java.util.List"%>
<%@page import="by.minsler.skarnik.beans.Key"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!doctype html>
<html>
<head>
	<title>Перевод</title>
	<meta charset="utf-8">
	<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:include page="/WEB-INF/template/header.jsp" />
	<div id="container">
	
	
		<div id="content">
		
				<div id="search_tab">
					<form  action="translate"> 
						<span id="logo"> SKARNIK</span>
						<input type="text" name="text" value="${param.text}" size="70">
						<input type="submit" value="перевести">
					</form>
				</div>
			<div id="result">
				<%
					List<Key> list = (List<Key>) request.getAttribute("list");
					if(list != null){
						for(Key key:list){
							String text = key.getText();
				%>
							<div class="strictlink">
								<a href="translate?text=<%=text%>&strict=yes>"><%=text%></a>
							</div>
				<%
							}
					} else if((String) request.getAttribute("keyText") != null){
						
				%>
				
				<div class="article">
					<div class="key">
						<%= (String) request.getAttribute("keyText")%>
					</div>
					<div class="def">
						<%= (String) request.getAttribute("defText")%>
					</div>
				</div>
				
				
				<%	
					} else{
				%>
					
					<div class="emptyresult">
						ничего не найдено по тексту ${param.text}
					</div>
						
				<%		
				}
				%>
			</div>
		
		</div>
		
	</div>
	<jsp:include page="/WEB-INF/template/footer.jsp" />
</body>
</html>