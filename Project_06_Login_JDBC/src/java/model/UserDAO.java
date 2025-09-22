/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
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

    public boolean login(String userName, String password) {
        try {
            // 1 - Tao ket noi
            Connection conn = DBUtils.getConnection();
            
            // 2 - Tao cau lenh
            String sql = "SELECT * FROM tblUsers WHERE"
                    + " userID='"+ userName +"' AND "
                    + " password='" + password +"'";
            
            // 3 - Tao statement de co the run cau lenh
            Statement st = conn.createStatement();
            
            // 4 - Thuc thi cau lenh
            ResultSet rs = st.executeQuery(sql);
            
            // 5 - Kiem tra
            if(rs.next()){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
   
}
