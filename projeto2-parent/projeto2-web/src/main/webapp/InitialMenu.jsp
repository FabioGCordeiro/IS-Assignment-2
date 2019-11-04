<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" session="false"%>

<%
HttpSession session = request.getSession();
session.setAttribute("email",null);
%>

<html>
    <head>
        <title>MyBay - Initial Menu</title>
    </head>
    <body>
        <h1>Welcome to MyBay!</h1><br>
        <a href="Register.jsp"><button> Create Account </button></a><br><br>
        <a href="Login.jsp"><button> Login </button></a>
    </body>
</html>