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

@WebServlet(urlPatterns = {"/assign_members"})
public class assign_members extends HttpServlet {

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
            
            int id = Integer.parseInt(request.getParameter("id"));
            
            try{  
                Class.forName("com.mysql.jdbc.Driver");  
                 Connection con=DriverManager.getConnection(  
                    "jdbc:mysql://localhost:3306/sample_db","root","");
                 
                Statement stmt2=con.createStatement(); 
                    ResultSet r2=stmt2.executeQuery("select * from assign_member where paper_id = "+id);
                    
                        int size=0;
                        while (r2.next()) {
                            size++;
                        }
                        if(size>2){
                            out.println("<script>alert('This paper is already under review by 3 PC Members');"
                                    + "window.location = 'http://localhost:8080/web/admin_dashboard'</script>");
                        }
                   
                Statement stmt=con.createStatement();  
                ResultSet rs=stmt.executeQuery("select * from conference_papers where paper_id = "+id);    
                
                 out.println("<!DOCTYPE html>\n" +
                            "<!--\n" +
                            "To change this license header, choose License Headers in Project Properties.\n" +
                            "To change this template file, choose Tools | Templates\n" +
                            "and open the template in the editor.\n" +
                            "-->\n" +
                            "<html>\n" +
                            "    <head>\n" +
                            "        <title>Assign Members</title>\n" +
                            "        <meta charset=\"UTF-8\">\n" +
                            "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                            "        <link href=\"//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css\" rel=\"stylesheet\" id=\"bootstrap-css\">\n" +
                            "       \n" +
                            "        <style>\n" +
                            "            .main {\n" +
                            "                position:absolute;\n" +
                            "                left:50%;top:0;\n" +
                            "                transform:translate(-50%,0%);\n" +
                            "                -ms-transform:translate(-50%,0%);\n" +
                            "            }\n" +
                            "\n" +
                            "            form {\n" +
                            "                margin-top: 50px;\n" +
                            "            }\n" +
                            "\n" +
                            "            p {\n" +
                            "                text-align: center;\n" +
                            "            }\n" +
                            "        </style>\n" +
                            "    \n" +
                            "</head>\n" +
                            "\n" +
                            "<body>\n" +
                            "    <div class=\"mainn\">\n" +
                            "        <div class=\"main col-xs-10 col-sm-7 col-md-4 col-lg-4\">\n" +
                            "            <div class=\"tab-content\">\n" +
                            "                \n" +
                            "            <div class=\" tab-pane fade in active\" id=\"login\">\n" +
                            "\n");
                       
                while(rs.next()){
                    
                    Statement stmt1=con.createStatement();  
                    ResultSet rs1=stmt1.executeQuery("select * from pc_members");
                    
                    String title = rs.getString(2);
                     
                    out.println("<center><h1>Assign PC Members To Conference Parper</h1>"
                            + "<p>You can assign upto 3 Members</p></center><br><form class=\"form-horizontal\" method=\"get\" action=\"assign\">\n" +
                            "                  <div class=\"form-group\" style=\"display:none\">\n" +
                            "                    <label class=\"control-label col-sm-3\" for=\"email\">Title</label>\n" +
                            "                    <div class=\"col-sm-9 col-md-9 col-lg-9\" >\n" +
                            "                      <input type=\"text\" class=\"form-control\" id=\"cid\" name=\"cId\" value="+id+">\n" +
                            "                    </div>\n" +
                            "                  </div>\n"+
                            "                  <div class=\"form-group\">\n" +
                            "                    <label class=\"control-label col-sm-3\" for=\"email\">Title</label>\n" +
                            "                    <div class=\"col-sm-9 col-md-9 col-lg-9\">\n" +
                            "                      <input type=\"text\" class=\"form-control\" id=\"title\" name=\"title\" value="+title+" disabled>\n" +
                            "                    </div>\n" +
                            "                  </div>\n"+
                            "                  <div class=\"form-group\">\n" +
                            "                    <label class=\"control-label col-sm-3\" for=\"pwd\">Members</label>\n" +
                            "                    <select name=\"member\" style=\"margin:10px;margin-left:15px;padding: 5px;width: 310px;\">\n");
                    int pcid = 0;
                    while(rs1.next()){
                        String pcname = rs1.getString(2);
                        pcid = Integer.parseInt(rs1.getString(1));
                        
                        out.println( "<option value="+pcid+">"+pcname+"</option>\n");
                    }
                    
                    out.println("</select>\n" +
                                    "</div>\n");
                    
                    
                    out.println("\n" +
                            "<div class=\"form-group\">\n" +
                            "<div class=\"col-sm-offset-3 col-sm-6\">\n" +
                            "<button type=\"submit\" class=\"btn btn-primary\" id=\"assignBtn\">Assign Member</button>\n" +
                            "</div>\n" +
                            "\n" +
                            "</div>\n" +
                            "</form>\n" +
                            "\n" +
                            "</div>\n" +
                            "</div>\n" +
                            "\n" +
                            "</div>\n" +
                            "</div>\n" +
                            "</body>\n" +
                            "\n");
                    
                    out.println("</div>\n");
                    
                    
                }
                
                    out.println("</div>\n" +
                                "\n" +
                                "</body>\n" +
                                "</html>");
                
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
