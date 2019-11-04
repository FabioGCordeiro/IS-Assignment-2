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
        <title>MyBay - Edit Account Information</title>
    </head>
    <body>
        <form action="CheckUserCredentials">
            <h2>Please enter your credentials before editing information:</h2><br><br>
            Email:
            <input type="text" name = "email"><br><br>
            Password:
            <input type="password" name = "password"><br><br>
            <input type="submit" value="Confirm">
        </form>
    </body>
</html>