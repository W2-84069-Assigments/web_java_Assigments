package com.sunbeam.blogrestv2.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.blogrestv2.daos.CategoryDao;
import com.sunbeam.blogrestv2.entities.Category;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@RequestMapping("/v3")
@RestController
public class CategoriesRestControllerV3 
    {
        @Autowired
        private CategoryDao categoryDao;


        @GetMapping("/categories/{id}")
        public Map<String,Object> getCategoryById(@PathVariable("id")int categoryId) 
        {
           Map<String,Object> result= new HashMap<>();
           try{
           Category c = categoryDao.findById(categoryId);

           result.put("status","success");
           result.put("data",c);
           }
           catch(Exception e)
           {
            e.printStackTrace();
            result.put("status","error");
            result.put("error", e.getMessage());
          }
          return result;
        }
        
        @GetMapping("/categories")
        public Map<String,Object> getAllCategories() 
        {
           Map<String,Object> result= new HashMap<>();
           try{
           List<Category> list = categoryDao.findAll();

           result.put("status","success");
           result.put("data",list);
           }
           catch(Exception e)
           {
            e.printStackTrace();
            result.put("status","error");
            result.put("error", e.getMessage());
          }
          return result;
        }


        @PostMapping("/categories")
        public Map<String,Object> putCategories(@RequestBody Category c) 
        {
           Map<String,Object> result= new HashMap<>();
           try{
           int count =categoryDao.save(c);

           result.put("status","success");
           result.put("data",count);
           }
           catch(Exception e)
           {
            e.printStackTrace();
            result.put("status","error");
            result.put("error", e.getMessage());
          }
          return result;
        }
    }
