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


@WebServlet(urlPatterns = {"/rejected"})
public class rejected extends HttpServlet {

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
                 
                Statement stmt2=con.createStatement();  
                ResultSet rs2=stmt2.executeQuery("select * from pc_members");
                
                String johnId= null;
                String mattId= null;
                
                while(rs2.next()){
                    if(rs2.getString(2).equals("John")){
                        johnId = rs2.getString(1);
                    }else if(rs2.getString(2).equals("Matt")){
                        mattId = rs2.getString(1);
                    }
                }
                
                Statement stmt3=con.createStatement();  
//                ResultSet rs=stmt.executeQuery("select * from review_report where recomendation='\"reject\"' "
//                        + "and pc_id="+johnId+" or pc_id="+mattId+"; ");
                ResultSet rs3=stmt3.executeQuery("SELECT DISTINCT a1.paper_id\n" +
                                                ",  ( a1.paper_id    <> a2.paper_id    )\n" +
                                                " AS 'diffs'\n" +
                                                "FROM\n" +
                                                "  review_report a1,\n" +
                                                "  review_report a2\n" +
                                                "WHERE a1.pc_id = "+johnId+"\n" +
                                                "  AND a2.pc_id = "+mattId+" AND a1.recomendation='reject'; ");
                
                HttpSession session=request.getSession(false);  
                String userId=(String)session.getAttribute("uId"); 
             
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
                        + "<a href = \"admin_dashboard\"style=\"float:left;\" ><button type=\"button\" class=\"btn btn-primary\">Back</button></a><div style=\"float:right;\">"
                        + "<a href = \"login_view.html\" ><button type=\"button\" class=\"btn btn-primary\">Log Out</button></a></div><br>\n" +
                        "  <br><br><h2>Rejected Conference Papers By Matt And John</h2>"+
                        "  <p> </p>\n" +
                        "  <table class=\"table\">\n" +
                        "    <thead class=\"thead-dark\">\n" +
                        "      <tr>\n" +
                        "        <th>Title</th>\n" +
                        "        <th>Abstract</th>\n" +
                        "        <th>First Author</th>\n" +
                        "        <th>Second Author</th>\n" +
                        "        <th>PDF File</th>\n" +
                        "        <th>Action</th>\n" +
                        "        <th></th>\n" +
                        "        <th></th>\n" +
                        "        <th>Reviews</th>\n" +
                        "      </tr>\n" +
                        "    </thead>\n" +
                        "    <tbody>\n");
                
                while(rs3.next()){
                    if(rs3.getInt(2) == 0){
                
                        Statement stmt=con.createStatement();  
                        ResultSet rs=stmt.executeQuery("select * from conference_papers where paper_id="+rs3.getInt(1));
                        
                        while(rs.next()){
                    
                            int cid = rs.getInt(1);
                            String title = rs.getString(2);
                            String cabstract = rs.getString(3);
                            String cfile = rs.getString(4);

                            Statement stmt1=con.createStatement();  
                            ResultSet rs1=stmt1.executeQuery("select * from author where paper_id="+cid+";");
                            String fAuthor = "-";
                            String sAuthor = "-";
                            while(rs1.next()){
                                if(rs1.getString(4).equals("First Author")){
                                    fAuthor = rs1.getString(2);
                                }else{
                                    sAuthor = rs1.getString(2);
                                }
                            }
                            out.println("<tr>\n" +
                                "<td>"+title+"</td>\n" +
                                "<td>"+cabstract+"</td>\n" +
                                "<td>"+fAuthor+"</td>\n" +
                                "<td>"+sAuthor+"</td>\n" +
                                "<td>"+cfile+"</td>\n" +
                                "<td><a href='http://localhost:8080/web/assign_members?id="+cid+"'>Assign Members</a></td>\n" +
                                "<td><a href='http://localhost:8080/web/update_paper?id="+cid+"'>Update</a></td>\n" +
                                "<td><a href='http://localhost:8080/web/delete?id="+cid+"&delFlag=paperDelete'>Delete</a></td>\n" +
                                "<td><a href='http://localhost:8080/web/reviews?id="+cid+"'>Select</a></td>\n" +
                                "</tr>\n");
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
