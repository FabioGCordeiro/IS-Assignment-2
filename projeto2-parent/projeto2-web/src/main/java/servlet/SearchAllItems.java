package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.Item;
import ejb.ItemsEJBRemote;

@WebServlet("/SearchAllItems")
public class SearchAllItems extends HttpServlet {
 private static final long serialVersionUID = 1L;
 @EJB
 ItemsEJBRemote ejbremote;

 /**
  * @see HttpServlet#HttpServlet()
  */
 public SearchAllItems() {
  super();
 }

 /**
  * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
  */
 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    PrintWriter out = response.getWriter();
    response.setContentType("text/html; charset=UTF-8");

    try{
      HttpSession session = request.getSession();
      String email = session.getAttribute("email").toString();
  
      List<Item> items = ejbremote.getItems();
  
      if(items==null){
        out.print("<h1>There are no items in the database.</h1> ");
        out.println("<div style=position:absolute;top:10px;right:10px><a href=InitialMenu.jsp><button> Logout </button></a><br><br></div>");
  
      }
      else{
        if((Integer.parseInt(request.getParameter("order")) == 0)){
          //ordena mais antigo -> recente (primeiro por data, depois por id caso sejam iguais)
          items.sort(Comparator.comparing(Item::getInsertionDate).thenComparing(Item::getId));
        }
        else if((Integer.parseInt(request.getParameter("order")) == 1)){
            //ordena mais recente -> antigo (primeiro por data, depois por id caso sejam iguais)
            items.sort(Comparator.comparing(Item::getInsertionDate).thenComparing(Item::getId).reversed());
        }
        else if((Integer.parseInt(request.getParameter("order")) == 2)){
          //ordena mais barato -> caro (primeiro por data, depois por id caso sejam iguais)
          items.sort(Comparator.comparing(Item::getPrice).thenComparing(Item::getId));
        }
        else if((Integer.parseInt(request.getParameter("order")) == 3)){
          //ordena mais caro -> barato (primeiro por data, depois por id caso sejam iguais)
          items.sort(Comparator.comparing(Item::getPrice).reversed().thenComparing(Item::getId));
        }
        else if((Integer.parseInt(request.getParameter("order")) == 4)){
          //ordena mais A -> Z (primeiro por data, depois por id caso sejam iguais)
          items.sort(Comparator.comparing(Item::getName,String.CASE_INSENSITIVE_ORDER).thenComparing(Item::getId));
        }
        else if((Integer.parseInt(request.getParameter("order")) == 5)){
          //ordena mais Z -> A (primeiro por data, depois por id caso sejam iguais)
          items.sort(Comparator.comparing(Item::getName,String.CASE_INSENSITIVE_ORDER).reversed().thenComparing(Item::getId));
        }
          
        for (Item item : items) {
            out.println("<form action=ShowItem><input type=hidden name=id value="+item.getId()+"></input><button type=submit> " + item.getName() + "</button></form>");
        }
    
        //SEARCH BAR TO SEARCH PARTIALLY (OR NOT) BY NAME
        out.println("<form action=SearchItemsName>Name:<input type=hidden name=order value=0><input type=text name=name></input><button class='w3-btn w3-xlarge w3-round-xlarge w3-deep-purple w3-hover-black' type=submit value=Search>Search</button></form>");
    

        out.println("<html><title>MyBay - Search</title><meta name='viewport' content='width=device-width, initial-scale=1'><link rel='stylesheet' href=../lib/w3.css'><link rel='stylesheet' href='https://www.w3schools.com/w3css/4/w3.css'>");
        out.println("<body><div class='w3-container'><div class='w3-dropdown-hover w3-row we3-right' style=position:absolute;top:0px;right:90px><button class='w3-button w3-black'>Order by:</button><div class='w3-dropdown-content w3-bar-block w3-border'><form action=ShowUserItems><input type=hidden name=order value=0><button type=submit> Older To Recent </button><br></form><form action=ShowUserItems><input type=hidden name=order value=1></input><button type=submit> Recent To Older </button><br><br></form><form action=ShowUserItems><input type=hidden name=order value=4></input><button type=submit> A to Z </button><br></form><form action=ShowUserItems><input type=hidden name=order value=5><button type=submit> Z to A  </button></form><form action=ShowUserItems><input type=hidden name=order value=2><button type=submit> Cheapest To Most Expensive </button><br></form><form action=ShowUserItems><input type=hidden name=order value=3></input><button type=submit> Most Expensive To Cheapest  </button></form></div></div></div></body></html>");
        //LOGOUT BUTTON
        out.println("<div style=position:absolute;bottom:10px;left:45%><a href=InitialMenu.jsp><button class='w3-btn w3-xlarge w3-round-xlarge w3-deep-purple w3-hover-black' style='font-weight:900;'> Logout </button></a><br><br></div>");
    
    }
  }catch(NullPointerException | NumberFormatException e){
      response.sendRedirect("Error.jsp");
  }
    

  }

 /**
  * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
  */
 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  doGet(request, response);
 }

}