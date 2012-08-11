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
<body onload="init();" >
	<jsp:include page="/WEB-INF/template/header.jsp" />
	<div id="container">
	
	
		<div id="content">
		<a href="index.jsp"><img src="img/logo.gif" /></a>
		
		<div id="search" >
    	<div id="searh-line" >
	    	<form id="main-form" action="translate">
		        <div id="search-tab" onkeydown="keyControl();">
		        	<input id="translate-input-form" type="text" value="${param.text}" 
		        			name="text" onkeyup="doCompletion();" autocomplete="off" onblur="//clearCheatList();" ></div>
		        <div id="search-button"><input id="translate-button" type="submit" value="перевести"></div>
		    </form>
	    </div>
		    <div id="cheat">
		        <ul id="cheat-list">
		        </ul>
		    </div>

		</div>
		
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