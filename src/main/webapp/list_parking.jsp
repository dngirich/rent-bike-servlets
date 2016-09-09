<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true" %>
<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : 'en_US'}"/>
<fmt:setBundle basename="messages" />
<html>
    <head>
        <meta charset="UTF-8">
        <title> rent-bike </title>
        <link rel="stylesheet" href="css/bootstrap.css">
    
    </head>
    <body style="margin-bottom: 0px;">
        <jsp:include page="navigation.jsp"/>
       <center style="color:graytext;"><h3><fmt:message key="BIKE_PARKINGS"/></h3></center>
        <br/>
        <table border=2>
            <thead>
                <tr>
                    <th>№</th>
                    <th><fmt:message key="STREET"/></th>
                    <th><fmt:message key="SHOW"/></th>
                          <c:set var="admin" value="admin"/>
                        <c:if test="${sessionScope.user.getRoles().contains(admin)}"> 
                        <th colspan=2><fmt:message key="ACTION"/></th>
                        </c:if>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${parkings}" var="parking">
                    <tr>
                        <td><c:out value="${parking.parkingId}"/></td>
                        <td><c:out value="${parking.street}"/></td>
                    <td><a href="BikeController?action=showBike&id=<c:out value="${parking.parkingId}"/>"><fmt:message key="LIST_BIKES"/></a></td>
                        <c:if test="${sessionScope.user.getRoles().contains(admin)}"> 
                            <td><a href="ParkingController?action=edit&id=<c:out value="${parking.parkingId}"/>"><fmt:message key="UPDATE"/></a></td>
                            <td><a href="ParkingController?action=delete&id=<c:out value="${parking.parkingId}"/>"><fmt:message key="DELETE"/></a></td>
                        </c:if>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <br/>

        <c:if test="${sessionScope.user.getRoles().contains(admin)}"> 
        <center><a href="ParkingController?action=add"><button><fmt:message key="ADD_PARKING"/></button></a></center>
        </c:if>

    <jsp:include page="footer.jsp"/>
</body>
</html>