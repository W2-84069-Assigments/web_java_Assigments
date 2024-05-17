package com.sunbeam.blogsboot.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sunbeam.blogsboot.entities.Blog;
import com.sunbeam.blogsboot.entities.Category;
import com.sunbeam.blogsboot.services.BlogService;
import com.sunbeam.blogsboot.services.UserService;






@Controller
public class BlogController 
{
    @Autowired
    private BlogService blogService;

    @Autowired
    private UserService userService;

    @RequestMapping("/blogs")
    public String getBlogList(Model model,@RequestParam(value="userid",required=false)Integer userId)
    {
        List<Blog> list = new ArrayList<>();
        if(userId!=null)
            list=blogService.findUserBlogs(userId);
        
        else
            list = blogService.findAllBlogs();
       //System.out.println(listBlog);

       model.addAttribute("blogList", list);
       //Map<Integer,String> categoryMap= blogService.findCategoriesMap();
       
       //model.addAttribute("categoryMap", categoryMap);
       //Map<Integer,String> userMap=userService.findUserNameMap();
       
       //model.addAttribute("userMap", userMap);
       return "bloglist";
     }
    
     @GetMapping("/createcategory")
      public String createCategory()
     {
        return "category";
     }

     @PostMapping("/createcategory")
     public String createCategory(Category c) 
     {
        int count = blogService.saveCategory(c);
        return "redirect:blogs";    
         
     }

     @RequestMapping("/categories")
     public String showAllCategories(Model model) {
        List<Category> list=blogService.findAllCategories();
        model.addAttribute("catList", list);
        return "categorylist";
     }

     @GetMapping("/find")
     public String findWord()
    {
       return "find";
    }

    @PostMapping("/find")
    public String findWord(Model model,String word) {
        List<Blog> list = blogService.findBlogsByWord(word);
       // System.out.println(word);
        model.addAttribute("blogList", list);
        return "bloglist";
    }
    
     
     
}
