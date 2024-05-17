package com.sunbeam.blogsrestv1.daos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sunbeam.blogsrestv1.entities.Blog;

@Repository
public class BlogDao 

    {
        @Autowired
        private BlogRowMapper blogRowMapper;
        @Autowired
        private JdbcTemplate jdbcTemplate;

        public int save(Blog b)
        {
            String sql = "INSERT INTO blogs(title,contents,user_id,category_id,created_on) VALUES(?,?,?,?,NOW())";   
            int count=jdbcTemplate.update(sql, b.getTitle(),b.getContent(),b.getUserId(),b.getCategoryId());
            return count;
        }
        public int deleteById(int id)
        {
            String sql = "Delete from blogs where id=? ";
             int count=jdbcTemplate.update(sql, id);
            return count;
        }

        public int update(Blog b)
    {
        String sql = "Update blogs set title=?,contents=?,category_id=? , user_id=? where id=?";
        int count = jdbcTemplate.update(sql,b.getTitle(),b.getContent(),b.getCategoryId(),b.getUserId(),b.getId());
        return count;
    }

    public List<Blog> findAll()
    {
        String sql = "Select * from blogs";
          List<Blog> list= jdbcTemplate.query(sql,blogRowMapper);
          return list.size()>0?list:null;
    }

    public List<Blog> findBlogByUserId(int user_id)
    {
        String sql ="Select * from blogs where user_id=?";         
         List<Blog> list= jdbcTemplate.query(sql,blogRowMapper,user_id);
          return list.size()>0?list:null;
    }
       

    public List<Blog> findBlogById(int id)
    {
        String sql ="Select * from blogs where id=?";         
         List<Blog> list= jdbcTemplate.query(sql,blogRowMapper,id);
          return list.size()>0?list:null;
    }
    

    public List<Blog> findBlogByCategoryId(int categoryId)
    {
        String sql ="Select * from blogs where category_id=?";         
         List<Blog> list= jdbcTemplate.query(sql,blogRowMapper,categoryId);
          return list.size()>0?list:null;
    }


    public List<Blog> findByLikeContent(String word) 
    { 
     
        String sql ="Select * from blogs where contents like Concat('%',?,'%') or title like Concat('%',?,'%')";
        List <Blog> list = jdbcTemplate.query(sql,blogRowMapper,word,word );
        return list.size()>0?list:null;
 


    }

    }
