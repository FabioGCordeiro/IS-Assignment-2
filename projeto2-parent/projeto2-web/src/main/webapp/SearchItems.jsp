<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" session="false"%>

<%/*
HttpSession session = request.getSession(false);
String user = (String) session.getAttribute("email");

if(user==null){
    response.sendRedirect("Error.jsp");
}*/
%>

<html>
    <title>MyBay</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    
    
    <body >
            <div class="w3-top">
                <div class="w3-row w3-padding w3-black">
                    <div class="w3-col s3 w3-dropdown-hover">
                        <div class="w3-button w3-block w3-black w3-center">Search Items Menu
                        <div class="w3-dropdown-content w3-bar-block w3-border">
                                <a><form action="SearchAllItems">
                                        <input type=hidden name=order value=0></input>
                                        <button type="submit"> Show All Items </button>
                                </form></a>
                                <a href="SearchByCategory.jsp"><button> Search By Category </button></a>
                                <a><form action="SearchByCountry">
                                        <input type=hidden name=order value=0></input>
                                        <button type="submit"> Search By Country </button>
                                </form></a>
                                <a href="SearchItemsByPrice.jsp"><button> Search By Price </button></a>
                                <a href="SearchItemsByDate.jsp"><button> Search Items Posted After Date </button></a>
                              </div></div>
                    </div>
                </div>
                <!--<h1>Welcome to MyBay!</h1><br>
                <img src="myBay.jpg" alt="brand" width="250" height="160"><br><br>-->
                </div>
            
            </div>
            <div class="bgimg w3-display-container">
            </div>
        </body>
</html>

<body>
        <h1>Search Items Menu</h1><br>
        <h3>Choose an option:</h3><br>
        
        <br><br>

        

        
        <div style=position:absolute;top:10px;right:10px>
            <a href=InitialMenu.jsp><button> Logout </button></a><br><br>
        </div>

    </body>