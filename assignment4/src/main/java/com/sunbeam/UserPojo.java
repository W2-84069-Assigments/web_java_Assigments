package com.sunbeam;

public class UserPojo {
    private static int id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private java.sql.Timestamp createdOn;
    public UserPojo(java.sql.Timestamp createdOn) {
        this.createdOn = createdOn;
    }
    public UserPojo(int id, String name, String email, String password, String phone, java.sql.Timestamp created) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.createdOn = created;
    }
    public static int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public java.sql.Timestamp getCreatedOn() {
        return createdOn;
    }
    public void setCreatedOn(java.sql.Timestamp createdOn) {
        this.createdOn = createdOn;
    }
    @Override
    public String toString() {
        return "UserPojo [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", phone="
                + phone + ", createdOn=" + createdOn + "]";
    }
    
    
}
