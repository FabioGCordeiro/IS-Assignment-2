package servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ejb.ItemsEJBRemote;;

@WebServlet("/AddItem")
public class AddItem extends HttpServlet {
 private static final long serialVersionUID = 1L;
 @EJB
 ItemsEJBRemote ejbremote;

 /**
  * @see HttpServlet#HttpServlet()
  */
 public AddItem() {
  super();
 }

 /**
  * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
  */
 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  response.setContentType("text/html");

  String name = request.getParameter("name");
  String category = request.getParameter("category");
  String countryOrigin = request.getParameter("country");

  HttpSession session=request.getSession();


  if(ejbremote.createItem(name, category,countryOrigin,session.getAttribute("email").toString())){
    response.sendRedirect("MainMenu.jsp");
  }
  else{
    response.sendRedirect("Login.jsp");
  }
 }

 /**
  * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
  */
 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  response.sendRedirect("Login.jsp");
  doGet(request, response);
 }

}