package com.sunbeam.blogrestv2.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sunbeam.blogrestv2.dtos.BlogResult;

@ControllerAdvice
public class BlogAppControllerExceptionHandling 
    {
        @ExceptionHandler
        public @ResponseBody BlogResult<?> handleException(Throwable e)
        {
            e.printStackTrace();
            return BlogResult.error(e.getMessage());
        }
    }
