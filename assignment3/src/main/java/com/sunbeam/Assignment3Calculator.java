package com.sunbeam;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calculator")

public class Assignment3Calculator extends HttpServlet{
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String num1 = req.getParameter("num1");
        String num2=req.getParameter("num2");
        String option=req.getParameter("option");
        

        int val1=Integer.parseInt(num1);
        int val2=Integer.parseInt(num2);
        try{
            switch (option) {
                case "addition": int add =val1+val2;
                    PrintWriter out=resp.getWriter();
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Greeting</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<div>Additon of  "+val1 +" and "+val2 +" is </div>"+"<h2>"+add+"</h2>");
                    out.println("</body>");
                    out.println("</html>");
                    break;
    
                    case "substraction": int sub =val1-val2;
                    PrintWriter out1=resp.getWriter();
                    out1.println("<html>");
                    out1.println("<head>");
                    out1.println("<title>Greeting</title>");
                    out1.println("</head>");
                    out1.println("<body>");
                    out1.println("<div>Substraction of  "+val1 +" and "+val2 +" is </div>"+"<h2>"+sub+"</h2>");
                    out1.println("</body>");
                    out1.println("</html>");
                    break;
            
                    case "multiplication": int mul =val1*val2;
                    PrintWriter out2=resp.getWriter();
                    out2.println("<html>");
                    out2.println("<head>");
                    out2.println("<title>Greeting</title>");
                    out2.println("</head>");
                    out2.println("<body>");
                    out2.println("<div>Multiplication of  "+val1 +" and "+val2 +" is </div>"+"<h2>"+mul+"</h2>");
                    out2.println("</body>");
                    out2.println("</html>");
                    break;
    
                    case "division": int div =val1/val2;
                    PrintWriter out3=resp.getWriter();
                    out3.println("<html>");
                    out3.println("<head>");
                    out3.println("<title>Greeting</title>");
                    out3.println("</head>");
                    out3.println("<body>");
                    out3.println("<div>Divison of  "+val1 +" and "+val2 +" is </div>"+"<h2>"+div+"</h2>");
                    out3.println("</body>");
                    out3.println("</html>");
                    break;
                default:
                    break;
            }
        }
        catch(Exception e){
            System.out.println("Incorrect credentials");
        }

    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

}
