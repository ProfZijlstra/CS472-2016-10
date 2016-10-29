package controller;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Duck;
import model.GameDao;
import model.Player;

/**
 * Duck servlet
 * Return Ducks list or Duck status
 */
@WebServlet("/duck")
public class DuckController extends HttpServlet {
	private static final long serialVersionUID = 1L;      
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		// Return duck list
		if (action.equals("list"))
		{
			if (GameDao.Ducks().size() == 0)
				GameDao.generateDucks();
			
			request.setAttribute("ducks", GameDao.Ducks());
			
	        // show json 
	        request.getRequestDispatcher("ducks.jsp").forward(request, response);
		}
		// Check if duck is alive or not
		else if (action.equals("status"))
		{
			String id = request.getParameter("id");
			Optional<Duck> duck = GameDao.Ducks().stream().filter(d -> d.getId() == Integer.parseInt(id))
							.findAny();
			
			response.setContentType("text");
			
			if (!duck.isPresent())
				response.getWriter().print("0");
			else
				response.getWriter().print("1");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String playerId = request.getParameter("playerid");
		
		if (id != null && playerId != null)
		{
			for (Duck duck : GameDao.Ducks())
			{
				if (duck.getId() == Integer.parseInt(id))
				{
					duck.setDead(true);
					Player player = GameDao.getPlayer(playerId);
					if (player != null)
					{
						player.setScore(player.getScore() + 100);
					}
				}
			}
			
			GameDao.removeDucks();
		}
		
//		Optional<Duck> duck = GameDao.Ducks().stream().filter(d -> d.getId() == Integer.parseInt(id))
//				.findAny();
		
//		if (duck.isPresent())
//		{
//			duck.get().setDead(true);
//			GameDao.removeDucks();
//		}		
	}
}
