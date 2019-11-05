<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" session="false"%>

<%
HttpSession session = request.getSession(false);
String user = (String) session.getAttribute("email");

if(user==null){
    response.sendRedirect("Error.jsp");
}
%>

<html>
    <head>
        <title>MyBay - All Items</title>
    </head>
    <body>
        <h1>All Items for Sale</h1><br><br>
    </body>
</html>