package servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.EmailServiceEJBRemote;;

@WebServlet("/SendEmail")
public class SendEmail extends HttpServlet {
 private static final long serialVersionUID = 1L;
 @EJB
 EmailServiceEJBRemote ejbremote;

 /**
  * @see HttpServlet#HttpServlet()
  */
 public SendEmail() {
  super();
 }

 /**
  * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
  */
 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");

    ejbremote.sendAccountActivationLinkToBuyer("fabiocordeiro1998@gmail.com", "Fabz");
    response.sendRedirect("MainMenu.jsp");
 }


    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
    }

}