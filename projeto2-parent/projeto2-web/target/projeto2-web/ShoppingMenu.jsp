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
        <h1>Shopping Menu</h1><br>
        <h3>Choose an option:</h3><br>
        <a href="ManageItemsMenu.jsp"><button> Manage Items </button></a><br><br>
        <a href="SearchItems.jsp"><button> Search Items </button></a>
    </body>
</html>