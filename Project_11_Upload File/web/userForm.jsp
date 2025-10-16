<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${update ? "Update" : "Add"} User</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body class="bg-light py-5">

<div class="container">
    <div class="card shadow-sm">
        <div class="card-header bg-primary text-white">
            <h4 class="mb-0">${update ? "Update" : "Add"} User Form</h4>
        </div>
        <div class="card-body">
            <form action="MainController" method="post">
                <input type="hidden" name="txtAction" value="${update ? 'updateUser' : 'addUser'}" />

                <!-- Username -->
                <div class="mb-3">
                    <label class="form-label">Username</label>
                    <input type="text" name="txtUserID" class="form-control" 
                           value="${u.userName}" ${update ? "readonly" : "required"} />
                    <c:if test="${not empty error_userID}">
                        <div class="text-danger small">${error_userID}</div>
                    </c:if>
                </div>

                <!-- Password -->
                <div class="mb-3">
                    <label class="form-label">Password</label>
                    <input type="password" name="txtPassword" class="form-control" 
                           value="${u.password}" required />
                    <c:if test="${not empty error_password}">
                        <div class="text-danger small">${error_password}</div>
                    </c:if>
                </div>

                <!-- Full Name -->
                <div class="mb-3">
                    <label class="form-label">Full Name</label>
                    <input type="text" name="txtFullname" class="form-control" 
                           value="${u.fullName}" required />
                    <c:if test="${not empty error_fullName}">
                        <div class="text-danger small">${error_fullName}</div>
                    </c:if>
                </div>

                <!-- Role -->
                <div class="mb-3">
                    <label class="form-label">Role</label>
                    <input type="text" name="txtRole" class="form-control" 
                           value="${u.role}" required />
                    <c:if test="${not empty error_role}">
                        <div class="text-danger small">${error_role}</div>
                    </c:if>
                </div>

                <!-- Active -->
                <div class="form-check mb-3">
                    <input type="checkbox" class="form-check-input" name="txtStatus" 
                           id="activeCheck" ${u.active ? "checked" : ""}/>
                    <label class="form-check-label" for="activeCheck">Active</label>
                </div>

                <!-- Avatar Upload -->
                <div class="mb-3">
                    <label class="form-label">Avatar</label>
                    <input type="file" id="avatarFile" accept="image/*" class="form-control" />
                    <!-- Trường ẩn để lưu base64 gửi lên server -->
                    <input type="hidden" name="txtAvatarBase64" id="avatarBase64" value="${u.avatarBase64}" />

                    <!-- Xem trước ảnh -->
                    <div class="mt-3">
                        <img id="avatarPreview" 
                             src="${not empty u.avatarBase64 ? u.avatarBase64 : ''}" 
                             alt="Avatar Preview" 
                             class="rounded border" 
                             style="max-width: 150px; max-height: 150px; display: ${not empty u.avatarBase64 ? 'block' : 'none'};">
                    </div>
                </div>

                <!-- Submit Button -->
                <button type="submit" class="btn ${update ? 'btn-warning' : 'btn-success'}">
                    ${update ? "Update" : "Add"} User
                </button>
                <a href="MainController" class="btn btn-secondary ms-2">Cancel</a>
            </form>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

<!-- JavaScript: convert ảnh sang base64 -->
<script>
document.getElementById('avatarFile').addEventListener('change', function() {
    const file = this.files[0];
    if (!file) return;

    const reader = new FileReader();
    reader.onload = function(e) {
        const base64String = e.target.result; // chứa cả header data:image/png;base64,...
        document.getElementById('avatarBase64').value = base64String;

        // Hiển thị xem trước
        const imgPreview = document.getElementById('avatarPreview');
        imgPreview.src = base64String;
        imgPreview.style.display = 'block';
    };
    reader.readAsDataURL(file); // đọc file dưới dạng Base64
});
</script>

</body>
</html>
