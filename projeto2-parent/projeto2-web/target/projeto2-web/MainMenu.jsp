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
        <title>MyBay - Main Menu</title>
    </head>
    <body>
        <h1>Main Menu</h1>
        <a href="UserMenu.jsp"><button> User Menu </button></a><br><br>
        <a href="ShoppingMenu.jsp"><button> Shopping Menu </button></a><br><br>
        
        <div style="position:absolute;top:10px;right:10px" >
            <a href="InitialMenu.jsp"><button> Logout </button></a><br><br>
        </div>

        <form action="SendEmail">
                <button type=submit value="Send Email">SEND EMAIL</button>
        </form>

    </body>
</html>