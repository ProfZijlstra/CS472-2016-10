<%-- 
    Document   : game
    Created on : Oct 21, 2016, 7:20:18 AM
    Author     : mzijlstra
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Game Skeleton</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
        <script src="game.js"></script>
        <link rel="stylesheet" href="game.css" type="text/css" />
    </head>
    <body>
        <input id="name" type="hidden" value="${name}" />
        <div id="container" class="container">
            <c:forEach var="player" items="${players}">
                <div class="dude" id="${player.key}" 
                     style="left: ${player.value.x}; top: ${player.value.y}">
                    ${player.key}
                </div>
            </c:forEach>
        </div>
    </body>
</html>
