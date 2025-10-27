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
            <div class="d-flex align-items-center">
                <span class="navbar-text me-3">
                    Welcome, <span class="fw-bold text-warning">${user.fullName}</span>
                </span>
                <a class="btn btn-outline-light btn-sm" href="MainController?txtAction=logout">Logout</a>
            </div>
        </div>
    </nav>

    <!-- main content -->
    <div class="container my-4">

        <!-- Add New User button -->
        <div class="d-flex justify-content-between align-items-center mb-3">
            <h2 class="text-primary mb-0">User Management</h2>
            <a href="userForm.jsp" class="btn btn-success">
                + Add New User
            </a>
        </div>

        <!-- User list -->
        <jsp:include page="listOfUsers.jsp"/>
    </div>

    <!-- bootstrap js -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
