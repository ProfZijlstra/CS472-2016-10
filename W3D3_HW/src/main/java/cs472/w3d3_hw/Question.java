/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs472.w3d3_hw;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
@WebServlet(name = "Question", urlPatterns = {"/submit-question"})
public class Question extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        // check input
        String answer = request.getParameter("answer");
        if (answer == null || answer.isEmpty()) {
            session.setAttribute("error", "Asnwer is empty or missing");
            response.sendRedirect("question.jsp");
            return;
        }

        // get the current question
        List<String> questions = (List<String>) session.getAttribute("questions");
        int qIdx = (int) session.getAttribute("qIdx");

        // check for error condition, qIdx already beyond end of list
        if (qIdx >= questions.size()) {
            response.sendRedirect("result.jsp");
            return;
        }
        String question = questions.get(qIdx);

        // get the current studentId
        String sid = (String) session.getAttribute("sid");

        // set the answer in the 'database'
        ServletContext context = getServletContext();
        Map<String, Student> students = (Map<String, Student>) context.getAttribute("students");
        if (students.get(sid) == null) {
            // error condition, how did he submit without being logged in?
            // TODO write code to show error
        }

        Student student = (Student) students.get(sid);
        student.getAnswers().put(question, answer);

        qIdx += 1;
        session.setAttribute("qIdx", qIdx);
        // if last question show result
        if (qIdx >= questions.size()) {
            student.setStop(new Date());
            response.sendRedirect("result.jsp");
        } else {
            response.sendRedirect("question.jsp");
        }
    }
}
