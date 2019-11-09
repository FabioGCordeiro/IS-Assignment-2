<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" session="false"%>


<%
HttpSession session = request.getSession(false);
String user = (String) session.getAttribute("email");

if(user==null){
    response.sendRedirect("Error.jsp");
}
%>


<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>
    <title>MyBay - Search</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel='stylesheet' href=../lib/w3.css>
    <link rel='stylesheet' href='https://www.w3schools.com/w3css/4/w3.css'>
<body>
    <c:forEach items="${items}" var="post">
        <button>${post.name}</button>
    </c:forEach>

    <form action=SearchItemsName>
        Name:
        <input type=hidden name=order value=0>
        <input type=text name=name></input>
        <button class='w3-btn w3-xlarge w3-round-xlarge w3-deep-purple w3-hover-black' type=submit value=Search>Search</button>
    </form>
    <div class='w3-container'>
        <div class='w3-dropdown-hover w3-row we3-right' style=position:absolute;top:0px;right:90px>
            <button class='w3-button w3-black'>Order by:</button>
            <div class='w3-dropdown-content w3-bar-block w3-border'>
                <form action=SearchAllItems>
                    <input type=hidden name=order value=0>
                    <button type=submit> Older To Recent </button><br>
                </form>
                <form action=SearchAllItems>
                    <input type=hidden name=order value=1></input>
                    <button type=submit> Recent To Older </button><br><br>
                </form>
                <form action=SearchAllItems>
                    <input type=hidden name=order value=4></input>
                    <button type=submit> A to Z </button><br>
                </form>
                <form action=SearchAllItems>
                    <input type=hidden name=order value=5>
                    <button type=submit> Z to A  </button>
                </form>
                <form action=SearchAllItems>
                    <input type=hidden name=order value=2>
                    <button type=submit> Cheapest To Most Expensive </button><br>
                </form>
                <form action=SearchAllItems>
                    <input type=hidden name=order value=3></input>
                    <button type=submit> Most Expensive To Cheapest  </button>
                </form>
                </div>
            </div>
        </div>
        <div style=position:absolute;bottom:10px;left:45%>
            <a href=InitialMenu.jsp>
                <button class='w3-btn w3-xlarge w3-round-xlarge w3-deep-purple w3-hover-black' style='font-weight:900;'> Logout </button>
            </a>
            <br><br>
        </div>
    </body>
</html>