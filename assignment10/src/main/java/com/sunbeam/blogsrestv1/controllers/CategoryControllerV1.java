package com.sunbeam.blogsrestv1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sunbeam.blogsrestv1.daos.CategoryDao;
import com.sunbeam.blogsrestv1.entities.Category;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RequestMapping("/v1")
@Controller
public class CategoryControllerV1
    {
        @Autowired
        private CategoryDao categoryDao;

        @GetMapping("/categories")
        public @ResponseBody List<Category> getAllCategories() 
        {
            List<Category> list = categoryDao.findAll();
            return list;
        }
        
        @GetMapping("/categories/{id}")
        public @ResponseBody Category getCategoryById(@PathVariable("id") int categoryId) {
            
            Category c = categoryDao.findById(categoryId);
            return c;
        }

        @PostMapping("/categories")
        public @ResponseBody int postCategory(@RequestBody Category c) {
            int count = categoryDao.save(c);            
            return count;
        }

        @PutMapping("/categories/{id}")
        public @ResponseBody int putCategory(@PathVariable("id") int categoryId, @RequestBody Category c) 
        {
            c.setId(categoryId);
            int count = categoryDao.update(c);
            return count;
        }

        @DeleteMapping("/categories/{id}")
        public @ResponseBody int deleteCategoryById(@PathVariable("id")int categoryId)

        {
                int count = categoryDao.deleteById(categoryId);
                return count;
        }
        
        
        
    }
