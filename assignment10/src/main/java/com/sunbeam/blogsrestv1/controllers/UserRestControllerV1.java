package com.sunbeam.blogsrestv1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.blogsrestv1.daos.UserDao;
import com.sunbeam.blogsrestv1.entities.User;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;





@RequestMapping("/v1")
@RestController
@Controller
public class UserRestControllerV1 
    {
        @Autowired
        private UserDao userDao;
        
        @GetMapping("/users")
        public List<User> getAllUsers() {
        List<User> list = userDao.getAllUsers();
        return list;
        }

        @GetMapping("/users/{id}")
        public User getUserById(@PathVariable("id") int userId) 
        {
            User u = userDao.findById(userId);
            return u;
        }

        @GetMapping("/users/email")
        public User getUserByEmail(@RequestParam String email) 
        {
            User u = userDao.findByEmail(email);
            return u;
        }
        
        @PostMapping("/users")
        public int postUser(@RequestBody User u) {
            int count = userDao.save(u);
            return count;
        }
        
        @PutMapping("path/{id}")
        public int putUser(@PathVariable("id") int id, @RequestBody User u) 
        {
            u.setId(id);
            int count = userDao.updateUser(u);
            return count;
        }
        @DeleteMapping("/users/{id}")
        public int deleteUser(@PathVariable("id")int userId)
        {
            int count = userDao.deleteUser(userId);
            return count;
        }


        
    }
