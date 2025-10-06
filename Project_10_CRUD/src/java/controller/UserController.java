/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.UserDAO;
import model.UserDTO;

/**
 *
 * @author tungi
 */
@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
public class UserController extends HttpServlet {

    private void processLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String txtUsername = request.getParameter("txtUsername");
        String txtPassword = request.getParameter("txtPassword");

        UserDAO userDAO = new UserDAO();

        boolean checkLogin = userDAO.login(txtUsername, txtPassword);
        // Cach chuyen trang
        String url = "";
        url = checkLogin ? "loginSuccess.jsp" : "login.jsp";
        UserDTO user = null;

        String msg = "";
        if (!checkLogin) {
            // Login fail
            msg = "Username or password incorrect!";
        } else {
            user = userDAO.getUserById(txtUsername);
        }
        // setAttribute
        // request.setAttribute("username", txtUsername);
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        // A -> B
        // B -> C
        // C -> D
        request.setAttribute("msg", msg);

        // 1. Kem theo thong tin / chuyen tiep
        request.getRequestDispatcher(url).forward(request, response);

        // 2. Chuyen trang ma khong kem thong tin / chuyen huong
        // response.sendRedirect(url);
    }

    private void processLogout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate(); // Huy tat ca nhung cai dang co trong session
        response.sendRedirect("login.jsp");
    }

    private void processSearchUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("txtName");
        UserDAO userDAO = new UserDAO();

        ArrayList<UserDTO> listOfUsers = new ArrayList<>();
        if (name == null || name.trim().length() == 0) {
            listOfUsers = userDAO.getAllUser();
        } else {
            listOfUsers = userDAO.getAllUserByName(name);
        }
        request.setAttribute("listOfUsers", listOfUsers);
        request.setAttribute("name", name);

        request.getRequestDispatcher("loginSuccess.jsp").forward(request, response);
    }

    private void processSaveUser(HttpServletRequest request, HttpServletResponse response, boolean update)
            throws ServletException, IOException {
        if (update == true) {
            request.setAttribute("update", true);
        }

        UserDAO userDAO = new UserDAO();
        String userID = request.getParameter("txtUserID");
        String password = request.getParameter("txtPassword");
        String fullName = request.getParameter("txtFullname");
        String role = request.getParameter("txtRole");
        String status = request.getParameter("txtStatus");
        boolean checkStatus = (status != null && status.equals("on")) ? true : false;

        UserDTO user = new UserDTO(userID, password, fullName, role, checkStatus);

        String error_userID = "";
        String error_password = "";
        String error_fullName = "";
        String error_role = "";
        String error_status = "";

        boolean hasError = false;

        if (userID == null || userID.trim().isEmpty()) {
            error_userID = "userID can not be empty!";
            hasError = true;
        } else {
            if (update == false && (userDAO.getUserById(userID.trim()) != null)) {
                error_userID = "userID is dupplicated!";
                hasError = true;
            }
        }

        String regex = "^(?=.*[!@#$%^&*(),.?\":{}|<>])[A-Za-z\\d!@#$%^&*(),.?\":{}|<>]{8,}$";
        if (password == null || !password.matches(regex)) {
            error_password = "Mat khau phai co toi thieu 8 ky tu, co it nhat 1 ky tu dac biet!";
            hasError = true;
        }

        if (fullName == null || fullName.trim().isEmpty()) {
            error_fullName = "fullName can not be empty!";
            hasError = true;
        }

        if (role == null || role.trim().isEmpty()) {
            error_role = "role can not be empty!";
            hasError = true;
        }

        String error = "";
        if (!hasError) {
            if (update != true && !userDAO.insert(user)) {
                error = "Them user khong thanh cong!";
                hasError = true;
            }

            if (update == true && !userDAO.update(user)) {
                error = "Chinh sua user khong thanh cong!";
                hasError = true;
            }
        }

        if (hasError) {
            request.setAttribute("u", user);
            request.setAttribute("error_userID", error_userID);
            request.setAttribute("error_password", error_password);
            request.setAttribute("error_fullName", error_fullName);
            request.setAttribute("error_role", error_role);
            request.setAttribute("error", error);
            request.getRequestDispatcher("userForm.jsp").forward(request, response);
            return;
        }

        processSearchUser(request, response);
    }

    private void processCallUpdateUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();
        String uid = request.getParameter("uid");
        UserDTO user = userDAO.getUserById(uid);
        if (user != null) {
            request.setAttribute("update", true);
            request.setAttribute("u", user);
        }
        System.out.println("CHECK");
        request.getRequestDispatcher("userForm.jsp").forward(request, response);
    }

    private void processDeleteUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uid = request.getParameter("uid");
        UserDAO userDAO = new UserDAO();
        userDAO.softDelete(uid);
        processSearchUser(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // request.getParameter
        String txtAction = request.getParameter("txtAction");

        if (txtAction == null) {
            txtAction = "login";
        }

        if (txtAction.equals("login")) {
            processLogin(request, response);
        } else if (txtAction.equals("logout")) {
            processLogout(request, response);
        } else if (txtAction.equals("searchUser")) {
            processSearchUser(request, response);
        } else if (txtAction.equals("addUser")) {
            processSaveUser(request, response, false);
        } else if (txtAction.equals("callUpdateUser")) {
            processCallUpdateUser(request, response);
        } else if (txtAction.equals("updateUser")) {
            processSaveUser(request, response, true);
        } else if (txtAction.equals("deleteUser")) {
            processDeleteUser(request, response);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
