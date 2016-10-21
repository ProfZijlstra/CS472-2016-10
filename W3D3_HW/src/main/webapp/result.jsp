<%-- 
    Document   : result
    Created on : Oct 19, 2016, 3:46:34 PM
    Author     : mzijlstra
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="style.css" type="text/css" rel="stylesheet" />        
        <title>Result</title>
    </head>
    <body>
        <div class="bg"></div>
        <h1>Your Answers</h1>
        <div class="container result">
            <h2>${cookie.name.value}, ${sid}</h2>    

            <c:forEach items="${students[sid].answers}" var="entry">
                <div class="question">${entry.key}</div>
                <div class="answer">${entry.value}</div>
            </c:forEach>

            <c:set scope="page" value="${students[sid].stop.time - students[sid].start.time}" var="milis" />
            <h3>Time to complete: 
                <fmt:formatNumber value="${milis / 3600000}" minIntegerDigits="2" maxFractionDigits="0" />:<fmt:formatNumber value="${(milis % 3600000) / 60000}" minIntegerDigits="2" maxFractionDigits="0" />:<fmt:formatNumber value="${(milis % 60000) / 1000}" minIntegerDigits="2" maxFractionDigits="0" />
            </h3>
        </div>
    </body>
</html>
