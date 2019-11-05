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
        <title>MyBay - Edit Account Information</title>
    </head>
    <body>
        <form action="DeleteAccount">
            <h2>Please enter your credentials to confirm:</h2><br><br>
            Email:
            <input type="text" name = "email"><br><br>
            Password:
            <input type="password" name = "password"><br><br>
            <input type="submit" value="Confirm">
        </form>

        <div style="position:absolute;top:10px;right:10px" >
            <a href="InitialMenu.jsp"><button> Logout </button></a><br><br>
        </div>
    </body>
</html>