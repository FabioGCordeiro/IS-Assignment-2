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
        <a href="AddItem.jsp"><button> Add Item </button></a><br><br>
        <form action="ShowUserItems">
            <input type=hidden name=order value=0></input>
            <button type="submit"> Show Items </button><br><br>
        </form>

        <div style="position:absolute;top:10px;right:10px" >
            <a href="InitialMenu.jsp"><button> Logout </button></a><br><br>
        </div>
    </body>

</html>