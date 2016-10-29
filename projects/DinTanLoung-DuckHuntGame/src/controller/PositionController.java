package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GameDao;
import model.Player;
/**
 * Servlet implementation class PositionController
 */
@WebServlet("/position")
public class PositionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    // Update player position and return a list of players in JSON
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {    	
    	
        HttpSession session = request.getSession();
        Player me = (Player) session.getAttribute("me");
        
        // Save position for current player
        if (me != null)
        {
        	int x = request.getParameter("x") == null ? 0 : Integer.parseInt(request.getParameter("x"));
        	int y = request.getParameter("y") == null ? 0 : Integer.parseInt(request.getParameter("y"));
            
            Player player = GameDao.getPlayer(me.getName());
            
            if (player != null)
            {
            	player.setX(x);
                player.setY(y);
            }             
        }        
        
        // Set players list
        request.setAttribute("players", GameDao.Players());
        
        // show json 
        request.getRequestDispatcher("positions.jsp").forward(request, response);
    }

}
