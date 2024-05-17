package com.sunbeam.beans;

import java.util.ArrayList;
import java.util.List;

import com.sunbeam.daos.BlogDao;
import com.sunbeam.entities.Blog;

public class BlogListBean {
private int userId;
public List<Blog> blogList;

public BlogListBean() {
    userId=0;
    blogList= new ArrayList<>();
}

public List<Blog> getBlogList() {
    return blogList;
}

public void setBlogList(List<Blog> blogList) {
    this.blogList = blogList;
}
public int getUserId() {
    return userId;
}

public void setUserId(int userId) {
    this.userId = userId;
}


public void fetchBlogs()
{
    try(BlogDao blogDao=new BlogDao())
    {
        if(userId==0)
        this.blogList=blogDao.findAll();
        else
            this.blogList=blogDao.findByUserId(userId);
    }
    catch(Exception e){
        e.printStackTrace();
    }
}


}
