package com.sunbeam.blogrestv2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.blogrestv2.daos.CategoryDao;
import com.sunbeam.blogrestv2.dtos.BlogResult;
import com.sunbeam.blogrestv2.entities.Category;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequestMapping("/v5")
@RestController
public class CategoriesRestControllerV5 
    {
        @Autowired
        private CategoryDao categoryDao;
        
        @GetMapping("/categories/{id}")
        public ResponseEntity<BlogResult<?>> getCategoryById(@PathVariable("id")int categoryId) 
        {
            Category c = categoryDao.findById(categoryId);
            if(c!=null)
            {
                BlogResult<Category> res = BlogResult.success(c);
                return ResponseEntity.ok(res);
            }
            else
                return ResponseEntity.notFound().build();
        }

        @GetMapping("/categories")
        public ResponseEntity<BlogResult<?>> getAllCategories() 
        {
            List<Category> list = categoryDao.findAll();
            BlogResult<?> res = BlogResult.success(list);
                return  ResponseEntity.ok(res);
        }

        @PostMapping("/categories")
        public ResponseEntity<BlogResult<?>> postCategory(@RequestBody Category c) {
            int count = categoryDao.save(c);
            if(count==1)
            return ResponseEntity.status(201).build();            
            else
            return ResponseEntity.status(500).build();
        }

        @ExceptionHandler
        public ResponseEntity<BlogResult<?>> handleException(Throwable e)
        {
            e.printStackTrace();
            BlogResult<Object> res = BlogResult.error(e.getMessage());
            return ResponseEntity.status(500).body(res);
        }
        
    }
