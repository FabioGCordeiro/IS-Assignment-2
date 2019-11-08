<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" session="false"%>

<%
/*HttpSession session = request.getSession(false);
String user = (String) session.getAttribute("email");

if(user==null){
    response.sendRedirect("Error.jsp");
}*/
%>


<html>
<<<<<<< HEAD
<title>MyBay - UserMenu</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../lib/w3.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<style>
        .dropbtn {
          background-color:black;
          color: white;
          padding: 16px;
          font-size: 16px;
          block-size: 150px;
          inline-size: 1900px;
          border: none;
          cursor: pointer;
        }
        
        .dropdown {
          position: relative;
          display: inline-block;
        }
        
        .dropdown-content {
          display: none;
          position: absolute;
          background-color: rgb(77, 1, 77);
          text-align: center;
          min-width: 1900px;
          box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.918);
          z-index: 1;
        }
        
        .dropdown-content a {
          color: black;
          padding: 50px 16px;
          min-height: 100px;
          text-decoration: none;
          display: block;
        }


        .dropdown-content a:hover {background-color: #f1f1f1}
        
        .dropdown:hover .dropdown-content {
          display: block;
        }
        
</style>
        
        
=======
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
>>>>>>> master

    <body style="background-image: url('myBayFinal2.jpg');">
        <div class="dropdown">
        <button class="dropbtn w3-xxxlarge" style="font-weight:900">User Menu</button>
        <div class="dropdown-content">
            <a href="EditAccountInformation.jsp"><button class="w3-btn w3-xlarge w3-round-xlarge w3-white w3-hover-black " style="font-weight:900;"> Edit Account Information </button></a>
            <a href="DeleteAccount.jsp"><button class="w3-btn w3-xlarge w3-round-xlarge w3-white w3-hover-black" style="font-weight:900;"> Delete Account </button></a>
            <a><form action="ShowUserItems">
                    <input type=hidden name=order value=0></input>
                    <button class="w3-btn w3-xlarge w3-round-xlarge w3-white w3-hover-black" style="font-weight:900;" type="submit"> Show Items </button> 
            </form></a>
        </div>
        </div>
        <br><br>
        <br><br>
        <div style=position:absolute;bottom:10px;left:50%>
                <a href=InitialMenu.jsp><button class="w3-btn w3-xlarge w3-round-xlarge w3-deep-purple w3-hover-black" style="font-weight:900;"> Logout </button></a><br><br>
        </div>
    </body>
</html>