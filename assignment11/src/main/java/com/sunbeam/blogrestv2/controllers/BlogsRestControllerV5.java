package com.sunbeam.blogrestv2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.blogrestv2.daos.BlogDao;
import com.sunbeam.blogrestv2.dtos.BlogResult;
import com.sunbeam.blogrestv2.entities.Blog;
import com.sunbeam.blogrestv2.entities.Category;

@RequestMapping("/v5")
@RestController

public class BlogsRestControllerV5 
    {
        @Autowired
        private BlogDao blogDao;

        @GetMapping("/blogs")
        public ResponseEntity<BlogResult<?>> getAllBlogs() {
          List<Blog> list= blogDao.findAll();
          BlogResult<?> res = BlogResult.success(list);
          return ResponseEntity.ok(res);
        }

         @GetMapping("/blogs/{id}")
        public ResponseEntity<BlogResult<?>> getBlogById(@PathVariable("id")int blogId) 
        {
            List<Blog> list = blogDao.findBlogById(blogId);
            if(list!=null)
            {
                BlogResult <List<Blog>> res = BlogResult.success(list);
                return ResponseEntity.ok(res);
            }
            else
                return ResponseEntity.notFound().build();
        }


        @GetMapping("/blogs/userId")
        public ResponseEntity<BlogResult<?>> getBlogByUserId(@RequestParam int userId) 
        {
            List<Blog> list = blogDao.findBlogByUserId(userId);
            if(list!=null)
            {
                BlogResult <List<Blog>> res = BlogResult.success(list);
                return ResponseEntity.ok(res);
            }
            else
                return ResponseEntity.notFound().build();
        }

            @PostMapping("/blogs")
        public ResponseEntity<BlogResult<?>> postBlog(@RequestBody Blog b) {
            int count = blogDao.save(b);
            
            if(count==1)
            return ResponseEntity.status(201).build();            
            else
            return ResponseEntity.status(500).build();
        }

          @DeleteMapping("/blogs/{id}")
        public ResponseEntity<BlogResult<?>> deleteUser(@PathVariable("id") int blogId)
        {
            int count = blogDao.deleteById(blogId);
            if(count==1)
            return ResponseEntity.status(200).build();
            else
            return ResponseEntity.status(500).build();
        }


    }
