package com.sunbeam;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/editblog")
public class EditBlogServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BlogPojo b = new BlogPojo();
        try(BlogDao blogDao = new BlogDao()) {
            int blogId = Integer.parseInt(req.getParameter("id"));
            System.out.println(blogId);
            b = blogDao.findById(blogId);
            System.out.println("blogdata"+b.toString());
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        HttpSession session = req.getSession();
        UserPojo user = (UserPojo) session.getAttribute("curusr");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Edit Blog</title>");
        out.println("</head>");
        out.println("<body>");
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
        out.println("<form method='post' action='editblog'>");
        out.printf("Title: <input type='text' name='title' value='%s'/><br/>\n",b.getTitle());
        try(CategoryDao categoryDao = new CategoryDao()) {
            out.printf("Category: <select name='categoryId'>\n", b.getCatId());
            List<CategoryPojo> categoryList = categoryDao.showCategory();
            for(CategoryPojo c:categoryList) {
                if(c.getId() == b.getCatId())
                    out.printf("<option value='%d' selected>%s</option>\n", c.getId(), c.getTitle());
                else
                    out.printf("<option value='%d'>%s</option>\n", c.getId(), c.getTitle());
            }
            out.println("</select><br/>");
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        out.printf("Contents: <textarea rows='10' cols='60' name='contents'>%s</textarea><br/>\n",b.getContents());
        //out.printf("Current User Id: <input type='text' name='userId' value='%d' readonly/><br/>\n", user.getId());
        out.printf("<input type='hidden' name='userId' value='%d'/><br/>\n", b.getUserId());
        out.printf("<input type='hidden' name='id' value='%d'/><br/>\n", b.getId());
        out.println("<input type='submit' value='Update Blog'/>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        int userId = Integer.parseInt(req.getParameter("userId"));
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));
        String title = req.getParameter("title");
        String contents = req.getParameter("contents");
        BlogPojo b = new BlogPojo(id, title, contents, userId, categoryId, null);
        try(BlogDao blogDao = new BlogDao()) {
            int count = blogDao.editBlog(b);
            String message = "Blog " + count + " Updated Successfully.";
            req.setAttribute("message", message);
        }
        catch(Exception e) {
            e.printStackTrace();
            String message = "Blog Update Failed.";
            req.setAttribute("message", message);
        }
        //resp.sendRedirect("bloglist"); // all blogs
        RequestDispatcher rd = req.getRequestDispatcher("bloglist");
        rd.forward(req, resp);
    }
}