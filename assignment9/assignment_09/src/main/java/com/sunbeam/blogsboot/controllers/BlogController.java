package com.sunbeam.blogsboot.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.sunbeam.blogsboot.entities.Blog;
import com.sunbeam.blogsboot.entities.Category;
import com.sunbeam.blogsboot.entities.User;
import com.sunbeam.blogsboot.services.BlogService;
import com.sunbeam.blogsboot.services.UserService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;






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
       Map<Integer,String> categoryMap= blogService.findCategoriesMap();
       
       model.addAttribute("categoryMap", categoryMap);
       Map<Integer,String> userMap=userService.findUserNameMap();
       
       model.addAttribute("userMap", userMap);
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

    @GetMapping("/createblog")
    public String createBlog(Model model,HttpSession session) 
    {
        User user = (User) session.getAttribute("curusr");
        Blog b = new Blog(0, "", "", user.getId(), 1, null) ;
        model.addAttribute("command", b);
        List<Category> categoryList= blogService.findAllCategories() ;
        model.addAttribute("categoryList", categoryList);
        return "newblog";  
    }
    
    @PostMapping("/createblog")
    public String createBlog(Blog b) {
        int count = blogService.saveBlog(b);
        return "redirect:blogs";
    }
    
    @GetMapping("/editblog")
    public String editBlog(@RequestParam("id")int blogId,Model model)
    {
        Blog b =blogService.findBlogsById(blogId); 
        model.addAttribute("command", b);
        List<Category> categoryList= blogService.findAllCategories() ;
        model.addAttribute("categoryList", categoryList);
        return "editblog";  
    }

    @PostMapping("/editblog")
    public String updateBlog(Blog b) {
        int count = blogService.updateBlog(b);
        return "redirect:blogs";
    }
    
    @GetMapping("/delblog")
    public String postMethodName(Model model,@RequestParam(value="id")int bid) 
    {
       int count= blogService.deleteById(bid);
        return "redirect:blogs";
    }
     
     
}
