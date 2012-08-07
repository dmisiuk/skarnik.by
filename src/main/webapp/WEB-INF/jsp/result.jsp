<%@page import="java.util.List"%>
<%@page import="by.minsler.skarnik.beans.Key"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!doctype html>
<html>
<head>
	<title>«${param.text}» по-белорусски | Skarnik — русско-белорусский словарь</title>
	<meta charset="utf-8">
	<meta name="description" content="Перевод слова «${param.text}» с русского на белорусский язык. Перевести со Скарником легко и быстро!" />
	<meta name="keywords" content="${param.text}" />
	<link rel="stylesheet" href="css/style.css">
	<script type="text/javascript" src="js/javascript.js" ></script>
</head>
<body onload="init();" onkeydown="if(event.keyCode==38){ alert('up');}else if(event.keyCode==40){alert('down');}">
	<jsp:include page="/WEB-INF/template/header.jsp" />
	<div id="container">
	
	
		<div id="content">
		
				<div id="search_tab">
				<form  action="translate"> 
					<a href="index.jsp"><img src="img/logo.gif" /></a>
				
					<!-- <a id="logo" href="index.jsp">SKARNIK</a>-->
					<br>
					<input type="text" name="text" value="${param.text}" size="50" id="complete-field" onkeyup="doCompletion();" autocomplete="off" onclick="this.select();">
					<input id="button-translate"  type="submit" value="перевести">
					<br>
					Рус > Бел
					<table>
						<tbody>
						 	<tr>
							<td id="auto-row" colspan="2">
       							<table id="complete-table">
       							</table>
							</td> 
							</tr>
						</tbody>
					</table>
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
								<a href="translate?text=<%=text%>&strict=yes"><%=text%></a>
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