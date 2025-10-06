/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import utils.DBUtils;

/**
 * DAO = Data Access Object
 */
public class UserDAO {

    public UserDAO() {
    }

    public UserDTO getUserById(String userName) {
        try {
            // 1 - Tao ket noi
            Connection conn = DBUtils.getConnection();

            // 2 - Tao cau lenh
            String sql = "SELECT * FROM tblUsers WHERE"
                    + " userID=?";

            // 3 - Tao statement de co the run cau lenh
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, userName);

            // 4 - Thuc thi cau lenh
            ResultSet rs = pst.executeQuery();

            // 5 - Kiem tra
            if (rs.next()) {
                UserDTO user = new UserDTO();
                user.setUserName(rs.getString("userID"));
                user.setPassword(rs.getString("password"));
                user.setFullName(rs.getString("fullName"));
                user.setRole(rs.getString("roleID"));
                user.setActive(rs.getBoolean("status"));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean login(String userName, String password) {
        try {
            UserDTO user = getUserById(userName);
            if (user != null) {
                return user.getPassword().equals(password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<UserDTO> getAllUser() {
        ArrayList<UserDTO> list = new ArrayList<>();
        try {
            // 1 - Tao ket noi
            Connection conn = DBUtils.getConnection();

            // 2 - Tao cau lenh
            String sql = "SELECT * FROM tblUsers";

            // 3 - Tao statement de co the run cau lenh
            PreparedStatement pst = conn.prepareStatement(sql);

            // 4 - Thuc thi cau lenh
            ResultSet rs = pst.executeQuery();

            // 5 - Kiem tra
            while (rs.next()) {
                UserDTO user = new UserDTO();
                user.setUserName(rs.getString("userID"));
                user.setPassword(rs.getString("password"));
                user.setFullName(rs.getString("fullName"));
                user.setRole(rs.getString("roleID"));
                user.setActive(rs.getBoolean("status"));
                list.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<UserDTO> getAllUserByName(String name) {
        ArrayList<UserDTO> list = new ArrayList<>();
        try {
            // 1 - Tao ket noi
            Connection conn = DBUtils.getConnection();

            // 2 - Tao cau lenh
            String sql = "SELECT * FROM tblUsers WHERE fullName LIKE ?";

            // 3 - Tao statement de co the run cau lenh
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, "%" + name + "%");
            // 4 - Thuc thi cau lenh
            ResultSet rs = pst.executeQuery();

            // 5 - Kiem tra
            while (rs.next()) {
                UserDTO user = new UserDTO();
                user.setUserName(rs.getString("userID"));
                user.setPassword(rs.getString("password"));
                user.setFullName(rs.getString("fullName"));
                user.setRole(rs.getString("roleID"));
                user.setActive(rs.getBoolean("status"));
                list.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insert(UserDTO user) {
        try {
            Connection c = DBUtils.getConnection();
            String sql = "INSERT INTO tblUsers(userID, password, fullName, roleID, status) "
                    + "VALUES(?, ?, ?, ?, ?) ";

            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFullName());
            ps.setString(4, user.getRole());
            ps.setBoolean(5, true);

            int i = ps.executeUpdate();
            return i > 0;
        } catch (Exception e) {
        }
        return false;
    }

    public boolean update(UserDTO user) {
        try {
            Connection c = DBUtils.getConnection();
            String sql = "UPDATE tblUsers "
                    + "   SET fullName = ? "
                    + "      , password = ? "
                    + "      , roleID = ? "
                    + "      , status = ?"
                    + " WHERE userID=?";

            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole());
            ps.setBoolean(4, user.isActive());
            ps.setString(5, user.getUserName());

            int i = ps.executeUpdate();
            return i > 0;
        } catch (Exception e) {
        }
        return false;
    }
    
    public boolean hardDelete(String userID) {
        try {
            Connection c = DBUtils.getConnection();
            String sql = "DELETE FROM tblUsers" +
                    "      WHERE userID=?";

            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(5, userID);

            int i = ps.executeUpdate();
            return i > 0;
        } catch (Exception e) {
        }
        return false;
    }
    
        public boolean softDelete(String userID) {
        try {
            Connection c = DBUtils.getConnection();
            String sql = "UPDATE tblUsers SET status=0" +
                    "      WHERE userID=?";

            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, userID);

            int i = ps.executeUpdate();
            return i > 0;
        } catch (Exception e) {
        }
        return false;
    }
}
