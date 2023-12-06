<%-- 
    Document   : contenido
    Created on : 27/09/2023, 1:39:27 p. m.
    Author     : farud
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% request.setAttribute ("acction", request.getParameter("page") );%>
<c:choose>
    <c:when test="${action eq 'product'}">
        <jsp:include page="page/product.jsp"/>
    </c:when>
    
    <c:when test="${action eq 'consultarusuario'}">
        <jsp:include page="page/consultarusuario.jsp"/>
    </c:when>
    
    <c:otherwise>
        <jsp:include page="page/dashboard.jsp"/>
    </c:otherwise>
</c:choose>