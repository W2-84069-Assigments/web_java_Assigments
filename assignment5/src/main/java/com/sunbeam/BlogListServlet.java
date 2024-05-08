package com.sunbeam;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/bloglist")
public class BlogListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<BlogPojo> list = new ArrayList<>();
        try (BlogDao blogDao = new BlogDao()) {
            if(req.getParameter("userid") != null) { // my blogs
                int userId = Integer.parseInt(req.getParameter("userid"));
                list = blogDao.myBlog(userId);
            }
            else { // all blogs
                list = blogDao.showBlogs(); 
            }
        } catch (Exception e) {
            // e.printStackTrace();
        }
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Blogs</title>");
        out.println("</head>");
        out.println("<body>");

        // display appln title (from context-param)
        // ServletContext ctx = this.getServletContext();
        // String appTitle = ctx.getInitParameter("appTitle");
        // out.printf("<h2>%s</h2>\n", appTitle);

        HttpSession session = req.getSession();
        UserPojo user = (UserPojo)session.getAttribute("curusr");
        //out.println("Hello, " + user.getName() + "<hr/>");
        // retrieve username from cookie and display it
        String userName = "";
        Cookie[] arr = req.getCookies();
        if(arr != null) {
            for (Cookie c : arr) {
                if(c.getName().equals("uname")) {
                    userName = c.getValue();
                    break;
                }
            }
        }
        out.println("Hello, " + userName + "<hr/>");
        out.println("<a href='newcategory' style='background-color: #AAAAAA; padding: 14px 20px; text-align: center; display: inline-block;'>Create Category</a>");
        out.println("<a href='showcategory' style='background-color: #AAAAAA; padding: 14px 20px; text-align: center; display: inline-block;'>Show Category</a>");
        out.println("<a href='newblog' style='background-color: #AAAAAA; padding: 14px 20px; text-align: center; display: inline-block;'>Create Blog</a>");
        out.println("<a href='findblog' style='background-color: #AAAAAA; padding: 14px 20px; text-align: center; display: inline-block;'>Find Blogs</a>");
        out.println("<a href='bloglist' style='background-color: #AAAAAA; padding: 14px 20px; text-align: center; display: inline-block;'>All Blogs</a>");
        out.printf("<a href='bloglist?userid=%d' style='background-color: #AAAAAA; padding: 14px 20px; text-align: center; display: inline-block;'>My Blogs</a>", user.getId());
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
        try (UserDao ud = new UserDao()) {
            try (CategoryDao categoryDao = new CategoryDao()) {
                for (BlogPojo b : list) {
                    out.println("<tr>");
                    out.printf("<td>%d</td>", b.getId());
                    out.printf("<td>%s</td>", b.getTitle());
                    CategoryPojo c = categoryDao.showById(b.getCatId());
                    out.printf("<td>%s</td>", c.getTitle());
                    out.printf("<td>%s</td>", ud.NameById(b.getUserId()));
                    out.println("<td>");
                    if(b.getUserId() == user.getId())
                        out.printf("<a href='editblog?id=%d'><img src='edit.png' alt='Edit' width='18' height='18'/></a> <a href='delblog?id=%d'><img src='delete.png' alt='Delete' width='16' height='16'/></a>", b.getId(), b.getId());
                    out.println("</td>");
                    out.println("</tr>");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (ServletException e) {
            throw e;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        out.println("</table>");
        String message = (String) req.getAttribute("message");
        if(message != null)
            out.println("<br/>" + message);
        out.println("</body>");
        out.println("</html>");
    }
}