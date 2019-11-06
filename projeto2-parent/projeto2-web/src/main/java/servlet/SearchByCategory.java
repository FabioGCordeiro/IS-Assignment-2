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

@WebServlet("/SearchByCategory")
public class SearchByCategory extends HttpServlet {
 private static final long serialVersionUID = 1L;
 @EJB
 ItemsEJBRemote ejbremote;

 /**
  * @see HttpServlet#HttpServlet()
  */
 public SearchByCategory() {
  super();
 }

 /**
  * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
  */
 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    PrintWriter out = response.getWriter();
    response.setContentType("text/html");

    String category = request.getParameter("category");

    List<Item> categoryItems = ejbremote.getItemsByCategory(category);

    if((Integer.parseInt(request.getParameter("order")) == 1)){
      //ordena mais recente -> antigo (primeiro por data, depois por id caso sejam iguais)
      categoryItems.sort(Comparator.comparing(Item::getInsertionDate).thenComparing(Item::getId).reversed());
    }
    else{
      //ordena mais antigo -> recente (primeiro por data, depois por id caso sejam iguais)
      categoryItems.sort(Comparator.comparing(Item::getInsertionDate).thenComparing(Item::getId));
    }
      
    for (Item item : categoryItems) {
        out.println("<form action=ShowItem><input type=hidden name=id value="+item.getId()+"></input><button type=submit> " + item.getName() + "</button></form>");
    }

    //DATA ORDERING BUTTONS
    out.println("<form action=SearchByCategory><input type=hidden name=order value=0></input><input type=hidden name=category value="+category+"></input><button type=submit> Older To Recent </button><br><br></form><form action=SearchByCategory><input type=hidden name=order value=1></input><input type=hidden name=category value="+category+"></input><button type=submit> Recent To Older </button><br><br></form>");

    
 }

 /**
  * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
  */
 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  doGet(request, response);
 }

}