package servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ejb.UsersEJBRemote;

@WebServlet("/Login")
public class Login extends HttpServlet {
 private static final long serialVersionUID = 1L;
 @EJB
 UsersEJBRemote ejbremote;

 /**
  * @see HttpServlet#HttpServlet()
  */
 public Login() {
  super();
 }

 /**
  * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
  */
 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  response.setContentType("text/html");

  String email = request.getParameter("email");
  String password = request.getParameter("password");

  HttpSession session=request.getSession();  
  session.setAttribute("email",email);  

  if(ejbremote.checkUserLogin(email, password)){
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