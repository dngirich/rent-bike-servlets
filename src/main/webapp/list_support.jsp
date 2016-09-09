<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>
<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : 'en_US'}"/>
<fmt:setBundle basename="messages" />
<!doctype html>
<html>
    <head>
        <meta charset="UTF-8">
        <title> rent-bike </title>
        <link rel="stylesheet" href="css/bootstrap.css">
    </head>
    <body style="margin-bottom: 0px;">
        <jsp:include page="navigation.jsp"/>
           <center style="color:graytext;"><h3><fmt:message key="SERVICE_STAFF"/></h3></center>
        <br/>
        <table border=2>
            <thead>
                <tr>
                    <th>№</th>
                     <th><fmt:message key="FIRST_NAME"/></th>
                    <th><fmt:message key="LAST_NAME"/></th>
                    <th><fmt:message key="EMAIL"/></th>
                    <th><fmt:message key="PASSWORD"/></th>
                        <c:set var="admin" value="admin"/>
                        <c:if test="${sessionScope.user.getRoles().contains(admin)}"> 
                         <th colspan=2><fmt:message key="ACTION"/></th>
                        </c:if>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${supports}" var="support">
                    <tr>
                        <td><c:out value="${support.id}"/></td>
                        <td><c:out value="${support.firstName}"/></td>
                        <td><c:out value="${support.lastName}"/></td>
                        <td><c:out value="${support.email}"/></td>
                        <td><c:out value="${support.password}"/></td>
                        <c:if test="${sessionScope.user.getRoles().contains(admin)}"> 
                            <td><a href="UserController?action=edit&id=<c:out value="${support.id}"/>"><fmt:message key="UPDATE"/></a></td>
                            <td><a href="UserController?action=delete&id=<c:out value="${support.id}"/>"><fmt:message key="DELETE"/></a></td>
                        </c:if>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <br/>
        <c:if test="${sessionScope.user.getRoles().contains(admin)}"> 
        <center><a href="UserController?action=add"><button><fmt:message key="ADD_SERVICE_STUFF"/></button></a></center>
        </c:if>
        <jsp:include page="footer.jsp"/>
</body>
</html>