/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 * DAO = Data Access Object
 */
public class UserDAO {

    ArrayList<UserDTO> list;

    public UserDAO() {
        this.list = new ArrayList<>();
        list.add(new UserDTO("admin", "admin", "Administrator", "AD", true));
        list.add(new UserDTO("se123", "hcm", "Nguyen Van A", "MB", true));
        list.add(new UserDTO("se456", "hcm", "Hoa Hau TT", "MB", true));
    }

    public boolean login(String userName, String password) {
        for (UserDTO user : list) {
            if (user.getUserName().equals(userName)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
   
}
