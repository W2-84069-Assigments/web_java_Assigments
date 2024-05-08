package com.sunbeam;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
public class CategoryDao implements AutoCloseable{
    private Connection con;
    public CategoryDao()throws Exception
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

    public List<CategoryPojo> showCategory() throws Exception {
        List<CategoryPojo> list = new ArrayList<>();
        String sql = "SELECT * FROM categories";
        try(PreparedStatement stmt = con.prepareStatement(sql)) {
            try(ResultSet rs = stmt.executeQuery()) {
                while(rs.next()) {
                    int id = rs.getInt("id");
                    String title = rs.getString("title");
                    String desc = rs.getString("description");
                    CategoryPojo c = new CategoryPojo(id, title, desc);
                    list.add(c);
                }
            } // rs.close();
        } // stmt.close();
        return list;
    }
    public int addCategory(CategoryPojo c) throws Exception {
        String sql = "INSERT INTO categories(title,description) VALUES(?,?)";
        try(PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, c.getTitle());
            stmt.setString(2, c.getDescription());
            int count = stmt.executeUpdate();
            return count;
        }
    }

    public CategoryPojo showById(int categoryId) throws Exception {
        String sql = "SELECT * FROM categories WHERE id=?";
        try(PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, categoryId);
            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()) {
                    int id = rs.getInt("id");
                    String title = rs.getString("title");
                    String desc = rs.getString("description");
                    CategoryPojo c = new CategoryPojo(id, title, desc);
                    return c;
                }
            } // rs.close();
        } // stmt.close();
        return null;
    }  
    }
    

