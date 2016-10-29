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
 * Player Servlet
 * Remove user from the list
 */
@WebServlet("/player")
public class PlayerController extends HttpServlet {
	private static final long serialVersionUID = 1L;      

	// When quit the game, remove 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        Player me = (Player) session.getAttribute("me");
        
        if (me != null)
        {
        	GameDao.removePlayer(me.getName());       	
        	session.removeAttribute("me");
        }
	}

}
