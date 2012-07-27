<%@ page import="java.sql.Connection" %>

<!doctype html>
<html>
<head>
	<title>Tets jsp</title>
</head>
<body>

<%
Connection connection = null;;
Object connnectionObject = application.getAttribute("connection");
if(connnectionObject != null){
	connection = (Connection) connnectionObject;
	out.println("connection exists");
} else{
	out.println("connection not exist");
}
%>
</body>
</html>
