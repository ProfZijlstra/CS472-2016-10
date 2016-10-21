<%-- 
    Document   : index.jsp
    Created on : Oct 19, 2016, 2:03:11 PM
    Author     : mzijlstra
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Start Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="style.css" type="text/css" rel="stylesheet" />
    </head>
    <body>
        <div class="bg"></div>
        <div class="container start">

            <c:if test="${error != null}">
                <div class="error">${error}</div>
                <c:remove scope="session" var="error" />
            </c:if>

            <form method="post" action="start">
                <label for="name">Full Name:</label><input id="name" autofocus="autofocus" type="text" placeholder="Firstname Lastname" name="name" /><br />
                <label for="sid">Student ID:</label><input id="sid" type="text" name="sid" placeholder="000000" /> <br />
                <input id="begin" type="submit" value="Start Exam"/>
            </form>
        </div>
    </body>
</html>
