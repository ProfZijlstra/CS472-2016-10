/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs472.w3d3_hw;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mzijlstra
 */
@WebServlet(name = "start", urlPatterns = {"/start"})
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
        String sid = request.getParameter("sid");
        HttpSession session = request.getSession();

        StringBuilder error = new StringBuilder();
        if (name == null || name.isEmpty()) {
            error.append("Name is missing<br />");
        }
        if (sid == null || sid.isEmpty()) {
            error.append("Student ID is missing <br />");
        }
        if (error.length() != 0) {
            session.setAttribute("error", error.toString());
            response.sendRedirect("index.jsp");
            return;
        }

        // store the student's name in a cookie
        Cookie cookie = new Cookie("name", name);
        response.addCookie(cookie);

        // store the studentId in the session
        session.setAttribute("sid", sid);

        // get the students 'database'
        ServletContext context = getServletContext();
        Map<String, Student> students
                = (Map<String, Student>) context.getAttribute("students");
        List<String> questions = (List<String>) context.getAttribute("questions");

        // check if this user has started before
        if (students.get(sid) != null) {
            Student student = students.get(sid);

            // check if he has finished
            if (student.getAnswers().size() == questions.size()) {
                // send to result, can't do it again
                response.sendRedirect("result.jsp");
            } else {
                // send to current unanswered question
                // TODO: may note have questions and qIdx in session!
                response.sendRedirect("question.jsp");
            }
            return;
        }

        // start the new student (creating the object sets start timestamp)
        students.put(sid, new Student());

        // randomize the questions, and add these to the session
        List<String> clone = new ArrayList<>();
        for (String q : questions) {
            clone.add(q);
        }
        Collections.shuffle(clone);
        session.setAttribute("questions", clone);
        session.setAttribute("qIdx", 0);

        // send him on to start the exam
        response.sendRedirect("question.jsp");
    }

    @Override
    public void init() {
        // create question list
        List<String> questions = new ArrayList<>();
        questions.add("Explain first thing:");
        questions.add("Explain second thing:");
        questions.add("Explain third thing:");
        questions.add("Explain fourth thing:");
        questions.add("Explain fifth thing:");

        // create students data structure
        // studentId to Student Object
        Map<String, Student> students = new HashMap<>();

        // add them to the application scope if they're not there yet
        ServletContext context = getServletContext();
        if (context.getAttribute("questions") == null) {
            context.setAttribute("questions", questions);
        }
        if (context.getAttribute("students") == null) {
            context.setAttribute("students", students);
        }
    }
}
