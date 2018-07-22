/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = {"/insert_author"})
public class insert_author extends HttpServlet {

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
            out.println("<html>\n" +
                        "    <head>\n" +
                        "        <title>Insert Author</title>\n" +
                        "        <meta charset=\"UTF-8\">\n" +
                        "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                        "        <link href=\"//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css\" rel=\"stylesheet\" id=\"bootstrap-css\">\n" +
                        "       \n" +
                        "        <style>\n" +
                        "            .main {\n" +
                        "                position:absolute;\n" +
                        "                left:50%;top:10%;\n" +
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
                        "            select {\n" +
                        "                width: 10%;\n" +
                        "            }\n" +
                        "        </style>\n" +
                        "    \n" +
                        "</head>\n" +
                        "\n" +
                        "<body>\n" +
                        "    <div style=\"float:left; margin: 20px;\">\n" +
                        "            <a href = \"admin_dashboard\">\n" +
                        "                <button type=\"button\" class=\"btn btn-primary\">Back</button>\n" +
                        "            </a>\n" +
                        "    </div>\n" +
                        "    \n" +
                        "    <div class=\"mainn\">\n" +
                        "        \n" +
                        "        <div class=\"main col-xs-10 col-sm-7 col-md-4 col-lg-4\">\n" +
                        "            <h3>Insert New PC Members Information</h3>\n" +
                        "            <div class=\"tab-content\">\n" +
                        "                \n" +
                        "            <div class=\" tab-pane fade in active\" id=\"login\">\n" +
                        "\n" +
                        "                <form class=\"form-horizontal\" method=\"post\" action=\"insert\">\n" +
                        "                  \n" +
                        "                    <div class=\"form-group\">\n" +
                        "                        <label class=\"control-label col-sm-3\" for=\"aname\">Name:</label>\n" +
                        "                        <div class=\"col-sm-9 col-md-9 col-lg-9\">\n" +
                        "                            <input type=\"text\" class=\"form-control\" id=\"aname\" name=\"aname\" placeholder=\"Enter name\" required>\n" +
                        "                        </div>\n" +
                        "                    </div>\n" +
                        "                    <div class=\"form-group\">\n" +
                        "                        <label class=\"control-label col-sm-3\" for=\"aff\">Affiliations</label>\n" +
                        "                        <div class=\"col-sm-9 col-md-9 col-lg-9\">\n" +
                        "                            <input type=\"text\" class=\"form-control\" id=\"aff\" name=\"affiliation\" placeholder=\"Enter Affiliation\" required>\n" +
                        "                        </div>\n" +
                        "                    </div>\n" +
                        "                    <div class=\"form-group\">\n" +
                        "                        <label class=\"control-label col-sm-3\" for=\"role\">Author Role:</label>\n" +
                        "                        <div class=\"col-sm-9 col-md-9 col-lg-9\">\n" +
                        "                            <select class=\"form-control\" id=\"role\" name=\"role\">\n" +
                        "                                <option value=\"First Author\">First Author</option>\n" +
                        "                                <option value=\"Second Author\">Second Author</option>\n" +
                        "                            </select>\n" +
                        "                        </div>\n" +
                        "                    </div>\n" +
                        "                    <div class=\"form-group\">\n" +
                        "                        <label class=\"control-label col-sm-3\" for=\"email\">Email:</label>\n" +
                        "                        <div class=\"col-sm-9 col-md-9 col-lg-9\">\n" +
                        "                            <input type=\"email\" class=\"form-control\" id=\"memail\" name=\"email\" placeholder=\"Enter email\" required>\n" +
                        "                        </div>\n" +
                        "                    </div>\n" +
                        "                    <div class=\"form-group\" style=\"display:none\">\n" +
                        "                        <label class=\"control-label col-sm-3\" for=\"dId\">Paper ID:</label>\n" +
                        "                        <div class=\"col-sm-9 col-md-9 col-lg-9\">\n" +
                        "                            <input type=\"text\" class=\"form-control\" id=\"pId\" name=\"pId\" value="+id+">\n" +
                        "                        </div>\n" +
                        "                    </div>\n" +
                        "                    \n" +
                        "                    <div class=\"form-group\">\n" +
                        "                        <div class=\"col-sm-offset-3 col-sm-6\">\n" +
                        "                        <button type=\"submit\" name=\"insert\" value=\"insertAuthor\" class=\"btn btn-primary\">Insert</button>\n" +
                        "                        <a href = 'insert_author?id="+id+"'><button type=\"button\" class=\"btn btn-default\">Reset</button></a>\n" +
                        "                    </div>\n" +
                        "                  </div>\n" +
                        "                </form>\n" +
                        "            </div>\n" +
                        "          </div>\n" +
                        "        </div>\n" +
                        "    </div>\n" +
                        "</body>");
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
