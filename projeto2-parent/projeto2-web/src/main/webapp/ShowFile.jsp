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
        <title>MyBay - User Menu</title>
    </head>
    <body>
        <h1><%url%></h1>
    </body>

</html>