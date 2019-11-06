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

import data.Item;
import ejb.ItemsEJBRemote;

@WebServlet("/SearchByPrice")
public class SearchByPrice extends HttpServlet {
 private static final long serialVersionUID = 1L;
 @EJB
 ItemsEJBRemote ejbremote;

 /**
  * @see HttpServlet#HttpServlet()
  */
 public SearchByPrice() {
  super();
 }

 /**
  * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
  */
 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    PrintWriter out = response.getWriter();
    response.setContentType("text/html");

    Float lowestPrice = Float.parseFloat(request.getParameter("lowestPrice"));
    Float highestPrice = Float.parseFloat(request.getParameter("highestPrice"));



    List<Item> items = ejbremote.getItemsByPrice(lowestPrice, highestPrice);

    if((Integer.parseInt(request.getParameter("order")) == 0)){
      //ordena mais antigo -> recente (primeiro por data, depois por id caso sejam iguais)
      items.sort(Comparator.comparing(Item::getInsertionDate).thenComparing(Item::getId));
    }
    else if((Integer.parseInt(request.getParameter("order")) == 1)){
        //ordena mais recente -> antigo (primeiro por data, depois por id caso sejam iguais)
        items.sort(Comparator.comparing(Item::getInsertionDate).reversed().thenComparing(Item::getId));
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

    

    //DATA ORDERING BUTTONS
    out.println("<div style=position:absolute;top:10px;right:178px>Order By Date:");
    out.println("<form action=SearchByPrice><input type=hidden name=order value=0></input><input type=hidden name=lowestPrice value="+lowestPrice+"></input><input type=hidden name=highestPrice value="+highestPrice+"></input><button type=submit> Older To Recent </button><br></form><form action=SearchByPrice><input type=hidden name=order value=1></input><input type=hidden name=lowestPrice value="+lowestPrice+"></input><input type=hidden name=highestPrice value="+highestPrice+"></input><button type=submit> Recent To Older </button><br><br></form>");
    out.println("</div>");

    //PRICE ORDERING BUTTONS
    out.println("<div style=position:absolute;top:100px;right:100px>Order By Price:");
    out.println("<form action=SearchByPrice><input type=hidden name=order value=2></input><input type=hidden name=lowestPrice value="+lowestPrice+"></input><input type=hidden name=highestPrice value="+highestPrice+"></input><button type=submit> Cheapest To Most Expensive </button><br></form><form action=SearchByPrice><input type=hidden name=order value=3></input><input type=hidden name=lowestPrice value="+lowestPrice+"></input><input type=hidden name=highestPrice value="+highestPrice+"></input><button type=submit> Most Expensive To Cheapest  </button><br><br></form>");
    out.println("</div>");

    //NAME ORDERING BUTTONS
    out.println("<div style=position:absolute;top:190px;right:188px>Order By Price:");
    out.println("<form action=SearchByPrice><input type=hidden name=order value=4></input><input type=hidden name=lowestPrice value="+lowestPrice+"></input><input type=hidden name=highestPrice value="+highestPrice+"></input><button type=submit> A to Z </button><br></form><form action=SearchByPrice><input type=hidden name=order value=5></input><input type=hidden name=lowestPrice value="+lowestPrice+"></input><input type=hidden name=highestPrice value="+highestPrice+"></input><button type=submit> Z to A  </button><br><br></form>");
    out.println("</div>");

    //LOGOUT BUTTON
    out.println("<div style=position:absolute;top:10px;right:10px><a href=InitialMenu.jsp><button> Logout </button></a><br><br></div>");

  }

 /**
  * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
  */
 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  doGet(request, response);
 }

}