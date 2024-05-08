package com.sunbeam;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class BlogDao implements AutoCloseable{
    private Connection con;
    public BlogDao()throws Exception
    {
        con=DbUtil.getConnection();
    }

    @Override
    public void close() throws Exception {
        if(con !=null)
        {
            con.close();
        }
    }
    public List<BlogPojo> showBlogs() throws Exception {
        List<BlogPojo> list = new ArrayList<>();
        String sql = "SELECT * FROM blogs";
        try(PreparedStatement stmt = con.prepareStatement(sql)) {
            try(ResultSet rs = stmt.executeQuery()) {
                while(rs.next()) {
                    int id=rs.getInt("id");
                    int uid=rs.getInt("user_id");
                    int cid=rs.getInt("category_id");
                    String contents=rs.getString("contents");
                    String title=rs.getString("title");
                    Timestamp createdOn=rs.getTimestamp("created_on");
                    BlogPojo c = new BlogPojo(id, title, contents, uid, cid, createdOn);
                    list.add(c);
                }
            } // rs.close();
        } // stmt.close();
        return list;
    }
    public int addBlog(BlogPojo b) throws Exception {
        String sql = "INSERT INTO blogs(contents,title,category_id,user_id,created_on) VALUES(?,?,?,?,now())";
        try(PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, b.getContents());
            stmt.setString(2, b.getTitle());
            stmt.setInt(3, b.getCatId());
            stmt.setInt(4, b.getUserId());
            int count = stmt.executeUpdate();
            return count;
        }
    }

    public List<BlogPojo> myBlog(int userId) throws Exception {
        List<BlogPojo> list = new ArrayList<>();
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
                    BlogPojo b = new BlogPojo(id, title, content, userId, categoryId, created);
                    list.add(b);
                }
            } // rs.close();
        } // stmt.close();
        return list;
    }
    public BlogPojo findById(int blogId) throws Exception {
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
                    BlogPojo b = new BlogPojo(id, title, content, userId, categoryId, created);
                    return b;
                }
            } // rs.close();
        } // stmt.close();
        return null;
    }
    
    public int deleteById(int blogId) throws Exception {
        String sql = "DELETE FROM blogs WHERE id=?";
        try(PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, blogId);
            int count = stmt.executeUpdate();
            return count;
        }
    }


    public List<BlogPojo> showBytitle(String word) throws Exception {
        List<BlogPojo> list = new ArrayList<>();
        String sql = "Select * from blogs where contents like Concat('%',?,'%') or title like Concat('%',?,'%')";
        try(PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, word);
            stmt.setString(2, word);
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
                 BlogPojo b = new BlogPojo(id, title, content, userId, categoryId, created);
                 list.add(b);
 
             }
         }close();
        } 
        return list;
    }

    public int editBlog(BlogPojo b) throws Exception {
        String sql = "UPDATE blogs SET title=?, contents=?, user_id=?, category_id=? WHERE id=?";
        try(PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, b.getTitle());
            stmt.setString(2, b.getContents());
            stmt.setInt(3, b.getUserId());
            stmt.setInt(4, b.getCatId());
            stmt.setInt(5, b.getId());
            int count = stmt.executeUpdate();
            return count;
        } // stmt.close();
    }
    
}
