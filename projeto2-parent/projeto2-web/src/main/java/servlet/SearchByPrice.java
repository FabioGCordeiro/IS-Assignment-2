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

    if((Integer.parseInt(request.getParameter("order")) == 1)){
      //ordena mais recente -> antigo (primeiro por data, depois por id caso sejam iguais)
      items.sort(Comparator.comparing(Item::getInsertionDate).thenComparing(Item::getId).reversed());
  }
  else{
    //ordena mais antigo -> recente (primeiro por data, depois por id caso sejam iguais)
    items.sort(Comparator.comparing(Item::getInsertionDate).thenComparing(Item::getId));
  }

    for (Item item : items) {
        out.println("<form action=ShowItem><input type=hidden name=id value="+item.getId()+"></input><button type=submit> " + item.getName() + "</button></form>");
    }

    //DATA ORDERING BUTTONS
    out.println("<form action=SearchByPrice><input type=hidden name=order value=0></input><input type=hidden name=highestPrice value="+highestPrice+"></input><input type=hidden name=lowestPrice value="+lowestPrice+"></input><button type=submit> Older To Recent </button><br><br></form><form action=SearchByPrice><input type=hidden name=order value=1></input><input type=hidden name=highestPrice value="+highestPrice+"></input><input type=hidden name=lowestPrice value="+lowestPrice+"></input><button type=submit> Recent To Older </button><br><br></form>");
    
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