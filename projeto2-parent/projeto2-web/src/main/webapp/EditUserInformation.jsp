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
        <title>MyBay - Edit User Information</title>
    </head>
    <body>
        <form action="EditUserInformation">
            Email:
            <input type="text" name = "email"><br><br>
            Password:
            <input type="password" name = "password"><br><br>
            Username:
            <input type="text" name = "username"><br><br>
            Country:
            <input type="text" name = "country"><br><br>
            <input type="submit" value="Edit">
        </form>
    </body>
</html>