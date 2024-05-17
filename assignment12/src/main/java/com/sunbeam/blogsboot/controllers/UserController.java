package com.sunbeam.blogsboot.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.sunbeam.blogsboot.entities.User;
import com.sunbeam.blogsboot.models.Credentials;
import com.sunbeam.blogsboot.services.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@Controller
public class UserController
{
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String userAuthenticate(Credentials cr, HttpSession session)
    {
        User user = userService.authenticate(cr);
        if(user!=null)
        {
            session.setAttribute("curusr", user);            //return "welcome";
            return "redirect:blogs";
        }
        return "failed";
    }

    @GetMapping("/createuser")
    public String createNewUser() {
        return "register" ;
    }
    
    @PostMapping("/createuser")
    public String createNewUser(User u) {
        userService.saveUser(u);
        return "redirect:index";    
        
    }
    
     
    
}
