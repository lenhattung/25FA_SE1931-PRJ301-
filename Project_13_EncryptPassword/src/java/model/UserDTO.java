/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/*
DTO = Data Transfer Object
 */
public class UserDTO {
    private String userName;
    private String password;
    private String fullName;
    private String role;
    private boolean active;
    private String avatarBase64;

    public UserDTO() {
    }

    public UserDTO(String userName, String password, String fullName, String role, boolean active, String avatarBase64) {
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
        this.active = active;
        this.avatarBase64 = avatarBase64;
    }
    

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    } 

    public String getAvatarBase64() {
        return avatarBase64;
    }

    public void setAvatarBase64(String avatarBase64) {
        this.avatarBase64 = avatarBase64;
    }
    
}
