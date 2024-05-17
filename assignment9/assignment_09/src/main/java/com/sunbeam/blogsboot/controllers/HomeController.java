package com.sunbeam.blogsboot.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class HomeController 
    {
        @RequestMapping("/index")
        public String home()
        {
            return "index";
        }
        
        @RequestMapping("/logout")
        public String logout(HttpSession session) {
           session.invalidate();
            return "logout";
        }
        

    }
