package com.sunbeam;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String passwd = req.getParameter("passwd");
        UserPojo user = null;
        try(UserDao dao = new UserDao()) {
            user = dao.login(email, passwd);
        } catch(Exception e) {
            e.printStackTrace();
        }
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Login</title>");
        out.println("</head>");
        out.println("<body>");
        if(user != null) {
            //out.println("<h3>Welcome, "+ user.getName() +"!</h3>");
            // if login is successful, add current user object into session.
            HttpSession session = req.getSession();
            session.setAttribute("curusr", user);

            //add user name in cookie and send to client
            String userName = user.getName().replace(" ", "");
            Cookie c = new Cookie("uname", userName);
            c.setMaxAge(3600);
            resp.addCookie(c);

            resp.sendRedirect("bloglist");
            /// OR
            //RequestDispatcher rd = req.getRequestDispatcher("bloglist");
            //rd.forward(req, resp);
        }
        else {
            out.println("<h3>Invalid email or password.</h3>");
            out.println("<a href='index.jsp'>Login Again</a>");
        }
        out.println("</body>");
        out.println("</html>");
    }
}