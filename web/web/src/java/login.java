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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/login"})
public class login extends HttpServlet {
    
  
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
            
            String uname = request.getParameter("uname");  
            String upass = request.getParameter("password");
            Boolean flag = false;
            
            try{  
                Class.forName("com.mysql.jdbc.Driver");  
                 Connection con=DriverManager.getConnection(  
                    "jdbc:mysql://localhost:3306/sample_db","root","");
                   
                Statement stmt=con.createStatement();  
                ResultSet rs=stmt.executeQuery("select * from pc_members");
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Login</title>");            
                out.println("</head>");
                out.println("<body>");
                
                while(rs.next()){
                    
                    String uId = rs.getString(1);
                    String name = rs.getString(2);
                    String pass = rs.getString(5);
                    String type = rs.getString(6);
                    
                    if(uname.equals(name) && upass.equals(pass)){
                        HttpSession session=request.getSession();  
                        session.setAttribute("uId",uId);  
                         
                        if(type.equals("admin")){
                            session.setAttribute("userType","admin"); 
                            out.println("<script>window.location = 'http://localhost:8080/web/admin_dashboard'</script>");
                        }else{
                            session.setAttribute("userType","user"); 
                            out.println("<script>window.location = 'http://localhost:8080/web/dashboard'</script>");  
                        }
                        flag = true;
                        break;
                    }
                    
                }
                
                if(flag == false){
                    out.println("<script>alert('Invalid email or password !!');</script>");
                    out.println("<script>window.location = 'http://localhost:8080/web/login_view.html'</script>");
                }
                
                out.println("</body>");
                out.println("</html>");
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
