package com.sunbeam;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/adduser")
public class AddUSerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String nm=req.getParameter("name");
        String email=req.getParameter("email");
        String pass=req.getParameter("pass");
        String num=req.getParameter("phone");
        UserPojo user=new UserPojo(0, nm, email, pass, num, null);
        try(UserDao ud=new UserDao())
        {
            ud.registerUser(user);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Login</title>");
        out.println("</head>");
        out.println("<body>");

        if(user !=null)
        {
         out.println("<h3>Congratulations You are registered with us !</h3>");
         try {
            Thread.sleep(8000);
            resp.sendRedirect("index.jsp");
        } catch (InterruptedException e) {
            
            e.printStackTrace();
        }
        }
         else{
            out.println("<h3>User not added</h3>");
         }
         out.println("</body>");
        out.println("</html>");
    }


}