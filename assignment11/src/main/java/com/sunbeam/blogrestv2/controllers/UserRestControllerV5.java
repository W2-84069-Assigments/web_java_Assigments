package com.sunbeam.blogrestv2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.blogrestv2.daos.UserDao;
import com.sunbeam.blogrestv2.dtos.BlogResult;
import com.sunbeam.blogrestv2.entities.Blog;
import com.sunbeam.blogrestv2.entities.User;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/v5")
@RestController
public class UserRestControllerV5 {
    @Autowired
    private UserDao userDao;
    
    @GetMapping("/users")
    public ResponseEntity<BlogResult<?>> getUsers() {
        List<User> list=userDao.getAllUsers();
        
        BlogResult<?> res = BlogResult.success(list);
        return ResponseEntity.ok(res);

    }
    
       @GetMapping("/users/email")
        public ResponseEntity<BlogResult<?>> getUserById(@RequestParam String email) 
        {
            User u= userDao.findByEmail(email);
            if(u!=null)
            {
                BlogResult <User> res = BlogResult.success(u);
                return ResponseEntity.ok(res);
            }
            else
                return ResponseEntity.notFound().build();
        }

       @PostMapping("/users")
        public ResponseEntity<BlogResult<?>> postUser(@RequestBody User u) {
            int count = userDao.save(u);
            if(count==1)
            return ResponseEntity.status(201).build();            
            else
            return ResponseEntity.status(500).build();
        }

        @DeleteMapping("/users/{id}")
        public ResponseEntity<BlogResult<?>> deleteUser(@PathVariable("id") int userId)
        {
            int count = userDao.deleteUser(userId);
            if(count==1)
            return ResponseEntity.status(200).build();
            else
            return ResponseEntity.status(500).build();
        }
}
