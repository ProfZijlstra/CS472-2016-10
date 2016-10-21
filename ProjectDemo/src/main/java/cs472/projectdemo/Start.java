/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs472.projectdemo;

import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mzijlstra
 */
@WebServlet(name = "Start", urlPatterns = {"/start"})
public class Start extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        if (name == null || name.isEmpty()) {
            response.sendRedirect("index.html");
        }

        HttpSession session = request.getSession();
        session.setAttribute("name", name);

        ServletContext context = getServletContext();
        HashMap<String, Player> players 
                = (HashMap<String, Player>) context.getAttribute("players");
        Player me = new Player();
        me.setName(name);
        players.put(name, me);
        
        response.sendRedirect("game.jsp");
    }

    @Override
    public void init() {
        ServletContext context = getServletContext();
        if (context.getAttribute("players") == null) {
            context.setAttribute("players", new HashMap<String, Player>());
        }
    }

}
