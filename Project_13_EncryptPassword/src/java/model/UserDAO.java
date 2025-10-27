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
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import utils.DBUtils;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * DAO = Data Access Object
 */
public class UserDAO {

    public static String salt = "prj@301#2025";

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
                user.setAvatarBase64(rs.getString("avatarBase64"));
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
                return verifyPassword(password, user.getPassword(), salt);
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
                user.setAvatarBase64(rs.getString("avatarBase64"));
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
                user.setAvatarBase64(rs.getString("avatarBase64"));
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
            String sql = "INSERT INTO tblUsers(userID, password, fullName, roleID, status, avatarBase64) "
                    + "VALUES(?, ?, ?, ?, ?, ?) ";

            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, user.getUserName());
            ps.setString(2, hashPassword(user.getPassword()));
            ps.setString(3, user.getFullName());
            ps.setString(4, user.getRole());
            ps.setBoolean(5, true);
            ps.setString(6, user.getAvatarBase64());

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
                    + "      , avatarBase64 = ?"
                    + " WHERE userID=?";

            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole());
            ps.setBoolean(4, user.isActive());
            ps.setString(5, user.getAvatarBase64());
            ps.setString(6, user.getUserName());

            int i = ps.executeUpdate();
            return i > 0;
        } catch (Exception e) {
        }
        return false;
    }

    public boolean hardDelete(String userID) {
        try {
            Connection c = DBUtils.getConnection();
            String sql = "DELETE FROM tblUsers"
                    + "      WHERE userID=?";

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
            String sql = "UPDATE tblUsers SET status=0"
                    + "      WHERE userID=?";

            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, userID);

            int i = ps.executeUpdate();
            return i > 0;
        } catch (Exception e) {
        }
        return false;
    }

    // Hàm tạo hash từ mật khẩu và salt chuỗi
    public static String hashPassword(String password) throws Exception {
        int iterations = 65536;
        int keyLength = 256;

        // Chuyển salt String sang byte[]
        byte[] saltBytes = salt.getBytes("UTF-8");

        // Tạo thông số cho PBKDF2
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), saltBytes, iterations, keyLength);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

        // Sinh hash
        byte[] hash = skf.generateSecret(spec).getEncoded();

        // Trả về chuỗi Base64 để lưu vào DB
        return Base64.getEncoder().encodeToString(hash);
    }

    // Hàm kiểm tra mật khẩu
    public static boolean verifyPassword(String inputPassword, String storedHash, String salt) throws Exception {
        String newHash = hashPassword(inputPassword);
        return newHash.equals(storedHash);
    }
}
