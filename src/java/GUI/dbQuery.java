/*  
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author rajarshi 
 */
public class dbQuery extends SimpleTagSupport{
    Connection con=null;
    ResultSet rs=null;
    private String TYPE;
    private String cond;

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public String getCond() {
        return cond;
    }

    public void setCond(String cond) {
        this.cond = cond;
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        PageContext page=(PageContext) getJspContext();
        if(TYPE.equals("search")){
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='utf-8'>");
        out.println("<meta http-equiv='X-UA-Compatible' content='IE=edge'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1'>");
        out.println("<link rel='stylesheet' href='css/menu1.css'>");
        out.println("<link rel='stylesheet' href='css/form.css'>");
        out.println("<script src='http://code.jquery.com/jquery-latest.min.js' type='text/javascript'></script>");
        out.println("<script src='script.js'></script>");
        out.println("<title>search Data</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div id='cssmenu'>");
        out.println("<ul>");
        out.println("<li><a href='./dbUpdate.jsp '><span>Maintain</span></a></li>");
        out.println("<li><a href='./dbUpdate.jsp?TYPE=Insert'><span>Insert</span></a>");
    
        out.println("<li><a href='./dbQuery.jsp?TYPE=search'><span>Search</span></a></li>");
        out.println("<li><a href='./dbQuery.jsp?TYPE=Asearch'><span>Advanced Search</span></a></li>");
        out.println("<li class='last' style='float:right;'><a href='./dbQuery.jsp?TYPE=logout'><span>Logout</span></a></li>");
        out.println("</ul>");
        out.println("</div>");
        out.println("<div style='margin-left:100px;max-width:500px;color:white;background:black;opacity:0.9;' align='center' vertical-align='middle'>");
        out.println("<br>");
        out.println("<form method='post' style='color:white'><br>");
        out.print("Search:<input type='text' name='search' style='background:white' />");
        out.print(" <select >");
        out.print("<option value='ISBN'>ISBN</option>");
        out.print("<option value='Title'>Title</option>");
        out.print("<option value='Edition'>EditionNumber</option>");
        out.print("<option value='Copyright'>Copyright</option>");
        out.print("<option value='fname'>FirstName</option>");
        out.print("<option value='lname'>LastName</option>");
        out.print("</select>");
        out.println("<br><br><br>");
        out.println("<input type='submit' value='Search' />");
        out.println("<input type='submit' value='Show All Books' />");
        out.println("</form>");

        out.println("</div>");
        out.println("</body>");
        out.println ("</html>");
        String key=(String)page.getRequest().getParameter("search");
        try {
            rs=JDBCconnection.query(con, key);
            if(rs.next()){
               // rs.previous();
                out.println("<HTML>");
                // Start on the body
                out.println("<head>");
		
		out.println("<meta charset='utf-8'>");
                out.println("<link rel='stylesheet' href='css/table1_reset.css'>");
                out.println("<link rel='stylesheet' href='css/table1.css'>");

                out.println("</head>");
                out.println("<body>");
                out.println("<CENTER>");
          
                out.println("<section> ");
                out.println("<h1>Data Found</h1>  ");
                out.println("<div  class='tbl-header'>");
                out.println("<table cellpadding='0' cellspacing='0' border='0'>");
                out.println(" <thead><tr>");
                out.println("<th>ISBN</th><th>Title</th><th>Edition Number</th><th>Copyright</th><th>FirstName</th><th>LastName</th>");
                out.println("</tr> </thead>");
                out.println("</table>");
                out.println("</div>");
                out.println("<div  class='tbl-content'>");
                out.println("<table cellpadding='0' cellspacing='0' border='0'>");
                out.println(" <tbody>");
                while(rs.next()){
                    out.println("<tr>");
                    out.print("<td>"+(rs.getString("ISBN"))+ "</td>");
                    out.print("<td>"+rs.getString("Title")+ "</td>");
                    out.print("<td>"+rs.getString("EditionNumber")+ "</td>");
                    out.print("<td>"+rs.getString("Copyright")+ "</td>");
                    out.print("<td>"+rs.getString("FirstName")+ "</td>");
                    out.print("<td>"+rs.getString("LastName")+ "</td>");
                    out.println("</tr>");
                }
                out.println(" </tbody>");
                out.println("</table>");
                out.println("</div>");
                out.println("</section> ");
                out.println("<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>");

                out.println("<script src='js/index.js'></script>");
                out.println("</CENTER>");
                out.println("</BODY></HTML>") ; 
            }    
            } catch (SQLException | IOException e) {
                throw new JspException("Error in dbQuery tag", e);
            }
        }
        else if(TYPE.equals("Asearch")){
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='utf-8'>");
            out.println("<meta http-equiv='X-UA-Compatible' content='IE=edge'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1'>");
            out.println("<link rel='stylesheet' href='css/menu1.css'>");
            out.println("<link rel='stylesheet' href='css/form.css'>");
            out.println("<script src='http://code.jquery.com/jquery-latest.min.js' type='text/javascript'></script>");
            out.println("<script src='script.js'></script>");
            out.println("<title>search Data</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div id='cssmenu'>");
            out.println("<ul>");
            out.println("<li><a href='./dbUpdate.jsp '><span>Maintain</span></a></li>");
            out.println("<li><a href='./dbUpdate.jsp?TYPE=Insert'><span>Insert</span></a>");
            out.println("<li><a href='./dbQuery.jsp?TYPE=search'><span>Search</span></a></li>");
            out.println("<li><a href='./dbQuery.jsp?TYPE=Asearch'><span>Advanced Search</span></a></li>");

            out.println("<li class='last' style='float:right;'><a href='./dbQuery.jsp?TYPE=logout'><span>Logout</span></a></li>");
            out.println("</ul>");
            out.println("</div>");
            out.println("<div style='margin-left:100px;max-width:500px;color:white;background:black;opacity:0.9;' align='center' vertical-align='middle'>");
            out.println("<form method='post' action='./dbQuery.jsp?TYPE=Asearch' ><br><br>");
            out.print("Search:<input type='text' name='search' style='background:white'/>");
            out.print(" <select >");
            out.print("<option value='ISBN'>ISBN</option>");
            out.print("<option value='Title'>Title</option>");
            out.print("<option value='Edition'>EditionNumber</option>");
            out.print("<option value='Copyright'>Copyright</option>");
            out.print("<option value='fname'>FirstName</option>");
            out.print("<option value='lname'>LastName</option>");
            out.print("</select>");
            out.println("<br><br><br>");
            out.println(" <input type='radio' name='cond' value='AND' checked> AND");
            out.println("<input type='radio' name='cond' value='OR'> OR");
            out.println(" <input type='radio' name='cond' value='NOT'> NOT ");
            out.println("<br><br>");
            out.print("Search:<input type='text' name='Asearch' style='background:white' />");
            out.print(" <select >");
            out.print("<option value='ISBN'>ISBN</option>");
            out.print("<option value='Title'>Title</option>");
            out.print("<option value='Edition'>EditionNumber</option>");
            out.print("<option value='Copyright'>Copyright</option>");
            out.print("<option value='fname'>FirstName</option>");
            out.print("<option value='lname'>LastName</option>");
            out.print("</select>");
            out.println("<br><br><br>");
            out.println("<input type='submit' value='SEARCH' style='width:150px'/>");
            out.println("</form>");

            out.println ("</div> ");      

            out.println("</body>");
            out.println ("</html>");
            String key1=(String)page.getRequest().getParameter("search");
            String key2=(String)page.getRequest().getParameter("Asearch");
            if(cond.equals("AND")){
                try {
                    rs=JDBCconnection.query2(con, key1,cond,key2);
                    if(rs.next()){
                        rs.previous();
                        out.println("<HTML>");
                        // Start on the body
                        out.println("<head>");

                        out.println("<meta charset='utf-8'>");
                        out.println("<link rel='stylesheet' href='css/table1_reset.css'>");
                        out.println("<link rel='stylesheet' href='css/table1.css'>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<CENTER>");

                        out.println("<section> ");
                        out.println("<h1>Data Found</h1>  ");
                        out.println("<div  class='tbl-header'>");
                        out.println("<table cellpadding='0' cellspacing='0' border='0'>");
                        out.println(" <thead><tr>");
                        out.println("<th>ISBN</th><th>Title</th><th>Edition Number</th><th>Copyright</th><th>FirstName</th><th>LastName</th>");
                        out.println("</tr> </thead>");
                        out.println("</table>");
                        out.println("</div>");
                        out.println("<div  class='tbl-content'>");
                        out.println("<table cellpadding='0' cellspacing='0' border='0'>");
                        out.println(" <tbody>");
                        while(rs.next()){
                            out.println("<tr>");
                            out.print("<td>"+(rs.getString("ISBN"))+ "</td>");
                            out.print("<td>"+rs.getString("Title")+ "</td>");
                            out.print("<td>"+rs.getString("EditionNumber")+ "</td>");
                            out.print("<td>"+rs.getString("Copyright")+ "</td>");
                            out.print("<td>"+rs.getString("FirstName")+ "</td>");
                            out.print("<td>"+rs.getString("LastName")+ "</td>");
                            out.println("</tr>");
                        }
                        out.println(" </tbody>");
                        out.println("</table>");
                        out.println("</div>");
                        out.println("</section> ");
                        out.println("<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>");

                        out.println("<script src='js/index.js'></script>");
                        out.println("</CENTER>");
                        out.println("</BODY></HTML>") ; 
                    }

                }catch (SQLException | IOException e) {
                throw new JspException("Error in dbQuery tag", e);
                }
            }
            else if(cond.equals("OR")){
                try {
                    rs=JDBCconnection.query3(con, key1,cond,key2);
                    if(rs.next()){
                        rs.previous();
                        out.println("<HTML>");
                        // Start on the body
                        out.println("<head>");

                        out.println("<meta charset='utf-8'>");
                        out.println("<link rel='stylesheet' href='css/table1_reset.css'>");
                        out.println("<link rel='stylesheet' href='css/table1.css'>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<CENTER>");
                        out.println("<section> ");
                        out.println("<h1>Data Found</h1>  ");
                        out.println("<div  class='tbl-header'>");
                        out.println("<table cellpadding='0' cellspacing='0' border='0'>");
                        out.println(" <thead><tr>");
                        out.println("<th>ISBN</th><th>Title</th><th>Edition Number</th><th>Copyright</th><th>FirstName</th><th>LastName</th>");
                        out.println("</tr> </thead>");
                        out.println("</table>");
                        out.println("</div>");
                        out.println("<div  class='tbl-content'>");
                        out.println("<table cellpadding='0' cellspacing='0' border='0'>");
                        out.println(" <tbody>");
                        while(rs.next()){
                            out.println("<tr>");
                            out.print("<td>"+(rs.getString("ISBN"))+ "</td>");
                            out.print("<td>"+rs.getString("Title")+ "</td>");
                            out.print("<td>"+rs.getString("EditionNumber")+ "</td>");
                            out.print("<td>"+rs.getString("Copyright")+ "</td>");
                            out.print("<td>"+rs.getString("FirstName")+ "</td>");
                            out.print("<td>"+rs.getString("LastName")+ "</td>");
                            out.println("</tr>");
                        }
                        out.println(" </tbody>");
                        out.println("</table>");
                        out.println("</div>");
                        out.println("</section> ");
                        out.println("<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>");

                        out.println("<script src='js/index.js'></script>");
                        out.println("</CENTER>");
                        out.println("</BODY></HTML>") ; 
                    }
                }catch (SQLException | IOException e) {
                throw new JspException("Error in dbQuery tag", e);
                }
            }
            else if(cond.equals("NOT")){
                try {
                    rs=JDBCconnection.query4(con, key1,cond,key2);

                    if(rs.next()){
                        rs.previous();
                        out.println("<HTML>");
                        // Start on the body
                        out.println("<head>");

                        out.println("<meta charset='utf-8'>");
                        out.println("<link rel='stylesheet' href='css/table1_reset.css'>");
                        out.println("<link rel='stylesheet' href='css/table1.css'>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<CENTER>");

                        out.println("<section> ");
                        out.println("<h1>Data Found</h1>  ");
                        out.println("<div  class='tbl-header'>");
                        out.println("<table cellpadding='0' cellspacing='0' border='0'>");
                        out.println(" <thead><tr>");
                        out.println("<th>ISBN</th><th>Title</th><th>Edition Number</th><th>Copyright</th><th>FirstName</th><th>LastName</th>");
                        out.println("</tr> </thead>");
                        out.println("</table>");
                        out.println("</div>");
                        out.println("<div  class='tbl-content'>");
                        out.println("<table cellpadding='0' cellspacing='0' border='0'>");
                        out.println(" <tbody>");
                        while(rs.next()){
                            out.println("<tr>");
                            out.print("<td>"+(rs.getString("ISBN"))+ "</td>");
                            out.print("<td>"+rs.getString("Title")+ "</td>");
                            out.print("<td>"+rs.getString("EditionNumber")+ "</td>");
                            out.print("<td>"+rs.getString("Copyright")+ "</td>");
                            out.print("<td>"+rs.getString("FirstName")+ "</td>");
                            out.print("<td>"+rs.getString("LastName")+ "</td>");
                            out.println("</tr>");
                        }
                        out.println(" </tbody>");
                        out.println("</table>");
                        out.println("</div>");
                        out.println("</section> ");
                        out.println("<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>");

                        out.println("<script src='js/index.js'></script>");
                        out.println("</CENTER>");
                        out.println("</BODY></HTML>") ; 
                    }

                }catch (SQLException | IOException e) {
                throw new JspException("Error in dbQuery tag", e);
                }
            }
        }
    }
}

