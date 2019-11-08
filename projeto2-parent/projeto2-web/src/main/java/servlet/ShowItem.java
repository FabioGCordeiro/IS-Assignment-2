package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
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
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        HttpSession session = request.getSession();
        String email = session.getAttribute("email").toString();

        User user = uejbremote.getUser(email);
        List<Item> userItems = user.getItems();

        int id = Integer.parseInt(request.getParameter("id"));
        session.setAttribute("id", id);

        Item itemToDetail = ejbremote.getItem(id);
        PrintWriter out = response.getWriter();
        
        //CREATE BASE64 IMAGE URL
        byte[] imgData = itemToDetail.getPhoto();
        String encodedImage = Base64.getEncoder().encodeToString(imgData);
        String url = "data:image/jpg;base64," + encodedImage;

        //DISPLAY ITEM INFORMATION + IMAGE + BUTTON IF IT IS A USER'S ITEM
        out.println(itemToDetail.toString() + "<div><img src=" + url + " width=100 height=100></img></div><br>");
        for (Item item : userItems){
            if(item.getId() == id){
                out.println("<a href=" + "EditItem.jsp"+"><button> Edit Item</button></a><br><br>");
                out.println("<a href=" + "DeleteItem.jsp"+"><button>Delete Item</button></a><br><br>");
            }
        }

        //LOGOUT BUTTON
        out.println("<div style=position:absolute;top:10px;right:10px><a href=InitialMenu.jsp><button> Logout </button></a><br><br></div>");

        out.close();
 }

 /**
  * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
  */
 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  doGet(request, response);
 }

}