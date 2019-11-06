package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    Float price = Float.parseFloat(request.getParameter("price"));

    Date d = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    String date = formatter.format(d);
   
    String [] dateSplit = date.split(" ");
    String[] daySplit = dateSplit[0].split("-");
    
    int insertionDate = (Integer.parseInt(daySplit[2])*10000) + (Integer.parseInt(daySplit[1])*100) + (Integer.parseInt(daySplit[0]));

    HttpSession session=request.getSession();


    if(ejbremote.createItem(name, category,countryOrigin, price, session.getAttribute("email").toString(),insertionDate)){
      response.sendRedirect("MainMenu.jsp");
    }
    else{
      response.sendRedirect("AddItem.jsp");
    }
  }

  /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

}