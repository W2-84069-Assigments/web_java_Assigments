package com.sunbeam.blogsboot.daos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sunbeam.blogsboot.entities.User;
@Repository
public class UserDao 
{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private UserRowMapper userRowMapper;

    public User findByEmail(String email){
        String sql= "Select * from users where email=?";
        List<User> list= jdbcTemplate.query(sql,userRowMapper,email);
        return list.size()>0?list.get(0) :null;

    }

    public int save(User user)
    {
     String sql = "INSERT INTO users(name,email,password,phone,created_on) VALUES(?,?,?,?,NOW())";
     int count = jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getPassword(), user.getPhone());
        return count;
    }

    public List<User> findAll(){
        String sql= "Select * from users ";
        List<User> list= jdbcTemplate.query(sql,userRowMapper);
        return list;

    }
}
