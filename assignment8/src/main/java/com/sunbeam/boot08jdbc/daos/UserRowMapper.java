package com.sunbeam.boot08jdbc.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;


import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.sunbeam.boot08jdbc.entities.User;

@Component
public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException 
    {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String email = rs.getString("email");
        String pass = rs.getString("password");
        String phone = rs.getString("phone");
        Timestamp created = rs.getTimestamp("created_on");
        User u = new User(id, name, email, pass, phone, created);
        return u;
    }


    }
    


