<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:if test="${not empty user}" >
            <c:redirect url="loginSuccess.jsp"/>
        </c:if>
        <!--
            Vi du ve JSP: 
        <c:forEach var="i" begin="0" end="9">
            Index: ${i} <br/>
        </c:forEach>
        -->       


        <form action="MainController" method="post">
            <input type="hidden" name="txtAction" value="login"/>
            <table>
                <tr>
                    <td colspan="2">LOGIN FORM</td>
                </tr>
                <tr>
                    <td>Username</td>
                    <td><input type="text" name="txtUsername" value="${username}" required /></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" name="txtPassword" required /></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Login" />
                        <input type="reset" value="Reset" />
                    </td>
                </tr>
            </table>
            <span style="color:red">${msg}</span>
        </form>
    </body>
</html>
