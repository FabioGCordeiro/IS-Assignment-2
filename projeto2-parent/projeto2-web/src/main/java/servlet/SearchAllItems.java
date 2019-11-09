package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

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

 Logger logger = Logger.getLogger(Register.class.getName());

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
          logger.info("Sorting from most recent to most old");
        }
        else if((Integer.parseInt(request.getParameter("order")) == 1)){
            //ordena mais recente -> antigo (primeiro por data, depois por id caso sejam iguais)
            items.sort(Comparator.comparing(Item::getInsertionDate).thenComparing(Item::getId).reversed());
            logger.info("Sorting from most old to most most");
        }
        else if((Integer.parseInt(request.getParameter("order")) == 2)){
          //ordena mais barato -> caro (primeiro por data, depois por id caso sejam iguais)
          items.sort(Comparator.comparing(Item::getPrice).thenComparing(Item::getId));
          logger.info("Sorting from cheapest to most expensive");
        }
        else if((Integer.parseInt(request.getParameter("order")) == 3)){
          //ordena mais caro -> barato (primeiro por data, depois por id caso sejam iguais)
          items.sort(Comparator.comparing(Item::getPrice).reversed().thenComparing(Item::getId));
          logger.info("Sorting from most expensive to cheapest");
        }
        else if((Integer.parseInt(request.getParameter("order")) == 4)){
          //ordena mais A -> Z (primeiro por data, depois por id caso sejam iguais)
          items.sort(Comparator.comparing(Item::getName,String.CASE_INSENSITIVE_ORDER).thenComparing(Item::getId));
          logger.info("Sorting from A to Z");
        }
        else if((Integer.parseInt(request.getParameter("order")) == 5)){
          //ordena mais Z -> A (primeiro por data, depois por id caso sejam iguais)
          items.sort(Comparator.comparing(Item::getName,String.CASE_INSENSITIVE_ORDER).reversed().thenComparing(Item::getId));
          logger.info("Sorting from Z to A");
        }
          
        for (Item item : items) {
            out.println("<form action=ShowItem><input type=hidden name=id value="+item.getId()+"></input><button type=submit> " + item.getName() + "</button></form>");
        }
    
        //SEARCH BAR TO SEARCH PARTIALLY (OR NOT) BY NAME
        out.println("<form action=SearchItemsName>Name:<input type=hidden name=order value=0><input type=text name=name></input><button type=submit value=Search>Search</button></form>");
    
        //DATA ORDERING BUTTONS
        out.println("<div style=position:absolute;top:10px;right:178px>Order By Date:");
        out.println("<form action=SearchAllItems><input type=hidden name=order value=0><button type=submit> Older To Recent </button><br></form><form action=SearchAllItems><input type=hidden name=order value=1></input><button type=submit> Recent To Older </button><br><br></form>");
        out.println("</div>");
    
        //PRICE ORDERING BUTTONS
        out.println("<div style=position:absolute;top:100px;right:100px>Order By Price:");
        out.println("<form action=SearchAllItems><input type=hidden name=order value=2><button type=submit> Cheapest To Most Expensive </button><br></form><form action=SearchAllItems><input type=hidden name=order value=3></input><button type=submit> Most Expensive To Cheapest  </button><br><br></form>");
        out.println("</div>");
    
        //NAME ORDERING BUTTONS
        out.println("<div style=position:absolute;top:190px;right:188px>Order By Name:");
        out.println("<form action=SearchAllItems><input type=hidden name=order value=4></input><button type=submit> A to Z </button><br></form><form action=SearchAllItems><input type=hidden name=order value=5><button type=submit> Z to A  </button><br><br></form>");
        out.println("</div>");
    
        //LOGOUT BUTTON
        out.println("<div style=position:absolute;top:10px;right:10px><a href=InitialMenu.jsp><button> Logout </button></a><br><br></div>");  
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