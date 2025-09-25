<%-- 
    Document   : loginSuccess.jsp
    Created on : Sep 18, 2025, 10:25:24 AM
    Author     : tungi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:if test="${empty user}" >
            <c:redirect url="login.jsp"/>
        </c:if>
        
        <h1>Welcome <span style="color: red">${user.fullName}</span>!</h1>
        
        <a href="MainController?txtAction=logout">Logout</a>
    </body>
</html>
