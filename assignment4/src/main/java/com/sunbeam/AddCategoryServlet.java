package com.sunbeam;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addcategory")
public class AddCategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String dicription = req.getParameter("desc");
        
        CategoryPojo c = new CategoryPojo(0, title, dicription);
        try(CategoryDao categoryDao = new CategoryDao()) {
            int count =categoryDao.addCategory(c);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        resp.sendRedirect("bloglist");
    }
}