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


@WebServlet(urlPatterns = {"/update_paper"})
public class update_paper extends HttpServlet {

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
           
            String id = request.getParameter("id");
            try{
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sample_db","root","");
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select * from conference_papers where paper_id="+id);
            
            out.println("<!DOCTYPE html>\n" +
            "<!--\n" +
            "To change this license header, choose License Headers in Project Properties.\n" +
            "To change this template file, choose Tools | Templates\n" +
            "and open the template in the editor.\n" +
            "-->\n" +
            "<html>\n" +
            "    <head>\n" +
            "        <title>Update Review</title>\n" +
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
            "        \n" +
            "        <div class=\"main col-xs-10 col-sm-7 col-md-4 col-lg-4\">\n" +
            "            <h3>Update PC Members Information</h3>\n" +
            "            <div class=\"tab-content\">\n" +
            "                \n" +
            "            <div class=\" tab-pane fade in active\" id=\"login\">\n" +
            "\n");
            
            while(rs.next()){
                    
                    int cid = rs.getInt(1);
                    String title = rs.getString(2);
                    String abst = rs.getString(3);
                    String file = rs.getString(4);
            
                out.println(" <form class=\"form-horizontal\" method=\"post\" action=\"update\">\n" +
"                  \n" +
                        "<div class=\"form-group\" style=\"display:none;\">\n" +
"                        <label class=\"control-label col-sm-3\" for=\"title\">ID:</label>\n" +
"                        <div class=\"col-sm-9 col-md-9 col-lg-9\">\n" +
"                            <textarea class=\"form-control\" id=\"title\" name=\"id\" value="+cid+">"+cid+"</textarea>\n" +
"                        </div>\n" +
"                    </div>\n" +
"                    <div class=\"form-group\">\n" +
"                        <label class=\"control-label col-sm-3\" for=\"title\">Title:</label>\n" +
"                        <div class=\"col-sm-9 col-md-9 col-lg-9\">\n" +
"                            <textarea class=\"form-control\" id=\"title\" name=\"title\" value="+title+">"+title+"</textarea>\n" +
"                        </div>\n" +
"                    </div>\n" +
"                    <div class=\"form-group\">\n" +
"                        <label class=\"control-label col-sm-3\" for=\"abstract\">Abstract:</label>\n" +
"                        <div class=\"col-sm-9 col-md-9 col-lg-9\">\n" +
"                            <textarea class=\"form-control\" id=\"abstract\" name=\"abstract\" value="+abst+">"+abst+"</textarea>\n" +
"                        </div>\n" +
"                    </div>\n" +
"                    <div class=\"form-group\">\n" +
"                        <label class=\"control-label col-sm-3\" for=\"pdf\">PDF:</label>\n" +
"                        <div class=\"col-sm-9 col-md-9 col-lg-9\">\n" +
"                            <input type=\"file\" class=\"form-control-file\" id=\"pdffile\">\n" +
"                        </div>\n" +
"                      </div>\n" +
"                    <div class=\"form-group\">\n" +
"                        <div class=\"col-sm-offset-3 col-sm-6\">\n" +
"                        <button type=\"submit\" name=\"update\" value=\"updatePaper\" class=\"btn btn-primary\">Update</button>\n" +
"                        <a href = \"insert_paper.html\"><button type=\"button\" class=\"btn btn-default\">Reset</button></a>\n" +
"                    </div>\n" +
"                  </div>\n" +
"                </form>");
            }
                
            out.println("</div>\n" +
            "          </div>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</body>\n" +
            "\n" +
            "");
            
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
