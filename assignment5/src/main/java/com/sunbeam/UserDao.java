package com.sunbeam;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

public class UserDao implements AutoCloseable {
    private Connection con;
    public UserDao() throws Exception {
        con = DbUtil.getConnection();
    }
    @Override
    public void close() throws Exception {
        if(con != null)
            con.close();
    }

    public int registerUser(UserPojo u) throws Exception {
        String sql = "INSERT INTO users(email,name,password,phone,created_on) VALUES(?,?,?,?,NOW())";
        try(PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setString(1, u.getEmail());
            stmt.setString(2, u.getName());
            stmt.setString(3, u.getPassword());
            stmt.setString(4, u.getPhone());
            int count = stmt.executeUpdate();
            return count;
        } // stmt.close();
    }

    public UserPojo login(String email, String passwd) throws Exception {
        String sql = "SELECT * FROM users WHERE email=? AND password=?";
        try(PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, passwd);
            try(ResultSet rs = stmt.executeQuery()) {
                while(rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("email");
                    email = rs.getString("name");
                    String password = rs.getString("password");
                    String phone = rs.getString("phone");
                    Timestamp created = rs.getTimestamp("created_on");
                    UserPojo u = new UserPojo(id, email, name, password, phone, created);
                    return u;
                }
            } // rs.close();
        } // stmt.close();
        return null;
    }

    public String NameById(int id) throws Exception {
        String sql = "SELECT * FROM users WHERE id=?";
        try(PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            
            try(ResultSet rs = stmt.executeQuery()) {
                while(rs.next()) {
                    id = rs.getInt("id");
                    String name = rs.getString("name");
                    return name;
                }
            } // rs.close();
        } // stmt.close();
        return null;
    }
    
}
    
