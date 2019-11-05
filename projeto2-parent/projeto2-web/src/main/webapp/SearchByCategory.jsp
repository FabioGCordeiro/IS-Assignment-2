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
        <title>MyBay - Seary Category</title>
    </head>
    <body>
        <form action="SearchByCategory">
            <h1>Please insert a category</h1><br><br>
            Category:
            <input type="text" name = "category"><br><br>
            <input type="submit" value="Search">
        </form>

    </body>
</html>