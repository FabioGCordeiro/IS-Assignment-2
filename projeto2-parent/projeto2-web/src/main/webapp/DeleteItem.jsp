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
        <title>MyBay - Delete Item</title>
    </head>
    <body>
        <form action="DeleteItem">
            <h2>Please enter the item's name to confirm:</h2><br><br>
            Item Name:
            <input type="text" name = "name"><br><br>
            <input type="submit" value="Confirm">
        </form>
    </body>
</html>