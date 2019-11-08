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
        <meta charset="UTF-8">
        <title>MyBay - Add Item</title>
    </head>
    <body>
        <form method="post" action="AddItem" enctype="multipart/form-data">
            <h1>Add Item</h1><br><br>
            Item name:
            <input type="text" name = "name"><br><br>
            Category:
            <input type="text" name = "category"><br><br>
            Country of Origin:
            <input type="text" name = "country"><br><br>
            Price(€):
            <input type="text" name = "price"><br><br>
            Photo:
            <td><input type="file" name = "photo" size="50"></input></td><br><br>
            <input type="submit" value="Add">
            
            <div style="position:absolute;top:10px;right:10px" >
                <a href="InitialMenu.jsp"><button> Logout </button></a><br><br>
            </div>
        </form>
    </body>
</html>