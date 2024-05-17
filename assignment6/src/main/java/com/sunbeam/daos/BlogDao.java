package com.sunbeam.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.sunbeam.entities.Blog;
import com.sunbeam.utils.DbUtil;

public class BlogDao implements AutoCloseable {
    private Connection con;
    public BlogDao() throws Exception {
        con = DbUtil.getConnection();
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
    public List<Blog> findByUserId(int userId) throws Exception {
        List<Blog> list = new ArrayList<>();
        String sql = "SELECT * FROM blogs WHERE user_id=?";
        try(PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            try(ResultSet rs = stmt.executeQuery()) {
                while(rs.next()) {
                    int id = rs.getInt("id");
                    userId = rs.getInt("user_id");
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
    public int deleteById(int blogId) throws Exception {
        String sql = "DELETE FROM blogs WHERE id=?";
        try(PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, blogId);
            int count = stmt.executeUpdate();
            return count;
        }
    }
    public Blog findById(int blogId) throws Exception {
        String sql = "SELECT * FROM blogs WHERE id=?";
        try(PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, blogId);
            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()) {
                    int id = rs.getInt("id");
                    int userId = rs.getInt("user_id");
                    int categoryId = rs.getInt("category_id");
                    String title = rs.getString("title");
                    String content = rs.getString("contents");
                    Timestamp created = rs.getTimestamp("created_on");
                    Blog b = new Blog(id, title, content, userId, categoryId, created);
                    return b;
                }
            } // rs.close();
        } // stmt.close();
        return null;
    }
    public int update(Blog b) throws Exception {
        String sql = "UPDATE blogs SET title=?, contents=?, user_id=?, category_id=? WHERE id=?";
        try(PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, b.getTitle());
            stmt.setString(2, b.getContents());
            stmt.setInt(3, b.getUserId());
            stmt.setInt(4, b.getCategoryId());
            stmt.setInt(5, b.getId());
            int count = stmt.executeUpdate();
            return count;
        } // stmt.close();
    }

    // ...
}
