<%-- 
    Document   : TestJSP
    Created on : Oct 18, 2016, 7:40:59 PM
    Author     : mzijlstra
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:out value="<script src='http://evil.com/awesome.js'></script>" />
        <% for (int i = 0; i < 10; i++) { %>
            <h1>Hello World!</h1>
        <% } %>
        
        <%= request.getParameter("test") %>
        <div>This is a change</div>
    </body>
</html>
