package com.sunbeam.blogsboot.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunbeam.blogsboot.daos.BlogDao;
import com.sunbeam.blogsboot.daos.CategoryDao;
import com.sunbeam.blogsboot.entities.Blog;
import com.sunbeam.blogsboot.entities.Category;

@Service
public class BlogService 
{
    @Autowired
    private BlogDao blogDao;
    @Autowired
    private CategoryDao categoryDao;
    
    public List<Blog> findAllBlogs()
    {
        List<Blog> list = blogDao.findAll();
        return list;
    }

    public List<Category> findAllCategories(){
        List<Category> list= categoryDao.findAll();
        return list;

    }

    public Map<Integer,String> findCategoriesMap(){
        List<Category> categoryList=this.findAllCategories();
        Map<Integer,String> categoryMap = new HashMap<>();
        for(Category c: categoryList)
            categoryMap.put(c.getId(),c.getTitle());
        return categoryMap;
    }

    public List<Blog> findUserBlogs(int userId){
        List<Blog> list = blogDao.findBlogByUserId(userId);
        return list;

    }

    public int saveCategory(Category c){
        int count = categoryDao.save(c);
        return count;
    }

    public List<Blog> findBlogsByWord(String word){
        List<Blog> list = blogDao.findByLikeContent(word);
        return list;

    }

    public int saveBlog(Blog b) 
    {
        int count = blogDao.save(b);
        return count;
    }

    public Blog findBlogsById(int blogId){
        List<Blog> l = blogDao.findBlogById(blogId);
        return l.get(0);

    }

    public int updateBlog(Blog b) {
        int count = blogDao.update(b, b.getUserId());
        return count;
    }

    public int deleteById(int id) {
        int count =blogDao.deleteById(id);
        return count;
    }
    
}
