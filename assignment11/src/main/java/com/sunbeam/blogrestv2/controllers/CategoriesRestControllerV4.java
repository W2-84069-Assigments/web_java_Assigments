package com.sunbeam.blogrestv2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.blogrestv2.daos.CategoryDao;
import com.sunbeam.blogrestv2.dtos.BlogResult;
import com.sunbeam.blogrestv2.entities.Category;
@RequestMapping("/v4")
@RestController
public class CategoriesRestControllerV4 
    {
        @Autowired
        private CategoryDao categoryDao;

        @GetMapping("/categories/{id}")
        public BlogResult<?> getCategoryById(@PathVariable("id")int categoryId) 
        {
            Category c = categoryDao.findById(categoryId);
            return BlogResult.success(c);
        }


        @GetMapping("/categories")
        public BlogResult<?> getAllCategory() 
        {
           List<Category> list = categoryDao.findAll();
          return BlogResult.success(list);
        }

        @PostMapping("/categories")
        public BlogResult<?> putCategories(@RequestBody Category c) 
        {
           int count =categoryDao.save(c);
           return BlogResult.success(count);
        }
    }
