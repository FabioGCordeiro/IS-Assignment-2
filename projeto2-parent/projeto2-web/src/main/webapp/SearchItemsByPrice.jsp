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
        <title>MyBay - Search By Price Range</title>
    </head>
    <body>
        <form action="SearchByPrice">
            <h1>Please insert the lowest and highest prices</h1><br><br>
            Lowest price:
            <input type="text" name = "lowestPrice"><br><br>
            Highest price:
            <input type="text" name = "highestPrice"><br><br>
            <input type="hidden" name="order" value="0">
            <input type="submit" value="Search">
        </form>
    </body>
</html>