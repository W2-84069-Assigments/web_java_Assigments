package com.sunbeam.blogsboot.daos;

//import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sunbeam.blogsboot.entities.Blog;
import com.sunbeam.blogsboot.entities.User;


public interface UserDao extends JpaRepository<User,Integer>
{
    User findByEmail(String email);
}