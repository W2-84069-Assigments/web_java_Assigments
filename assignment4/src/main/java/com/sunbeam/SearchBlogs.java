package com.sunbeam;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/shownewblogs")
public class SearchBlogs extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);  
      }
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      processRequest(req, resp);
  }
  
  protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
      {
         List<BlogPojo> list = new ArrayList<>();
          String word= req.getParameter("nm");
          try(BlogDao bd = new BlogDao())
          {
               list= bd.showBytitle(word);
              
          }
          catch(Exception e)
          {
              
              e.printStackTrace();
          }
                
          PrintWriter out = resp.getWriter();
          out.println("<html>");
          out.println("<head>");
          out.println("<title>Blogs Searched by word</title>");
          
          out.println("</head>");
          out.println("<body>");
          
          out.println("<br/><br/>");
        out.println("<a href='newblog' style='background-color: #AAAAAA; padding: 14px 20px; text-align: center; display: inline-block;'>Create Blog</a>");
        out.println("<a href='findblog' style='background-color: #AAAAAA; padding: 14px 20px; text-align: center; display: inline-block;'>Find Blogs</a>");
        out.println("<a href='bloglist' style='background-color: #AAAAAA; padding: 14px 20px; text-align: center; display: inline-block;'>All Blogs</a>");
        out.printf("<a href='bloglist?userid=%d' style='background-color: #AAAAAA; padding: 14px 20px; text-align: center; display: inline-block;'>My Blogs</a>", UserPojo.getId()); //TODO show cur user blogs
        out.println("<a href='logout' style='background-color: #AAAAAA; padding: 14px 20px; text-align: center; display: inline-block;'>Sign Out</a>");
        out.println("<br/><br/><br/>");
          out.println("<table border='1'>");
          out.println("<tr>");
          out.println("<th>Id</th>");
          out.println("<th>Title</th>");
          out.println("<th>Category</th>");
          out.println("<th>User</th>");
          out.println("<th>Action</th>");
          out.println("</tr>");
        HttpSession session=req.getSession();
        UserPojo user=(UserPojo)session.getAttribute("curusr");
        out.println("Hello "+user.getName()+"<hr>");
          try(CategoryDao categoryDao = new CategoryDao())
          {
              for(BlogPojo b:list){
                  out.println("<tr>");
                  out.printf("<td>%d</td>",b.getId());
                  out.printf("<td>%s</td>",b.getTitle());
                  CategoryPojo c = categoryDao.showById(b.getCatId());
                  out.printf("<td>%s</td>",c.getTitle());
                  out.printf("<td>%d</td>",b.getUserId());
                  out.printf("<td></td>");
                  out.println("</tr>");
              }
          }
          catch(Exception e)
          {
              e.printStackTrace();
          }
          out.println("</table>");
          out.println("</body>");
          out.println("</html>");
      }
  }