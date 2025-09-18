<%-- 
    Document   : loginSuccess.jsp
    Created on : Sep 18, 2025, 10:25:24 AM
    Author     : tungi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Object obj_username = request.getAttribute("username");
            String username = obj_username!=null? obj_username +"" : "";     
        %>
        <h1>Welcome <span style="color: red"><%=username%></span>!</h1>
    </body>
</html>
