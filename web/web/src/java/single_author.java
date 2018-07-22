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


@WebServlet(urlPatterns = {"/single_author"})
public class single_author extends HttpServlet {

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
            String searchName = null;
            try{  
                Class.forName("com.mysql.jdbc.Driver");  
                Connection con=DriverManager.getConnection(  
                    "jdbc:mysql://localhost:3306/sample_db","root","");

                HttpSession session=request.getSession(false);  
                String userId=(String)session.getAttribute("uId"); 
                int paperId = 0;
                searchName = request.getParameter("search");
             
                Statement stmt1=con.createStatement();  
                ResultSet rs1=stmt1.executeQuery("SELECT paper_id,SUBSTRING_INDEX("
                            + "SUBSTRING_INDEX(author_name, ' ', 3), ' ', -1)\n" +
                            "FROM author where role != 'Second Author';");    
                
                out.println("<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "<head>\n" +
                        "  <title>Dashboard</title>\n" +
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
                "</style>"
                        + "</head>\n" +
                        "<body>\n" +
                        "\n" +
                        "<div class=\"container\">\n<br> "
                        + "<a href = \"admin_dashboard\" style=\"float:left\" >"
                        + "<button type=\"button\" class=\"btn btn-primary\">Back</button></a><div style=\"float:right;\">\n"
                        + "<a href = \"login_view.html\" ><button type=\"button\" class=\"btn btn-primary\">Log Out</button></a></div><br>\n" +
                        "  <br><br><h2>Conference Papers by Single Author</h2>"
                        + "<div class=\"topnav\" >\n" 
                            + "<div class=\"search-container\">\n" +
                            "    <form action=\"single_author\" method=\"post\" style=\"\">\n" +
                            "      <input type=\"text\" placeholder=\"Search Last Name..\" name=\"search\" >\n" +
                            "      <button type=\"submit\">Search</button>\n" +
                            "    </form>\n" +
                            "  </div>\n" +
                            "</div><br><br>"+
                        "  <p> </p>\n" +
                        "  <table class=\"table\">\n" +
                        "    <thead class=\"thead-dark\">\n" +
                        "      <tr>\n" +
                        "        <th>Title</th>\n" +
                        "        <th>Abstract</th>\n" +
                        "        <th>First Author</th>\n" +
                        "        <th>PDF File</th>\n" +
                        "        <th>Action</th>\n" +
                        "        <th></th>\n" +
                        "        <th></th>\n" +
                        "        <th>Reviews</th>\n" +
                        "      </tr>\n" +
                        "    </thead>\n" +
                        "    <tbody>\n");
                
                if(searchName != null){
                    while(rs1.next()){
                        if(rs1.getString(2).equals(searchName)){
                            paperId = rs1.getInt(1);
                            Statement stmt=con.createStatement();  
                            ResultSet rs=stmt.executeQuery("select * from conference_papers where paper_id="+paperId);

                            while(rs.next()){

                                int cid = rs.getInt(1);
                                String title = rs.getString(2);
                                String cabstract = rs.getString(3);
                                String cfile = rs.getString(4);

                                String fAuthor = rs1.getString(2);

                                out.println("<tr>\n" +
                                    "<td>"+title+"</td>\n" +
                                    "<td>"+cabstract+"</td>\n" +
                                    "<td>"+fAuthor+"</td>\n" +
                                    "<td>"+cfile+"</td>\n" +
                                    "<td><a href='http://localhost:8080/web/assign_members?id="+cid+"'>Assign Members</a></td>\n" +
                                    "<td><a href='http://localhost:8080/web/update_paper?id="+cid+"'>Update</a></td>\n" +
                                    "<td><a href='http://localhost:8080/web/delete?id="+cid+"&delFlag=paperDelete'>Delete</a></td>\n" +
                                    "<td><a href='http://localhost:8080/web/reviews?id="+cid+"'>Select</a></td>\n" +
                                    "</tr>\n");
                            }
                        }
                    }
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
