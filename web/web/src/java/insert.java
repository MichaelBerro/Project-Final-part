/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(urlPatterns = {"/insert"})
public class insert extends HttpServlet {

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
            String userId=(String)session.getAttribute("uId");
            
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            
            String date = dateFormat.format(cal.getTime());
            
            String submitFlag = request.getParameter("insert");
            
            try{  
                Class.forName("com.mysql.jdbc.Driver");  
                Connection con=DriverManager.getConnection(  
                    "jdbc:mysql://localhost:3306/sample_db","root","");
                Statement stmt=con.createStatement();  
                if(submitFlag.equals("insertPaper")){
                    String title = request.getParameter("title");
                    String abst = request.getParameter("abstract");  
                    String file = title.substring(5)+".pdf";
                    
                    stmt.executeUpdate("insert into conference_papers values (0,'"+title+"','"+abst+"','"+file+"');");
                    Statement stmt1=con.createStatement();
                    ResultSet rs=stmt1.executeQuery("select * from conference_papers");
                    if(rs.last()){
                        out.println("<script>");
                        out.println("window.location = 'http://localhost:8080/web/insert_author?id="+rs.getString(1)+"'</script>");
                    }

                    
                }else if(submitFlag.equals("insertMember")){
                    String name = request.getParameter("name"); 
                    String email = request.getParameter("email");
                    String phone = request.getParameter("phone");
                    String pass = request.getParameter("pass");
                    ResultSet rs=stmt.executeQuery("select * from pc_members where email='"+email+"';");
                    if(rs.next()){
                        out.println("<script>alert('Member Already Exists !!!');");
                        out.println("window.location = 'http://localhost:8080/web/insert_members.html'</script>"); 
                    }else{
                        stmt.executeUpdate("insert into pc_members values (0,'"+name+"','"+email+"','"+phone+"','"+pass+"','user');");
                        out.println("<script>alert('Successfully Inserted !!!');");
                        out.println("window.location = 'http://localhost:8080/web/insert_members.html'</script>"); 
                    }
                    
                    
                }else if(submitFlag.equals("insertAuthor")){
                    String aname = request.getParameter("aname"); 
                    String affl = request.getParameter("affiliation"); 
                    String role = request.getParameter("role"); 
                    String email = request.getParameter("email");
                    String pid = request.getParameter("pId");
                   
                    stmt.executeUpdate("insert into author values (0,'"+aname+"','"+affl+"','"+role+"','"+email+"',"+pid+");");
                    out.println("<script>alert('Successfully Inserted !!!');");
                    out.println("window.location = 'http://localhost:8080/web/insert_author'</script>"); 
                   
                }else if(submitFlag.equals("insertReview")){
                    String desc = request.getParameter("desc"); 
                    String recom = request.getParameter("recom");
                    String pID = request.getParameter("pID");
                    
                    Statement stmt1=con.createStatement();
                    ResultSet rs=stmt1.executeQuery("select * from review_report where pc_id="+userId);
                    
                    if(rs.next()){
                        out.println("<script>alert('You have already reviewed this paper !!!');");
                        out.println("window.location = 'http://localhost:8080/web/dashboard'</script>");
                    }else{
                        stmt.executeUpdate("insert into review_report values (0,'"+desc+"','"+recom+"','"+date+"',"+pID+","+userId+");");
                        out.println("<script>alert('Successfully Inserted !!!');");
                        out.println("window.location = 'http://localhost:8080/web/dashboard'</script>");
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
