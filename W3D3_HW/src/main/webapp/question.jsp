<%-- 
    Document   : question
    Created on : Oct 19, 2016, 2:33:09 PM
    Author     : mzijlstra
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exam Question</title>
        <link href="style.css" type="text/css" rel="stylesheet" />
    </head>
    <body>
        <div class="bg"></div>
        <div class="container question">
            <p>${questions[qIdx]}</p>

            <c:if test="${session.error}">
                <div class="error">${session.error}</div>
                <c:remove scope="session" var="error" />
            </c:if>

            <form action="submit-question" method="post">
                <textarea name="answer" autofocus="autofocus"></textarea><br />
                <input type="submit" value="Submit" />
            </form>
        </div>
    </body>
</html>
