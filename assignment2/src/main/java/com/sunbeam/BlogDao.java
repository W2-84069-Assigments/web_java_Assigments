package com.sunbeam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class BlogDao implements AutoCloseable {
    private Connection con;
    public BlogDao() throws Exception {
        con = Dbutil.getConnection();
    }
    @Override
    public void close() throws Exception {
        if(con != null)
            con.close();
    }
    public List<Blog> findAll() throws Exception {
        List<Blog> list = new ArrayList<>();
        String sql = "SELECT * FROM blogs";
        try(PreparedStatement stmt = con.prepareStatement(sql)) {
            try(ResultSet rs = stmt.executeQuery()) {
                while(rs.next()) {
                    int id = rs.getInt("id");
                    int userId = rs.getInt("user_id");
                    int categoryId = rs.getInt("category_id");
                    String title = rs.getString("title");
                    String content = rs.getString("contents");
                    Timestamp created = rs.getTimestamp("created_on");
                    Blog b = new Blog(id, title, content, userId, categoryId, created);
                    list.add(b);
                }
            } // rs.close();
        } // stmt.close();
        return list;
    }


    public int update(Blog b,int Cur_user) throws Exception {
        String sql = "Update blogs set title=?,contents=?,category_id=?,created_on=now() where user_id=? and id=?";
        try(PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, b.getTitle());
            stmt.setString(2, b.getContents());
            stmt.setInt(3, b.getCategoryId());
            stmt.setInt(4, Cur_user);
            stmt.setInt(5,b.getId());
            int count = stmt.executeUpdate();
            return count;
        } // stmt.close();
    }

 


    public int save(Blog b) throws Exception {
        String sql = "INSERT INTO blogs(title,contents,user_id,category_id,created_on) VALUES(?,?,?,?,NOW())";
        try(PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, b.getTitle());
            stmt.setString(2, b.getContents());
            stmt.setInt(3, b.getUserId());
            stmt.setInt(4, b.getCategoryId());
            int count = stmt.executeUpdate();
            return count;
        } // stmt.close();
    }

    public List<Blog>findBlogByUserId(int user_id) throws Exception
    {
        List <Blog> list = new ArrayList<>();
        String sql ="Select * from blogs where user_id=?";
        try(PreparedStatement stmt = con.prepareStatement(sql)) 
        {
            stmt.setInt(1, user_id);
            try(ResultSet rs = stmt.executeQuery()) 
            {
                while(rs.next()) 
                {
                    int id = rs.getInt("id");
                    int userId = rs.getInt("user_id");
                    int categoryId = rs.getInt("category_id");
                    String title = rs.getString("title");
                    String content = rs.getString("contents");
                    Timestamp created = rs.getTimestamp("created_on");
                    Blog b = new Blog(id, title, content, userId, categoryId, created);
                    list.add(b);

                }
            }
        }
    return list;
    }


 public int deleteById(int id,int user_id) throws Exception {
        String sql = "Delete from blogs where id=? and user_id=?";

        try(PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setInt(2,user_id);
            int count = stmt.executeUpdate();
            return count;
        } // stmt.close();
    }



    public List<Blog>findBlogById(int blog_id) throws Exception
    {
        List <Blog> list = new ArrayList<>();
        String sql ="Select * from blogs where id =? ";
        try(PreparedStatement stmt = con.prepareStatement(sql)) 
        {
            stmt.setInt(1, blog_id);
           // stmt.setInt(2, user_id);
            try(ResultSet rs = stmt.executeQuery()) 
            {
                while(rs.next()) 
                {
                    int id = rs.getInt("id");
                    int userId = rs.getInt("user_id");
                    int categoryId = rs.getInt("category_id");
                    String title = rs.getString("title");
                    String content = rs.getString("contents");
                    Timestamp created = rs.getTimestamp("created_on");
                    Blog b = new Blog(id, title, content, userId, categoryId, created);
                    list.add(b);

                }
            }
        }
    return list;
    }



    public List<Blog> findByLikeContent(String word) throws Exception 
    { 
     List <Blog> list = new ArrayList<>();
     String sql ="Select * from blogs where contents like Concat('%',?,'%')";
     try(PreparedStatement stmt = con.prepareStatement(sql)) 
     {
         stmt.setString(1, word);
         try(ResultSet rs = stmt.executeQuery()) 
         {
             while(rs.next()) 
             {
                 int id = rs.getInt("id");
                 int userId = rs.getInt("user_id");
                 int categoryId = rs.getInt("category_id");
                 String title = rs.getString("title");
                 String content = rs.getString("contents");
                 Timestamp created = rs.getTimestamp("created_on");
                 Blog b = new Blog(id, title, content, userId, categoryId, created);
                 list.add(b);
 
             }
         }
     }
 return list;
    }



        public List<Blog> findByCategoryId(int categoryId) throws Exception 
        { 
            List <Blog> list = new ArrayList<>();
            String sql ="Select * from blogs where category_id=?";
            try(PreparedStatement stmt = con.prepareStatement(sql)) 
            {
                //stmt.setInt(1, user_Id);
               stmt.setInt(1, categoryId);
                try(ResultSet rs = stmt.executeQuery()) 
                {
                    while(rs.next()) 
                    {
                        int id = rs.getInt("id");
                        int userId = rs.getInt("user_id");
                         categoryId = rs.getInt("category_id");
                        String title = rs.getString("title");
                        String content = rs.getString("contents");
                        Timestamp created = rs.getTimestamp("created_on");
                        Blog b = new Blog(id, title, content, userId, categoryId, created);
                        list.add(b);
    
                    }
                }
            }
        return list;  
        }

}