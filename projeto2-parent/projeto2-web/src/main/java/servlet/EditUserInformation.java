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

@WebServlet("/EditUserInformation")
public class EditUserInformation extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB
    UsersEJBRemote ejbremote;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditUserInformation() {
    super();
 }

 /**
  * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
  */
 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  response.setContentType("text/html");

  if(!request.getParameter("email").equals("") && !request.getParameter("password").equals("") && !request.getParameter("username").equals("") && !request.getParameter("country").equals("")){
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String username = request.getParameter("username");
    String country = request.getParameter("country");

    HttpSession session = request.getSession();
    String emailSession = session.getAttribute("email").toString();
    
    
    if(ejbremote.editUserInfo(username, password, email, country, emailSession)){
      session.setAttribute("country",country);
      session.setAttribute("email", email);
      response.sendRedirect("UserMenu.jsp");
    }
  }
  else{
    response.sendRedirect("EditUserInformation.jsp");
  }
 }

 /**
  * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
  */
 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  doGet(request, response);
 }

}