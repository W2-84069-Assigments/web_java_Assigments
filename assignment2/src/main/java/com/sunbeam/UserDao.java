package com.sunbeam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

public class UserDao implements AutoCloseable{
    private Connection con;
    public UserDao() throws Exception{
        con = Dbutil.getConnection();
    }
    public void close()throws Exception{
        if(con!=null)
        con.close();
    }
    public User findByEmail(String email) throws Exception
    {
       String sql = "Select * from users where email=?";
       try(PreparedStatement stmt=con.prepareStatement(sql)){
        stmt.setString(1, email);
        try(ResultSet rs = stmt.executeQuery())
        {
            if(rs.next()){
                int id = rs.getInt("id");
                String name= rs.getString("name");
                email= rs.getString("email");
                String password = rs.getString("password");
                String phone = rs.getString("phone");
                Timestamp created = rs.getTimestamp("created_on");
                User u = new User(id, name, email, password, phone, created);
                return u;

            }
        }
       } 
       return null;
    }


    public User findByEmailAndPassword(String email,String password) throws Exception
    {
       String sql = "Select * from users where email=? and password=?";
       try(PreparedStatement stmt=con.prepareStatement(sql)){
        stmt.setString(1, email);
        stmt.setString(2, password);
        try(ResultSet rs = stmt.executeQuery())
        {
            if(rs.next()){
                int id = rs.getInt("id");
                String name= rs.getString("name");
                email= rs.getString("email");
                password = rs.getString("password");
                String phone = rs.getString("phone");
                Timestamp created = rs.getTimestamp("created_on");
                User u = new User(id, name, email, password, phone, created);
                return u;

            }
        }
       } 
       return null;
    }

    public int save(User u) throws Exception{
        String sql ="Insert into users(name,email,password,phone,created_on) values(?,?,?,?,NOW())";

        try(PreparedStatement stmt = con.prepareStatement(sql))
            {
                  stmt.setString(1,u.getName());
                  stmt.setString(2,u.getEmail() );
                  stmt.setString(3, u.getPassword());
                  stmt.setString(4, u.getPhone());
                  int count = stmt.executeUpdate();
                  return count;

            }
        }
}
