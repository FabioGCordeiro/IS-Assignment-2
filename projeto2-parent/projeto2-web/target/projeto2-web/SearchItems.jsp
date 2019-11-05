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
        <title>MyBay - Shopping Menu</title>
    </head>
    <body>
        <h1>Search Items Menu</h1><br>
        <h3>Choose an option:</h3><br>
        <form action="SearchAllItems">
            <button type="submit"> Show All Items </button><br><br>
        </form>
        <a href="SearchByCategory.jsp"><button> Search By Category </button></a><br><br>

        <form action="SearchByCountry">
            <button type="submit"> Search By Country </button><br><br>
        </form>

        <a href="SearchItemsByPrice.jsp"><button> Search By Price </button></a>
    </body>
</html>