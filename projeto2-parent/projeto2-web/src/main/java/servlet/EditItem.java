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

@WebServlet("/EditItem")
public class EditItem extends HttpServlet {
 private static final long serialVersionUID = 1L;
 @EJB
 ItemsEJBRemote ejbremote;

 /**
  * @see HttpServlet#HttpServlet()
  */
 public EditItem() {
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
    Float price = Float.parseFloat(request.getParameter("price"));

    HttpSession session = request.getSession();
    int id = Integer.parseInt(session.getAttribute("id").toString());
    

    if(ejbremote.editItem(name, category,countryOrigin, price, id)){
        session.setAttribute("id", "");
        response.sendRedirect("MainMenu.jsp");
    }
    else{
        response.sendRedirect("EditItem.jsp");
    }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
    }

}