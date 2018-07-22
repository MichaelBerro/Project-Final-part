/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(urlPatterns = {"/update"})
public class update extends HttpServlet {

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
            HttpSession session=request.getSession(false);  
            String userType=(String)session.getAttribute("userType");
            
            String id = request.getParameter("id");
            
            String submitFlag = request.getParameter("update");
            
            try{  
                Class.forName("com.mysql.jdbc.Driver");  
                Connection con=DriverManager.getConnection(  
                    "jdbc:mysql://localhost:3306/sample_db","root","");
                Statement stmt=con.createStatement();  
                if(submitFlag.equals("updatePaper")){
                    
                    String title = request.getParameter("title");
                    String abst = request.getParameter("abstract");  
        //            String file = title.substring(5)+".pdf";
                    
                    stmt.executeUpdate("update conference_papers set tittle='"+title+"',abstarct='"+abst+"' where paper_id="+id+";");
                    out.println("<script>alert('Successfully Updated !!!');");
                    out.println("window.location = 'http://localhost:8080/web/admin_dashboard'</script>");
                    
                }else if(submitFlag.equals("updateMember")){
                    String name = request.getParameter("name"); 
                    String email = request.getParameter("email"); 
                    String phone = request.getParameter("phone"); 
                    
                    stmt.executeUpdate("update pc_members set pc_name='"+name+"',email='"+email+"',contact='"+phone+"' where pc_id="+id+";");
                    out.println("<script>alert('Successfully Updated !!!');");
                    out.println("window.location = 'http://localhost:8080/web/pc_members'</script>");        
                
                }else if(submitFlag.equals("updateReview")){
                    String des = request.getParameter("desc"); 
                    String rec = request.getParameter("recom"); 
            
                    stmt.executeUpdate("update review_report set descrption='"+des+"',recomendation='"+rec+"' where report_id="+id+";");
                    out.println("<script>alert('Successfully Updated !!!');</script>");
                    
                    if(userType.equals("admin")){
                        out.println("<script>window.location = 'http://localhost:8080/web/admin_dashboard'</script>");
                    }else{
                        out.println("<script>window.location = 'http://localhost:8080/web/dashboard'</script>");
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
