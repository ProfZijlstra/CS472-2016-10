<%-- 
    Document   : positions
    Created on : Oct 21, 2016, 7:42:18 AM
    Author     : mzijlstra
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/json" pageEncoding="UTF-8"%>
<c:set var="first" value="true" scope="page" />
{
<c:forEach var="player" items="${players}">
    <c:if test="${first == false}">,</c:if>
    <c:if test="${first != false}"><c:set var="first" scope="page" value="false" /></c:if>
    "${player.key}": {"x": ${player.value.x}, "y": ${player.value.y}}
</c:forEach>
}