package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.Item;
import data.User;
import ejb.ItemsEJBRemote;
import ejb.UsersEJBRemote;

@WebServlet("/ShowItem")
public class ShowItem extends HttpServlet {
 private static final long serialVersionUID = 1L;
 @EJB
 ItemsEJBRemote ejbremote;
 @EJB
 UsersEJBRemote uejbremote;

 /**
  * @see HttpServlet#HttpServlet()
  */
 public ShowItem() {
  super();
 }

 /**
  * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
  */
 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    PrintWriter out = response.getWriter();
    response.setContentType("text/html");

    HttpSession session = request.getSession();
    String email = session.getAttribute("email").toString();

    User user = uejbremote.getUser(email);

    List<Item> userItems = user.getItems();

    int id = Integer.parseInt(request.getParameter("id"));
    session.setAttribute("id", id);
    
    Item itemToDetail = ejbremote.getItem(id);

    out.println("Name: " + itemToDetail.getName());
    out.println("Category: " + itemToDetail.getCategory());
    out.println("Country: " + itemToDetail.getCountryOrigin());
    out.println("Price: " + itemToDetail.getPrice());

    for (Item item : userItems){
        if(item.getId() == id){
            out.println("<br><a href=" + "EditItem.jsp"+"><button> Edit Items </button></a><br><br>");
        }
    }
 }

 /**
  * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
  */
 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  doGet(request, response);
 }

}