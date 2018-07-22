/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/signup"})
public class signup extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            String name = request.getParameter("uname");
            String uemail = request.getParameter("email");  
            String phone = request.getParameter("phone");
            String upass = request.getParameter("password");
            String cpass = request.getParameter("cpassword");
            
            try{  
                Class.forName("com.mysql.jdbc.Driver");  
                 Connection con=DriverManager.getConnection(  
                    "jdbc:mysql://localhost:3306/sample_db","root","");
                 
                if(upass.equals(cpass)){
                    Statement stmt=con.createStatement();
                    Statement stmt1=con.createStatement();
                    ResultSet rs=stmt.executeQuery("select * from pc_members where pc_name='"+name+"' or email='"+uemail+"';");
//                    ResultSet rs1=stmt1.executeQuery("select * from pc_members where email='"+uemail+"';");
                    
                    if(rs.next()){
                        
                        if(name.equals(rs.getString(2))){
                            out.println("<script>alert('Username Already Exists !!!');");
                            out.println("window.location = 'http://localhost:8080/web/signup_view.html'</script>");
                        }else{
                            out.println("<script>alert('Email Already Exists !!!');");
                            out.println("window.location = 'http://localhost:8080/web/signup_view.html'</script>");
                        }
                    }else{
                        stmt.executeUpdate("insert into pc_members values (0,'"+name+"','"+uemail+"','"+phone+"','"+upass+"','user');");
                        out.println("<script>alert('Successfully Registered !!!');");
                        out.println("window.location = 'http://localhost:8080/web/login_view.html'</script>");
                    }
                    
                }
                    con.close();
            }catch(Exception e){ 
                out.println(e);
            }
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
