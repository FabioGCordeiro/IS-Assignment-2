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
        <title>MyBay - Main Menu</title>
    </head>
    <body>
        <h1>Main Menu</h1>
        <a href="UserMenu.jsp"><button> User Menu </button></a><br><br>
        <a href="ShoppingMenu.jsp"><button> Shopping Menu </button></a><br><br>
        <a href="InitialMenu.jsp"><button> Logout </button></a><br><br>
    </body>
</html>