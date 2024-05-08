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

@WebServlet("/showcategory")
public class ShowCategoryServlet extends HttpServlet 
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processRequest(req, resp);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            processRequest(req, resp);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)throws Exception {
        List<CategoryPojo> list = new ArrayList<>();
        try (CategoryDao categoryDao = new CategoryDao()) {
                list = categoryDao.showCategory(); 
        } catch (Exception e) {
            // e.printStackTrace();
        }
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Category</title>");
        out.println("</head>");
        out.println("<body>");
        HttpSession session = req.getSession();
        UserPojo user = (UserPojo)session.getAttribute("curusr");
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
        out.println("<th>Description</th>");
        out.println("</tr>");
        try (UserDao ud = new UserDao()) {
            try (CategoryDao categoryDao = new CategoryDao()) {
                for (CategoryPojo c: list) {
                    out.println("<tr>");
                    out.printf("<td>%d</td>", c.getId());
                    out.printf("<td>%s</td>", c.getTitle());
                    out.printf("<td>%s</td>,",c.getDescription());

            }
         } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (ServletException e) {
            throw e;
    }
        out.println("</table>");
        String message = (String) req.getAttribute("message");
        if(message != null)
            out.println("<br/>" + message);
        out.println("</body>");
        out.println("</html>");
    }
}