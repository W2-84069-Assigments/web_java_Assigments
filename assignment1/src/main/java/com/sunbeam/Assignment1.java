package com.sunbeam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Assignment1 {
    private static final String DB_DRIVER="com.mysql.cj.jdbc.Driver"; 
    private static final String DB_URL="jdbc:mysql://localhost:3306/dmcdb";
    private static final String DB_USER="dmc";
    private static final String DB_PASSWORD="dmc";
    static{
    try {
    Class.forName(DB_DRIVER);
    }   catch (ClassNotFoundException e) {
         e.printStackTrace();
        }
    }
    public static void createUser() throws SQLException
    {
        Scanner sc=new Scanner(System.in);
        try(Connection con=DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)){
            String sql="INSERT INTO users (id, email, name, password ,phone) VALUES (?, ?, ?, ?, ?)";
            try(PreparedStatement stmt=con.prepareStatement(sql))
            {
             System.out.println("Enter the id : ");   
             int id=sc.nextInt();
             sc.nextLine();
             System.out.println("Enter the email : ");
             String email=sc.nextLine();
             System.out.println("Enter the name : ");
             String name=sc.nextLine();
             System.out.println("Enter your password : ");
             String password=sc.nextLine();
             System.out.println("Enter your phone number :");
             long phoneno=sc.nextLong();

             stmt.setInt(1, id);
             stmt.setString(2, email);
             stmt.setString(3, name);
             stmt.setString(4, password);
             stmt.setLong(5, phoneno);

             int count=stmt.executeUpdate();
             System.out.println(count+" row is inserted");
            }
        }
    } 

    public static void createCategory()
    {
        Scanner sc=new Scanner(System.in);
        try(Connection con=DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)){
            String sql="insert into categories(id,title,description) values(?,?,?)";
            try(PreparedStatement stmt=con.prepareStatement(sql))
            {
                System.out.println("Enter the id : ");
                int id=sc.nextInt();
                sc.nextLine();
                System.out.println("Enter the title : ");
                String title =sc.nextLine();
                System.out.println("Enter the Description : ");
                String desc=sc.nextLine();

                stmt.setInt(1, id);
                stmt.setString(2, title);
                stmt.setString(3, desc);

                int count=stmt.executeUpdate();
                System.out.println(count + " rows inserted");
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        catch (Exception e) {
            
        }
    }

    public static void dispCategory() throws SQLException
    {
        try(Connection con=DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)){
            String sql="select * from categories";
            try(PreparedStatement stmt=con.prepareStatement(sql)){
            try(ResultSet rs=stmt.executeQuery())
            {
                while (rs.next()) {
                    int id=rs.getInt("id");
                    String title=rs.getString("title");
                    String desc=rs.getString("description");

                    System.out.println(id+"\t"+title+" \t"+desc);
                }
            }

            }
        }
    }

    // Create New Blog (input user id, category id, title, contents from user)
    public static void createBlog() throws SQLException
    {
        Scanner sc=new Scanner(System.in);
        try(Connection con=DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD))
        {
            String sql="insert into blogs(user_id,category_id,title,contents) values(?,?,?,?)";
            try(PreparedStatement stmt=con.prepareStatement(sql))
            {
                System.out.println("Enter the user id : ");
                int uid=sc.nextInt();
                System.out.println("Enter the cid : ");
                int cid=sc.nextInt();
                sc.nextLine();
                System.out.println("Enter the title : ");
                String title=sc.nextLine();
                System.out.println("Enter the contents : ");
                String contents=sc.nextLine();

                stmt.setInt(1, uid);
                stmt.setInt(2, cid);
                stmt.setString(3, title);
                stmt.setString(4, contents);

                int count=stmt.executeUpdate();
                System.out.println(count +" rows affected ");

            }
        }
    }

     public static void dispBlogs() throws SQLException
     {
        try(Connection con=DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD))
        {
            String sql="select * from blogs";
            try(PreparedStatement stmt=con.prepareStatement(sql)){
                try(ResultSet rs=stmt.executeQuery()){
                    while (rs.next()) {
                        int id=rs.getInt("id");
                        String contents=rs.getString("contents");
                        String title=rs.getString("title");
                        String cid=rs.getString("category_id");
                        String uid=rs.getString("user_id");

                     
                        System.out.println(id+"\t"+contents+"\t"+title +"\t"+cid+"\t"+uid);
                    }
                }
            }
        }
     }


     public static void editBlog() throws SQLException
     {
        Scanner sc=new Scanner(System.in);
        try(Connection con=DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD))
        {
            String sql="update blogs set contents=? ,title = ?,category_id= ? ,user_id= ? where id= ?";
            try(PreparedStatement stmt=con.prepareStatement(sql)){
                System.out.println("Enter the id to be modified :");
                int id=sc.nextInt();    
                sc.nextLine();

                System.out.println("Enter the contents : ");
                String content=sc.nextLine();

                System.out.println("enter the title : ");
                String title=sc.nextLine();

                System.out.println("Enter the category id : ");
                int cid=sc.nextInt();

                System.out.println("Enter the user id : ");
                int uid=sc.nextInt();

                stmt.setString(1, content);
                stmt.setString(2, title);
                stmt.setInt(3, cid);
                stmt.setInt(4, uid);
                stmt.setInt(5, id);

                int count=stmt.executeUpdate();
                System.out.println(count+"Rows affected");
            }
            
        }
     }

     public static void deleteBlog() throws SQLException
     {
        Scanner sc=new Scanner(System.in);
        try(Connection con=DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)){
            String sql="delete from blogs where id=?";
            try(PreparedStatement stmt=con.prepareStatement(sql))
            {
                System.out.println("Enter the id to remove the blog :");
                int id=sc.nextInt();

                stmt.setInt(1, id);
                int count=stmt.executeUpdate();
                System.out.println(count+" rows affected");
                
            }
        }
     }

     public static void blogByUserId() throws SQLException
     {
        Scanner sc=new Scanner(System.in);
        try(Connection con=DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD))
        {
            String sql="Select * from blogs where user_id=?";
            try(PreparedStatement stmt=con.prepareStatement(sql))
            {
                System.out.println("Enter the User id to fetch :");
                int uid=sc.nextInt();

                stmt.setInt(1,uid);

                ResultSet rs=stmt.executeQuery();
                while(rs.next())
                {
                   int id1=rs.getInt("id");
                   String contents=rs.getString("contents");
                   String title=rs.getString("title");
                   int cid=rs.getInt("category_id");
                   int usrid=rs.getInt("user_id");

                   System.out.println(id1 +" \t"+contents+" \t"+title+"\t"+cid+" \t"+usrid);
                }

            }
        }
     }

     public static void blogBycatId() throws SQLException
     {
        Scanner sc=new Scanner(System.in);
        try(Connection con=DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD))
        {
            String sql="select * from blogs where category_id=?";
            try(PreparedStatement stmt=con.prepareStatement(sql)){
                System.out.println("Enter the category id for searching the blog : ");
                int catid=sc.nextInt();
                stmt.setInt(1, catid);

                ResultSet rs=stmt.executeQuery();
                while (rs.next()) {
                   int id1=rs.getInt("id");
                   String contents=rs.getString("contents");
                   String title=rs.getString("title");
                   int cid=rs.getInt("category_id");
                   int uid=rs.getInt("user_id");

                   System.out.println(id1 +" \t"+contents+" \t"+title+"\t"+cid+" \t"+uid);
                    
                }
            }
        }
     }

     public static void searchByCatOrTitle() throws SQLException
     {
        Scanner sc=new Scanner(System.in);
       try(Connection con=DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD))
       {
            String sql="select contents,title from blogs where  contents like ? or title like ?";
            try(PreparedStatement stmt=con.prepareStatement(sql)){
                System.out.println("Enter the string or title to search content : ");
                String str=sc.nextLine();
                stmt.setString(1,"%"+str+"%");
                stmt.setString(2,str);
                ResultSet rs=stmt.executeQuery();
                while(rs.next())
                {
                   String contents=rs.getString("contents");
                   String title=rs.getString("title");

                   System.out.println(contents+" \t"+title);
                }
            }
       }
     }

     public static void main(String[] args) throws SQLException 
    {
        Scanner sc=new Scanner(System.in);
        int choice =0;
        do{
            System.out.println("1.Create New User"+"\n"+
        "2.Create New Category"+"\n"+
        "3.Display All Categories"+"\n"+
        "4.Create New Blog (input user id, category id, title, contents from user)."+"\n"+
        "5.Display All Blogs"+"\n"+
        "6.Edit the Blog (by id)"+"\n"+
        "7.Delete the Blog (by id)"+"\n"+
        "8.Display Blogs of the user (input user id from user)"+"\n"+
        "9.Display Blogs of the category (input category id from user)"+"\n"+
        "10.Search Blogs by content or title (input word to search from user)"+"\n"
        +"Select one option"
        );
        choice =sc.nextInt();
        switch (choice) {
            case 1:createUser();
                
                break;
        
                case 2:createCategory();
                
                break;
                case 3:dispCategory();
                
                break;
                case 4:createBlog();
                
                break;
                case 5:dispBlogs();
                
                break;
                case 6:editBlog();
                
                break;
                case 7:deleteBlog();
                
                break;
                case 8:blogByUserId();
                
                break;
                case 9:blogBycatId();
                
                break;
                case 10:searchByCatOrTitle();
                
                break;
            default:
                break;
        }
        }
        while(choice!=0);
    }
}