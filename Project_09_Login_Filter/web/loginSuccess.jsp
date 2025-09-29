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
        <title>Dashboard</title>
        <!-- bootstrap css -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"/>
    </head>
    <body class="bg-light">

        <!-- navbar -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">User Dashboard</a>
                <div class="d-flex">
                    <span class="navbar-text me-3">
                        Welcome, <span class="fw-bold text-warning">${user.fullName}</span>
                    </span>
                    <a class="btn btn-outline-light btn-sm" href="MainController?txtAction=logout">Logout</a>
                </div>
            </div>
        </nav>

        <!-- main content -->
        <div class="container mt-4">
            <jsp:include page="listOfUsers.jsp"/>
        </div>

        <!-- bootstrap js -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
