package com.sunbeam.blogsboot.daos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sunbeam.blogsboot.entities.Category;

@Repository
public class CategoryDao 
    {
        @Autowired
        private JdbcTemplate jdbcTemplate;
        @Autowired
        private CategoryRowMapper categoryRowMapper;

        public int save(Category c){
        String sql = "Insert INTO categories(title,description) Values(?,?)";
        int count = jdbcTemplate.update(sql, c.getTitle(),c.getDescription());
        return count;
    }

    public int update(Category c){
        String sql = "Update categories set title=?, description=? where id=?";
        int count = jdbcTemplate.update(sql,c.getTitle(),c.getDescription(),c.getId());
        return count;
    }

    public int deleteById(int id){
        String sql="Delete from categories where id=?";
        int count=jdbcTemplate.update(sql, id);
        return count;
    }

    public Category findById(int id){
        String sql="Select * from categories where id=?";
        List<Category> list = jdbcTemplate.query(sql,categoryRowMapper,id);
        return list.size()>0 ? list.get(0):null;

    }
   public List<Category> findAll()
   {
    String sql="Select * from categories ";
    List<Category> list= jdbcTemplate.query(sql,categoryRowMapper);
    return list.size()>0?list:null;

   } 


    }
