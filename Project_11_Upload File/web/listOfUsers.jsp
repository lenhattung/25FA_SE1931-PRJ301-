<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List of Users</title>
        <!-- bootstrap css -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"/>
    </head>
    <body class="container py-4">
        <h1 class="mb-4 text-primary">List of Users</h1>

        <!-- form search -->
        <form action="MainController" method="post" class="row g-2 mb-4">
            <input type="hidden" name="txtAction" value="searchUser"/>
            <div class="col-auto">
                <input type="text" class="form-control" name="txtName" placeholder="Enter name" value="${name}"/>
            </div>
            <div class="col-auto">
                <button type="submit" class="btn btn-primary">Search</button>
            </div>
        </form>

        <!-- table -->
        <div class="table-responsive">
            <table class="table table-bordered table-striped table-hover align-middle">
                <thead class="table-dark">
                    <tr>
                        <th>Avatar</th>
                        <th>UserID</th>
                        <th>Full Name</th>
                        <th>Role</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="u" items="${listOfUsers}">
                        <tr>
                            <td><img src="${u.avatarBase64}" style="width: 50px"/></td>
                            <td>${u.userName}</td>
                            <td>${u.fullName}</td>
                            <td>${u.role}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${u.active}">
                                        <span class="badge bg-success">Active</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="badge bg-secondary">Inactive</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <a href="MainController?txtAction=callUpdateUser&uid=${u.userName}" class="btn btn-sm btn-warning me-1">Update</a>
                                <a href="MainController?txtAction=deleteUser&uid=${u.userName}" 
                                   class="btn btn-sm btn-danger" 
                                   onclick="return confirmDelete('${u.fullName}')">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <!-- bootstrap js -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

        <!-- confirm delete script -->
        <script>
                                       function confirmDelete(name) {
                                           return confirm("Are you sure you want to delete user: " + name + "?");
                                       }
        </script>
    </body>
</html>
