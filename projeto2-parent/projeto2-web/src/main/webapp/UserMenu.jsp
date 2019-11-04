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
        <title>MyBay - User Menu</title>
    </head>
    <body>
        <h1>Main Menu</h1>
        <a href="EditAccountInformation.jsp"><button> Edit Account Information </button></a><br><br>
        <a href="DeleteAccount.jsp"><button> Delete Account </button></a><br><br>
        <a href="InitialMenu.jsp"><button> Logout </button></a><br><br>
    </body>
</html>