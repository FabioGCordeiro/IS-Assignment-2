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
        <a href="AddItem.jsp"><button> Add Item </button></a><br><br>
        <a href="EditItem.jsp"><button> Edit Items </button></a><br><br>
        <a href="DeleteItem.jsp"><button> Delete Item </button></a><br><br>
    </body>
</html>