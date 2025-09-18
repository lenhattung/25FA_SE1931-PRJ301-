<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <!--
        Vi du ve JSP: 
        <%
            for (int i = 0; i < 10; i++) {
               %>
               Index: <%=i%>  <br/>             
               <%
            }
        %>
        -->
        
        <%
            Object obj_msg = request.getAttribute("msg");
            String msg = obj_msg!=null? obj_msg+"" : "";
            
            Object obj_username = request.getAttribute("username");
            String username = obj_username!=null? obj_username +"" : "";
        %>
          <form action="MainController" method="post">
            <input type="hidden" name="txtAction" value="login"/>
            <table>
                <tr>
                    <td colspan="2">LOGIN FORM</td>
                </tr>
                <tr>
                    <td>Username</td>
                    <td><input type="text" name="txtUsername" value="<%=username%>" required /></td>
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
            <span style="color:red"><%=msg%> </span>
        </form>
    </body>
</html>
