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
 * Servlet implementation class GameController
 */
@WebServlet("/game")
public class GameController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GameController() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        if (name == null || name.isEmpty()) {
            response.sendRedirect("index.html");
        }        

        // Get first, if not existed, then create new
        Player me = GameDao.getPlayer(name);
        if (me == null)
        {
        	me = new Player();
            me.setName(name);
            GameDao.addPlayer(me);
        }       	
        
        // Set to session
        HttpSession session = request.getSession();
        session.setAttribute("me", me);
        
        // Redirect to the main game
        response.sendRedirect("game.jsp");
    }
}
