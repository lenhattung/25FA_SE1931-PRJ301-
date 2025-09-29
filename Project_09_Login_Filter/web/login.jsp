<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <!-- bootstrap css -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"/>
    </head>
    <body class="d-flex align-items-center justify-content-center vh-100 bg-light">

        <c:if test="${not empty user}">
            <c:redirect url="loginSuccess.jsp"/>
        </c:if>

        <!-- login card -->
        <div class="card shadow-lg p-4" style="min-width: 350px;">
            <h3 class="text-center mb-4 text-primary">Login Form</h3>
            <form action="MainController" method="post">
                <input type="hidden" name="txtAction" value="login"/>

                <!-- username -->
                <div class="mb-3">
                    <label for="username" class="form-label">Username</label>
                    <input type="text" 
                           class="form-control" 
                           id="username" 
                           name="txtUsername" 
                           value="${username}" 
                           required>
                </div>

                <!-- password -->
                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" 
                           class="form-control" 
                           id="password" 
                           name="txtPassword" 
                           required>
                </div>

                <!-- buttons -->
                <div class="d-flex justify-content-between">
                    <button type="submit" class="btn btn-primary">Login</button>
                    <button type="reset" class="btn btn-secondary">Reset</button>
                </div>

                <!-- message -->
                <c:if test="${not empty msg}">
                    <div class="alert alert-danger mt-3 p-2 text-center">
                        ${msg}
                    </div>
                </c:if>
            </form>
        </div>

        <!-- bootstrap js -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
