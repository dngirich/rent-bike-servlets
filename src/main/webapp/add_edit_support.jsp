<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
    <center style="color:graytext;"><h3><fmt:message key="EDITING"/></h3>
        <br/>  
        <div id="centerLayer" >
            <form method="post" action="UserController?action=${empty support ? 'create' : 'update'}">
                <input type="hidden" name="id" value="<c:out value="${support.id}" />" /> 
                <label><fmt:message key="FIRST_NAME"/>:</label> <input
                    type="text" name="firstName"
                    value="<c:out value="${support.firstName}"/>" /> <br /><br /> 
                <label><fmt:message key="LAST_NAME"/>:</label> <input
                    type="text" name="lastName"
                    value="<c:out value="${support.lastName}"/>" /> <br /><br /> 
                <label><fmt:message key="EMAIL"/>:</label> <input type="text" name="email"
                    value="<c:out value="${support.email}"/>" /><br/><br/> 
                <label><fmt:message key="PASSWORD"/>:</label> <input type="text" name="password"
                    value="<c:out value="${support.password}"/>" /> 
                <input type="hidden" name="support" value="support" />
                <br/><br/> 
                <input  type="submit" value="<fmt:message key="SAVE"/>"/>
            </form>   
        </div>      
    </center>
        <jsp:include page="footer.jsp"/>
</body>
</html>