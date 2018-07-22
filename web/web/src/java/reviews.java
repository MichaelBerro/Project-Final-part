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
import javax.servlet.http.HttpSession;


@WebServlet(urlPatterns = {"/reviews"})
public class reviews extends HttpServlet {
    
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
            String pid = request.getParameter("id");
            
            HttpSession session=request.getSession(false);  
            String userId=(String)session.getAttribute("uId");
            String userType=(String)session.getAttribute("userType");
            try{
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sample_db","root","");
            Statement stmt=con.createStatement();  
            Statement stmt1=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select * from conference_papers where paper_id="+pid);
            ResultSet rs1 = null;
            if(userType.equals("admin")){
                rs1=stmt1.executeQuery("select * from review_report where paper_id="+pid);
            }else{
                rs1=stmt1.executeQuery("select * from review_report where paper_id="+pid+" and pc_id="+userId);
            }
            
           
            out.println("<!DOCTYPE html>\n" +
            "<!--\n" +
            "To change this license header, choose License Headers in Project Properties.\n" +
            "To change this template file, choose Tools | Templates\n" +
            "and open the template in the editor.\n" +
            "-->\n" +
            "<html>\n" +
            "    <head>\n" +
            "        <title>Paper Reviews</title>\n" +
            "        <meta charset=\"UTF-8\">\n" +
            "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "        <link href=\"//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css\" "
                    + "rel=\"stylesheet\" id=\"bootstrap-css\">\n"
                    + "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>" +
                    "    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js\"></script>" +
                    "    <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js\"></script>" +
            "       \n" +
            "        <style>\n" +
            "            .main {\n" +
            "                position:absolute;\n" +
            "                left:40%;top:0;\n" +
            "                transform:translate(-50%,0%);\n" +
            "                -ms-transform:translate(-50%,0%);\n" +
            "            }\n"
                    + "thead{"
                    + "    background-color: black;\n" +
"    color: white;"
                    + "}"
                    + "table{"
                    + "margin-left:40%;"
                    + "}" +
                    
            "        </style>\n" +
            "    \n" +
            "</head>\n" +
            "\n" +
            "<body>\n" +
            "    <div class=\"mainn\" >\n" +
            "        <div class=\"main col-xs-10 col-sm-7 col-md-4 col-lg-4\"><br>\n" +
            "            <h4>Conference Paper Reviews</h4>\n" +
            "            <div class=\"tab-content\" >\n" +
            "                \n" +
            "            <div class=\" tab-pane fade in active\" id=\"login\">\n" +
            "\n");
            
            while(rs.next()){
                    
                    int cid = rs.getInt(1);
                    String title = rs.getString(2);
                    String cabstract = rs.getString(3);
                    String cfile = rs.getString(4);
            
            out.println("<div class=\"form-group\">\n" +
                    "<label style=\"margin:20px;width:10px\" class=\"control-label col-sm-3\" for=\"title\">Title:</label>\n" +
                    "<div class=\"col-sm-9 col-md-9 col-lg-9\">\n" +
                    "   <textarea style=\"margin:20px;width:600px\" class=\"form-control\" id=\"title\" name=\"title\" value="+title+" disabled>"+title+"</textarea>\n" +
                    "</div>\n\n" +
                    "</div>\n" +
                    "<div class=\"form-group\">\n" +
                    "   <label style=\"margin:20px;width:10px\" class=\"control-label col-sm-3\" for=\"abstract\">Abstract:</label>\n" +
                    "   <div class=\"col-sm-9 col-md-9 col-lg-9\">\n" +
                    "       <textarea style=\"margin:20px;width:600px\" class=\"form-control\" id=\"abstract\" name=\"abstract\" value="+cabstract+" disabled>"+cabstract+"</textarea>\n" +
                    "   </div>\n" +
                    "</div>\n");
                    }
            
            out.println("  <table class=\"table\">\n" +
                        "    <thead class=\"thead-dark\">\n" +
                        "      <tr>\n" +
                        "        <th>Comments</th>\n" +
                        "        <th>Recommendations</th>\n" +
                        "        <th>Review Date</th>\n" +
                        "        <th>Action</th>\n" +
                        "        <th></th>\n" +
                        "      </tr>\n" +
                        "    </thead>\n" +
                        "    <tbody>\n");
                
                while(rs1.next()){
                    
                    String rid = rs1.getString(1);
                    String desc = rs1.getString(2);
                    String rec = rs1.getString(3);
                    String date = rs1.getString(4);
                    
                    out.println("<tr>\n" +
//                        "<td>"+title+"</td>\n" +
                        "<td>"+desc+"</td>\n" +
                        "<td>"+rec+"</td>\n" +
                        "<td>"+date+"</td>\n" +
//                        "<td><a href='http://localhost:8080/web/assign_members?id="+pid+"'>Assign Members</a></td>\n" +
                        "<td><a href='http://localhost:8080/web/update_review?id="+rid+"'>Update</a></td>\n" +
                        "<td><a href='http://localhost:8080/web/delete?id="+rid+"&delFlag=reviewDelete'>Delete</a></td>\n" +
                        "</tr>\n");
                }
                
                
                        out.println("</tbody>\n" +
                        "  </table>\n" +
                    "</body>\n" +
                    "\n");
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
