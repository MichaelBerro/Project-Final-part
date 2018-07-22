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


@WebServlet(urlPatterns = {"/no_paper_reviewed"})
public class no_paper_reviewed extends HttpServlet {

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
            try{  
                Class.forName("com.mysql.jdbc.Driver");  
                 Connection con=DriverManager.getConnection(  
                    "jdbc:mysql://localhost:3306/sample_db","root","");
                 
                Statement stmt=con.createStatement();  
                ResultSet rs=stmt.executeQuery("select *\n" +
                                                "from pc_members pc\n" +
                                                "left join assign_member rr on pc.pc_id = rr.pc_id\n" +
                                                "where rr.pc_id is null;");
             
                out.println("<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "<head>\n" +
                        "  <title>PC Members</title>\n" +
                        "  <meta charset=\"utf-8\">\n" +
                        "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                        "  <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css\">\n" +
                        "  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\n" +
                        "  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js\"></script>\n" +
                        "  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js\"></script>\n" +
                        "<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">\n" +
                        "<style>\n" +
                            "\n" +
                            "\n" +
                            ".topnav .search-container {\n" +
                            "  float: right;\n"
                                    + "background-color:grey;" +
                            "}\n" +
                            "\n" +
                            ".topnav input[type=text] {\n" +
                            "  padding: 6px;\n" +
                            "  margin-top: 8px;\n" +
                            "  margin-bottom: 8px;\n" +
                            "  margin-left: 8px;\n" +
                            "  font-size: 17px;\n" +
                            "  border: none;\n" +
                            "}\n" +
                            "\n" +
                            ".topnav .search-container button {\n" +
                            "  float: right;\n" +
                            "  padding: 6px 10px;\n" +
                            "  margin-top: 8px;\n"
                                    + "" +

                            "  margin-right: 16px;\n" +
                            "  background: #ddd;\n" +
                            "  font-size: 17px;\n" +
                            "  border: none;\n" +
                            "  cursor: pointer;\n" +
                            "}\n" +
                            "\n" +
                            ".topnav .search-container button:hover {\n" +
                            "  background: #ccc;\n" +
                            "}\n" +
                            "</style>"+
                        "</head>\n" +
                        "<body>\n" +
                        "\n" +
                        "<div class=\"container\">\n<br><a href = \"most_reviewed\" ><button type=\"button\" class=\"btn btn-primary\">PC Member with most paper reviewed</button></a>\n"
                        + "<a href = \"pc_members\" ><button type=\"button\" class=\"btn btn-primary\">All PC Members</button></a>"
                        + "<div style=\"float:right;\"><a href = \"admin_dashboard\" ><button type=\"button\" class=\"btn btn-primary\">Conference Paper</button></a>\n"
                        + "<a href = \"login_view.html\" ><button type=\"button\" class=\"btn btn-primary\">Log Out</button></a></div><br>\n" +
                        "  <br><br><h2>No paper reviewed</h2>"+
                        "<div class=\"topnav\">\n" +
                            "  <div class=\"search-container\">\n" +
                            "    <form action=\"/action_page.php\">\n" +
                            "      <input type=\"text\" placeholder=\"Search..\" name=\"search\">\n" +
                            "      <button type=\"submit\"><i class=\"fa fa-search\"></i></button>\n" +
                            "    </form>\n" +
                            "  </div>\n" +
                            "</div><br><br>"+
                        "  <p> </p>\n" +
                        "  <table class=\"table\">\n" +
                        "    <thead class=\"thead-dark\">\n" +
                        "      <tr>\n" +
                        "        <th>Name</th>\n" +
                        "        <th>Email</th>\n" +
//                        "        <th>Designation</th>\n" +
                        "        <th>Action</th>\n" +
                        "        <th></th>\n" +
                        "      </tr>\n" +
                        "    </thead>\n" +
                        "    <tbody>\n");    
                    
                    while(rs.next()){
                    
                            int pid = rs.getInt(1);
                            String name = rs.getString(2);
                            String email = rs.getString(3);
        //                    String cfile = rs.getString(4);

                            out.println("<tr>\n" +
        //                        "<td>"+title+"</td>\n" +
                                "<td>"+name+"</td>\n" +
                                "<td>"+email+"</td>\n" +
        //                        "<td>"+email+"</td>\n" +
        //                        "<td><a href='http://localhost:8080/web/assign_members?id="+pid+"'>Assign Members</a></td>\n" +
                                "<td><a href='http://localhost:8080/web/update_members?id="+pid+"'>Update</a></td>\n" +
                                "<td><a href='http://localhost:8080/web/delete?id="+pid+"&delFlag=memberDelete'>Delete</a></td>\n" +
                                "</tr>\n");
                            
                   }
                
                
                
                
                        out.println("</tbody>\n" +
                        "  </table>\n" +
                        "</div>\n" +
                        "\n" +
                        "</body>\n" +
                        "</html>\n" +
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
