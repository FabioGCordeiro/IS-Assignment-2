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

@WebServlet("/SearchByCountry")
public class SearchByCountry extends HttpServlet {
 private static final long serialVersionUID = 1L;
 @EJB
 ItemsEJBRemote ejbremote;

 /**
  * @see HttpServlet#HttpServlet()
  */
 public SearchByCountry() {
  super();
 }

 /**
  * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
  */
 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    PrintWriter out = response.getWriter();
    response.setContentType("text/html");

    HttpSession session = request.getSession();
    String country = session.getAttribute("country").toString();

    List<Item> countryItems = ejbremote.getItemsByCountry(country);

    if((Integer.parseInt(request.getParameter("order")) == 1)){
      //ordena mais recente -> antigo (primeiro por data, depois por id caso sejam iguais)
      countryItems.sort(Comparator.comparing(Item::getInsertionDate).thenComparing(Item::getId).reversed());
    }
    else{
      //ordena mais antigo -> recente (primeiro por data, depois por id caso sejam iguais)
      countryItems.sort(Comparator.comparing(Item::getInsertionDate).thenComparing(Item::getId));
    }

    for (Item item : countryItems) {
        out.println("<form action=ShowItem><input type=hidden name=id value="+item.getId()+"></input><button type=submit> " + item.getName() + "</button></form>");
    }

    //DATA ORDERING BUTTONS
    out.println("<form action=SearchByCountry><input type=hidden name=order value=0></input><button type=submit> Older To Recent </button><br><br></form><form action=SearchByCountry><input type=hidden name=order value=1></input><button type=submit> Recent To Older </button><br><br></form>");
 }

 /**
  * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
  */
 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  doGet(request, response);
 }

}