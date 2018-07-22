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


@WebServlet(urlPatterns = {"/initializeDb"})
public class initializeDb extends HttpServlet {

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
            
            try{  
                    Class.forName("com.mysql.jdbc.Driver");  
                    Connection con=DriverManager.getConnection(  
                    "jdbc:mysql://localhost:3306/","root","");   
                    Statement stmt=con.createStatement();  
                    String sql = "CREATE DATABASE IF NOT EXISTS sample_db"; 
                    stmt.executeUpdate(sql);
                    String t1 = "create table conference_papers (\n" +
                                "paper_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT ,\n" +
                                "tittle varchar(500) ,\n" +
                                "abstarct varchar(5000),\n" +
                                "pdf_file varchar(50));";
                    
                    String t2 = "create table pc_members(\n" +
                                "pc_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
                                "pc_name varchar(50),\n" +
                                "email varchar(50), \n" +
                                "contact varchar(50),\n" +
                                "password varchar(50),\n" +
                                "user_type varchar(50)\n" +
                                ");";
                    
//                    String t2 = "Create table pc_members(\n" +
//                                "pc_id int PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
//                               "pc_name  varchar(50),\n" +
//                                "email  varchar (50));";
                    
                    String t3 = "Create table author(\n" +
                                "id int PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
                                "author_name varchar (50),\n" +
                                "affiliations Varchar (50),\n" +
                                "role Varchar (50),\n" +
                                "emails varchar (50) ,\n" +
                                "paper_id int,\n" +
                                "FOREIGN KEY (paper_id) REFERENCES conference_papers(paper_id));";
                    
                    String t4 = "create table review_report(\n" +
                                "report_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,\n" +
                                "descrption varchar(50) ,\n" +
                                "recomendation varchar(50) ,\n" +
                                "date_review date,\n" +
                                "paper_id int ,\n" +
                                "pc_id int,\n" +
                                "FOREIGN KEY (paper_id) REFERENCES conference_papers(paper_id),\n" +
                                "FOREIGN KEY (pc_id) REFERENCES pc_members(pc_id));";
                    
                    String t6 = "create table view(\n" +
                                "user_id INT,\n" +
                                "paper_id INT,\n" +
                                "FOREIGN KEY (paper_id) REFERENCES conference_papers(paper_id),\n" +
                                "FOREIGN KEY (user_id) REFERENCES users(id)\n" +
                                ");";
								
                    String t7 = "create table assign_member(\n" +
                                "pc_id INT,\n" +
                                "paper_id INT,\n" +
                                "FOREIGN KEY (paper_id) REFERENCES conference_papers(paper_id),\n" +
                                "FOREIGN KEY (pc_id) REFERENCES pc_members(pc_id)\n" +
                                ");";

                    Connection conn=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sample_db","root",""); 
                    Statement stmt1=conn.createStatement();
                    stmt1.executeUpdate(t1);
                    stmt1.executeUpdate(t2);
                    stmt1.executeUpdate(t3);
                    stmt1.executeUpdate(t4);
//                    stmt1.executeUpdate(t5);
                    stmt1.executeUpdate(t6);
                    stmt1.executeUpdate(t7);
                    
                    String iq1 = "insert into conference_papers values (0,\"Associations of fats and carbohydrate intake with cardiovascular disease\n" +
                                "and mortality in 18 countries from five continents (PURE): a prospective cohort study\",\"\n" +
                                "The relationship between macronutrients and cardiovascular disease and mortality is controversial. Most available \n" +
                                "data are from European and North American populations where nutrition excess is more likely, so their applicability\n" +
                                " to other populations is unclear.\",\"file1.pdf\"),\n" +
                                "(0,\"Work organization and mental health problems in PhD students\",\"\n" +
                                "Research policy observers are increasingly concerned about the potential impact of current academic working\n" +
                                " conditions on mental health, particularly in PhD students. The aim of the current study is threefold. First,\n" +
                                " we assess the prevalence of mental health problems in a representative sample of PhD students in Flanders,\n" +
                                " Belgium (N = 3659). Second, we compare PhD students to three other samples: (1) highly educated in the general\n" +
                                " population (N = 769); (2) highly educated employees (N = 592); and (3) higher education students (N = 333). Third,\n" +
                                " we assess those organizational factors relating to the role of PhD students that predict mental health status. \n" +
                                " Results based on 12 mental health symptoms (GHQ-12) showed that 32% of PhD students are at risk of having or\n" +
                                " developing a common psychiatric disorder, especially depression. This estimate was significantly higher than\n" +
                                " those obtained in the comparison groups. Organizational policies were significantly associated with the\n" +
                                " prevalence of mental health problems. Especially work-family interface, job demands and job control, the\n" +
                                " supervisor’s leadership style, team decision-making culture, and perception of a career outside academia are \n" +
                                " linked to mental health problems.\",\"file2.pdf\"),\n" +
                                "(0,\"Comparison of Hospital Mortality and Readmission Rates for Medicare Patients Treated by Male vs Female \n" +
                                "Physicians.\",\"Studies have found differences in practice patterns between male and female physicians, with female\n" +
                                " physicians more likely to adhere to clinical guidelines and evidence-based practice. However, whether patient\n" +
                                " outcomes differ between male and female physicians is largely unknown.\",\"file3.pdf\"),\n" +
                                "(0,\"Correction of a pathogenic gene mutation in human embryos\",\"Genome editing has potential for the targeted correction \n" +
                                "of germline mutations. Here we describe the correction of the heterozygous MYBPC3 mutation in human preimplantation\n" +
                                " embryos with precise CRISPR–Cas9-based targeting accuracy and high homology-directed repair efficiency by\n" +
                                " activating an endogenous, germline-specific DNA repair response. Induced double-strand breaks (DSBs) at the\n" +
                                " mutant paternal allele were predominantly repaired using the homologous wild-type maternal gene instead of a \n" +
                                " synthetic DNA template. By modulating the cell cycle stage at which the DSB was induced, we were able to avoid \n" +
                                " mosaicism in cleaving embryos and achieve a high yield of homozygous embryos carrying the wild-type MYBPC3 gene\n" +
                                " without evidence of off-target mutations. The efficiency, accuracy and safety of the approach presented suggest\n" +
                                " that it has potential to be used for the correction of heritable mutations in human embryos by complementing\n" +
                                " preimplantation genetic diagnosis. However, much remains to be considered before clinical applications, includin\n" +
                                " g the reproducibility of the technique with other heterozygous mutations.\",\"file4.pdf\"),\n" +
                                "(0,\"Gender stereotypes about intellectual ability emerge early and influence children’s interests\",\"Common \n" +
                                "stereotypes associate high-level intellectual ability (brilliance, genius, etc.) with men more than women. \n" +
                                "These stereotypes discourage women’s pursuit of many prestigious careers; that is, women are underrepresented in\n" +
                                " fields whose members cherish brilliance (such as physics and philosophy). Here we show that these stereotypes \n" +
                                " are endorsed by, and influence the interests of, children as young as 6. Specifically, 6-year-old girls are less \n" +
                                " likely than boys to believe that members of their gender are “really, really smart.” Also at age 6, girls begin\n" +
                                " to avoid activities said to be for children who are “really, really smart.” These findings suggest that gendered \n" +
                                " notions of brilliance are acquired early and have an immediate effect on children’s interests.\n" +
                                "\",\"file5.pdf\"),\n" +
                                "(0,\"More than 75 percent decline over 27 years in total flying insect biomass in protected areas\",\"Global declines\n" +
                                " in insects have sparked wide interest among scientists, politicians, and the general public. Loss of insect\n" +
                                " diversity and abundance is expected to provoke cascading effects on food webs and to jeopardize ecosystem\n" +
                                " services. Our understanding of the extent and underlying causes of this decline is based on the abundance \n" +
                                " of single species or taxonomic groups only, rather than changes in insect biomass which is more relevant for\n" +
                                " ecological functioning. Here, we used a standardized protocol to measure total insect biomass using Malaise\n" +
                                " traps, deployed over 27 years in 63 nature protection areas in Germany (96 unique location-year combinations) \n" +
                                " to infer on the status and trend of local entomofauna. Our analysis estimates a seasonal decline of 76%, \n" +
                                " and mid-summer decline of 82% in flying insect biomass over the 27 years of study. We show that this decline \n" +
                                " is apparent regardless of habitat type, while changes in weather, land use, and habitat characteristics cannot \n" +
                                " explain this overall decline. This yet unrecognized loss of insect biomass must be taken into account in\n" +
                                " evaluating declines in abundance of species depending on insects as a food source, and ecosystem functioning\n" +
                                " in the European landscape.Figures\",\"file6.pdf\"),\n" +
                                "(0,\"Worldwide trends in body-mass index, underweight, overweight, and obesity from 1975 to 2016: a pooled analysis\n" +
                                " of 2416 population-based measurement studies in 128·9 million children, adolescents, and adults.\",\"Underweight,\n" +
                                " overweight, and obesity in childhood and adolescence are associated with adverse health consequences throughout\n" +
                                " the life-course. Our aim was to estimate worldwide trends in mean body-mass index (BMI) and a comprehensive set \n" +
                                " of BMI categories that cover underweight to obesity in children and adolescents, and to compare trends with those\n" +
                                " of adults.\",\"file7.pdf\"),\n" +
                                "(0,\"A Feathered Dinosaur Tail with Primitive Plumage Trapped in Mid-Cretaceous Amber\",\"The first non-avialan \n" +
                                "theropod fragments preserved in amber are described\n" +
                                "•Vertebral outlines, curvature, and plumage suggest a source within Coelurosauria\n" +
                                "•Branching structure in the feathers supports a barbule-first evolutionary pattern\n" +
                                "•Iron within carbonized soft tissue suggests traces of original material are present\",\"file8.pdf\"),\n" +
                                "(0,\"Efficacy and effectiveness of an rVSV-vectored vaccine in preventing Ebola virus disease: final \n" +
                                "results from the Guinea ring vaccination, open-label, cluster-randomised trial (Ebola Ça Suffit!)\",\"rVSV-ZEBOV \n" +
                                "is a recombinant, replication competent vesicular stomatitis virus-based candidate vaccine expressing a surface\n" +
                                " glycoprotein of Zaire Ebolavirus. We tested the effect of rVSV-ZEBOV in preventing Ebola virus disease in\n" +
                                " contacts and contacts of contacts of recently confirmed cases in Guinea, west Africa.\",\"file9.pdf\"),\n" +
                                "(0,\"An extra-uterine system to physiologically supportthe extreme premature lamb\",\"In the developed world,\n" +
                                " extreme prematurity is the leading cause of neonatal mortality andmorbidity due to a combination of organ\n" +
                                " immaturity and iatrogenic injury. Until now, efforts toextend gestation using extracorporeal systems have \n" +
                                " achieved limited success. Here we reportthe development of a system that incorporates a pumpless oxygenator \n" +
                                " circuit connected tothe fetus of a lamb via an umbilical cord interface that is maintained within a closed \n" +
                                " ‘amnioticfluid’ circuit that closely reproduces the environment of the womb. We show that fetal lambsthat \n" +
                                " are  developmentally  equivalent  to  the  extreme  premature  human  infant  can  bephysiologically supported\n" +
                                " in this extra-uterine device for up to 4 weeks. Lambs on supportmaintain stable haemodynamics, have normal\n" +
                                " blood gas and oxygenation parameters andmaintain patency of the fetal circulation. With appropriate nutritional\n" +
                                " support, lambs onthe system demonstrate normal somatic growth, lung maturation and brain growth \n" +
                                " andmyelination.\",\"file10.pdf\");";
                    
                    String iq2 = "insert into pc_members values(0,\"john\",\"john@gmail.com\",\"+7652834765\",\"123\",\"admin\"),\n" +
                                "(0,\"Darius S. Hayes\",\"DariusSHayes@teleworm.us.com\",\"+7652834765\",\"hay\",\"user\"),\n" +
                                "(0,\"Matt\",\"MichelleRHayes@armyspy.com\",\"+7652834765\",\"matt\",\"user\"),\n" +
                                "(0,\"Betty R. Kranz\",\"BettyRKranz@teleworm.us\",\"+7652834765\",\"kran\",\"user\"),\n" +
                                "(0,\"James M. Reimann\",\"JamesMReimann@teleworm.us\",\"+7652834765\",\"rem\",\"user\"),\n" +
                                "(0,\"Diana M. Nix\",\"DianaMNix@jourrapide.com\",\"+7652834765\",\"dia\",\"user\"),\n" +
                                "(0,\"Michael S. Zook\",\"MichaelSZook@jourrapide.com\",\"+7652834765\",\"zook\",\"user\"),\n" +
                                "(0,\"Patricia K. Joiner\",\"PatriciaKJoiner@jourrapide.com\",\"+7652834765\",\"join\",\"user\"),\n" +
                                "(0,\"Andres T. Coates\",\"AndresTCoates@rhyta.com\",\"+7652834765\",\"coa\",\"user\"),\n" +
                                "(0,\"John\",\"john@gmail.com\",\"+7652834765\",\"jkl\",\"user\"),\n" +
                                "(0,\"Nicholas D. Morris\",\"NicholasDMorris@jourrapide.com\",\"+7652834765\",\"nik\",\"user\");";
                    
                    String iq3 = "insert into author values(0,\"Jason\",\"ASD\",\"First Author\",\"jason@gmail.com\",9),\n" +
                                "(0,\"Zhang\",\"Davis\",\"Second Author\",\"IrisRFitzgerald@rhyta.com\",6),\n" +
                                "(0,\"Ruth W. Lu\",\"Thaler\",\"First Author\",\"RuthWPough@armyspy.com\",5),\n" +
                                "(0,\"Evelyn W. Hazen\",\"Williams\",\"First Author\",\"EvelynWHazen@teleworm.us\",4),\n" +
                                "(0,\"Charles M. Brady\",\"Cole\",\"Second Author\",\"CharlesMBrady@dayrep.com\",3),\n" +
                                "(0,\"Lu\",\"Medina\",\"First Author\",\"JoseCBarnett@jourrapide.com\",6),\n" +
                                "(0,\"James H. Hill\",\"Myles\",\"First Author\",\"JamesHHill@jourrapide.com\",8),\n" +
                                "(0,\"Clarence G. Moultry\",\"Mark\",\"First Author\",\"ClarenceGMoultry@jourrapide.com\",2),\n" +
                                "(0,\"Miss G. Gilliam\",\"Lambright\",\"First Author\",\"MissGGilliam@armyspy.com\",1),\n" +
                                "(0,\"Chandra L. Martinez\",\"Hawkins\",\"Second Author\",\"ChandraLMartinez@teleworm.us\",6);";
                    
                    String iq4 = "insert into review_report values(0,\"Good\",\"accept\",\"2017-12-09\",4,2),\n" +
                                "(0,\"satisfactory\",\"accept\",\"2016-06-04\",6,1),\n" +
                                "(0,\"Need more research\",\"reject\",\"2015-02-06\",2,10),\n" +
                                "(0,\"Ecellent\",\"accept\",\"2017-12-11\",1,6),\n" +
                                "(0,\"Not satisfy\",\"reject\",\"2014-02-19\",5,9),\n" +
                                "(0,\"Not relate with topic\",\"reject\",\"2017-09-05\",2,3),\n" +
                                "(0,\"Excellent\",\"accept\",\"2013-12-06\",4,8),\n" +
                                "(0,\"Good work\",\"accept\",\"2016-12-11\",3,6),\n" +
                                "(0,\"Data not relate with topic\",\"reject\",\"2017-10-01\",5,9),\n" +
                                "(0,\"Graet work\",\"accept\",\"2017-07-05\",9,5);";
                    
//                    String iq5 = "insert into users values(0,\"John\",\"john@gmail.com\",\"+7652834765\",\"123\",\"admin\",10);";
                    
                    String iq6 = "insert into view values(1,5);";
					
                    String iq7 = "insert into assign_member values(1,5),"
                            + "(2,4),(1,6),(3,6),(6,1);";
                    
                    stmt1.executeUpdate(iq1);
                    stmt1.executeUpdate(iq2);
                    stmt1.executeUpdate(iq3);
                    stmt1.executeUpdate(iq4);
//                    stmt1.executeUpdate(iq5);
                    stmt1.executeUpdate(iq6);
                    stmt1.executeUpdate(iq7);
                    
                    out.println("<script>alert('Database is initialized successfully !!')"); 
                    out.println("window.location = 'http://localhost:8080/web/index.html'</script>");
                    out.println("</body>");
                    out.println("</html>");
                    conn.close();
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
