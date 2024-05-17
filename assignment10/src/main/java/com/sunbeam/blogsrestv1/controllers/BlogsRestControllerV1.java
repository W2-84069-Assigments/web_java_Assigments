package com.sunbeam.blogsrestv1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.blogsrestv1.daos.BlogDao;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.sunbeam.blogsrestv1.entities.Blog;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RequestMapping("/v2")
@RestController
@Controller
public class BlogsRestControllerV1 
    {
        
        @Autowired
        private BlogDao blogDao;

        @GetMapping("/blogs")
        public List<Blog> getAllBlogs() {
          return blogDao.findAll();
        }
        
        @GetMapping("/blogs/{id}")
        public List<Blog> getBlogById(@PathVariable("id") int blogId) {
            
            List<Blog> list= blogDao.findBlogById(blogId);
            return list;
        }

        @GetMapping("/blogs/userId")
        public List<Blog> getBlogByUserId(@RequestParam int userId) 
        {
            List<Blog> list=blogDao.findBlogByUserId(userId);
            return   list;
        }

        @PostMapping("/blogs")
        public int postBlogMethod(@RequestBody Blog b) 
        {
            int count = blogDao.save(b)  ;  
            return count;
        }
        
        @PutMapping("/blogs/{id}")
        public int putBlog(@PathVariable("id") int id, @RequestBody Blog b) 
        {
            b.setId(id);
            int count = blogDao.update(b);
            return count;
        }

        @DeleteMapping("/blogs/{id}")
    public int deleteBlogMethod(@PathVariable("id")int id)
    {
        int count = blogDao.deleteById(id);
        return count;
    }        
    }
