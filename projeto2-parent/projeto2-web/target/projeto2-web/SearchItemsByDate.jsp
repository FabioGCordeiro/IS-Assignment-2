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
        <title>MyBay - Seary By Date</title>
    </head>
    <body>
        <form action="SearchByDate">
            <h1>Please insert a date separated by /</h1><br><br>
            Category:
            <input type="text" name = "date"><br><br>
            <input type="hidden" name="order" value=0></input>
            <input type="submit" value="Search">
        </form>

    </body>
</html>