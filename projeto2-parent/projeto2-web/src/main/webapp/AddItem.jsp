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
        <title>MyBay - Add Item</title>
    </head>
    <body>
        <form action="AddItem">
            <h1>Add Item</h1><br><br>
            Item name:
            <input type="text" name = "name"><br><br>
            Category:
            <input type="text" name = "category"><br><br>
            Country of Origin:
            <input type="text" name = "country"><br><br>
            <input type="submit" value="Add">
        </form>
    </body>
</html>