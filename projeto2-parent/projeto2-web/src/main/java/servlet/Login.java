package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.UsersEJBRemote;


// http://127.0.0.1:8080/project2-web/PlayersTallerThan?fill=1
// url = http://127.0.0.1:8080/project2-web/PlayersTallerThan?height=1.80
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

  String username = request.getParameter("username");
  String password = request.getParameter("password");

  //metodo para checkar se o login foi bem sucedido ou não e se sim
  response.sendRedirect("MainMenu.jsp");

 }

 /**
  * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
  */
 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  response.sendRedirect("Login.jsp");
  doGet(request, response);
 }

}