<%-- 
    Document   : Ducks
    Created on : Oct 24, 2016
    Author     : Tan Luong
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/json" pageEncoding="UTF-8"%>

{
"Ducks" : [<c:forEach begin="0" var="duck" items="${ ducks }" varStatus="status">
    { "id" : ${ duck.id }, "top": ${ duck.top }, "left": ${ duck.left }, "direction": ${ duck.direction } }<c:if test="${ status.index < ducks.size() - 1 }">,</c:if>
</c:forEach>]
}