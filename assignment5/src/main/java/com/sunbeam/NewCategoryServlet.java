package com.sunbeam;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/newcategory")
public class NewCategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
   protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserPojo user = (UserPojo) session.getAttribute("curusr");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>New Category</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<form method='post' action='addcategory'>");
        out.println("Title: <input type='text' name='title'/><br/>");
        // try(CategoryDao categoryDao = new CategoryDao()) {
        //     out.println("Category: <select name='categoryId'>");
        //     List<CategoryPojo> categoryList = categoryDao.showCategory();
        //     for(CategoryPojo c:categoryList)
        //         out.printf("<option value='%d'>%s</option>\n", c.getId(), c.getTitle());
        //     out.println("</select><br/>");
        // } catch(Exception ex) {
        //     ex.printStackTrace();
        // }
        out.println("Description: <textarea rows='10' cols='60' name='desc'></textarea><br/>");
        out.printf("<input type='hidden' name='userId' value ='%d'><br/>\n", user.getId());
        out.println("<input type='submit' value='Submit'/>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }
}