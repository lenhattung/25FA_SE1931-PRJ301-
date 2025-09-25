/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
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

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // request.getParameter
        String txtAction = request.getParameter("txtAction");
        String txtUsername = request.getParameter("txtUsername");
        String txtPassword = request.getParameter("txtPassword");

        if (txtAction == null) {
            txtAction = "login";
        }

        if (txtAction.equals("login")) {
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
        } else if (txtAction.equals("logout")) {
            HttpSession session = request.getSession();
            session.invalidate(); // Huy tat ca nhung cai dang co trong session
            response.sendRedirect("login.jsp");
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
