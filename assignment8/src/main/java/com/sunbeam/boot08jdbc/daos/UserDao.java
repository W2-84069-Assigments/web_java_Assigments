package com.sunbeam.boot08jdbc.daos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sunbeam.boot08jdbc.entities.User;

@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private UserRowMapper userRowMapper;
    
    public int save(User u)
    {
        String sql= "Insert into users(name,email,password,phone,created_on) Values(?,?,?,?,Now())";
        int count=jdbcTemplate.update(sql,u.getName(),u.getEmail(),u.getPassword(),u.getPhone());
        return count;
    }

    public User findByEmailAndPassword(String email, String Password)
    {
        String sql= "Select * from users where email=? and password=?";
        List<User> list=jdbcTemplate.query(sql,userRowMapper,email,Password);
        return list.size()>0?list.get(0):null;

    }

    public User findByEmail(String email)
    {
        String sql= "Select * from users where email=?";
        List<User> list=jdbcTemplate.query(sql,userRowMapper,email);
        return list.size()>0?list.get(0):null;

    }
    public User findById(int id)
    {
        String sql= "Select * from users where id=?";
        List<User> list=jdbcTemplate.query(sql,userRowMapper,id);
        return list.size()>0?list.get(0):null;

    }
    
}
