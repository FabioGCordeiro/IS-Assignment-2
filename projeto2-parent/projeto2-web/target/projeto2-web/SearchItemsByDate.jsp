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
            Date:
            <input type="text" name = "date"><br><br>
            <input type="hidden" name="order" value=0></input>
            <input type="submit" value="Search">
        </form>

        <div style=position:absolute;top:10px;right:10px>
            <a href=InitialMenu.jsp><button> Logout </button></a><br><br>
        </div>

    </body>
</html>