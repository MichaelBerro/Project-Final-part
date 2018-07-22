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


@WebServlet(urlPatterns = {"/add_review_report"})
public class add_review_report extends HttpServlet {

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
            "        <title>Insert Review</title>\n" +
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
            "            <h3>Add Review Report to this Paper</h3>\n" +
            "            <div class=\"tab-content\">\n" +
            "                \n" +
            "            <div class=\" tab-pane fade in active\" id=\"login\">\n" +
            "\n");
            
            while(rs.next()){
                    
                    String title = rs.getString(2);
                    String cabstract = rs.getString(3);
                    
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
                    
            
                out.println("<form class=\"form-horizontal\" method=\"get\" action=\"insert\">\n" +
            "                  \n" +
            "                    <div class=\"form-group\">\n" +
            "                        <label class=\"control-label col-sm-3\" for=\"desc\">Description:</label>\n" +
            "                        <div class=\"col-sm-9 col-md-9 col-lg-9\">\n" +
            "                            <input type=\"text\" class=\"form-control\" id=\"desc\" name=\"desc\">\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                    <div class=\"form-group\">\n" +
            "                        <label class=\"control-label col-sm-3\" for=\"recom\">Recomandation:</label>\n" +
            "                        <div class=\"col-sm-9 col-md-9 col-lg-9\">\n" +
            "                            <input type=\"text\" class=\"form-control\" id=\"recom\" name=\"recom\">\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                    <div class=\"form-group\" style=\"display:none\">\n" +
            "                        <label class=\"control-label col-sm-3\" for=\"pID\">Paper ID:</label>\n" +
            "                        <div class=\"col-sm-9 col-md-9 col-lg-9\">\n" +
            "                            <input type=\"text\" class=\"form-control\" id=\"pID\" name=\"pID\" value="+id+">\n" +
            "                        </div>\n" +
            "                    </div>\n" +           
            "                    \n" +
            "                    <div class=\"form-group\">\n" +
            "                        <div class=\"col-sm-offset-3 col-sm-6\">\n" +
            "                        <button type=\"submit\" name=\"insert\" value=\"insertReview\" class=\"btn btn-primary\">Insert</button>\n" +
            "                        <a href ='add_review_report?id="+id+"'><button type=\"button\" class=\"btn btn-default\">Reset</button></a>\n" +
            "                    </div>\n" +
            "                  </div>\n" +
            "                </form>\n");
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
