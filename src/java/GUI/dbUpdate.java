/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author rajarshi
 */
public class dbUpdate extends SimpleTagSupport {
    private String TYPE;
    private String ISBN;
    Connection con=null;
    ResultSet rs=null;
    private String STATUS;

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        PageContext page=(PageContext) getJspContext();
        HttpServletRequest req=(HttpServletRequest)page.getRequest();
        HttpServletResponse res=(HttpServletResponse)page.getResponse();
        if(TYPE.equals("Insert")){
//        out.clear();
        try{
            out.println("<html>");
            out.println("<head>");

            out.println("<meta charset='utf-8'>");
            out.println("<meta http-equiv='X-UA-Compatible' content='IE=edge'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1'>");
            out.println("<link rel='stylesheet' href='css/menu1.css'>");
            out.println("<link rel='stylesheet' href='css/form.css'>");
            out.println("<script src='http://code.jquery.com/jquery-latest.min.js' type='text/javascript'></script>");
            out.println("<script src='script.js'></script>");
            out.println("<title>Insert Data</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div id='cssmenu'>");
            out.println("<ul>");
            out.println("<li><a href='./dbUpdate.jsp '><span>Maintain</span></a></li>");
            out.println("<li class='active has-sub'><a href='./dbUpdate.jsp?TYPE=Insert'><span>Insert</span></a>");
            out.println("<li><a href='./dbQuery.jsp?TYPE=search'><span>Search</span></a></li>");
            out.println("<li><a href='./dbQuery.jsp?TYPE=Asearch'><span>Advanced Search</span></a></li>");
            out.println("<li class='last' style='float:right;'><a href='./dbQuery.jsp?TYPE=logout'><span>Logout</span></a></li>");
            out.println("</ul>");
            out.println("</div>");
            out.println("<section class='container' >");
            out.println("<div class='login'>");
            out.println("<h1>Insert Book</h1>");
            out.println("<center>");
            out.println("<form method='post' style='color:white'>");
            out.print("<p><input type='text' name='ISBN'  placeholder='Please enter ISBN'style='color:white'/></p>");
            out.print (" <p><input type='text' name='Title' placeholder='Please enter Title' style='color:white' /></p>");
            out.print ("<p><input type='text' name='EditionNumber' placeholder='Please enter EditionNumber' style='color:white' /></p>");
            out.print ("<p><input type='text' name='Copyright' placeholder='Please enter Copyright of Book'style='color:white'/></p>");
            out.print ("<p><input type='text' name='firstname' placeholder='Please enter First Name of Author'style='color:white'/></p>");
            out.print("<p><input type='text' name='lastname' placeholder='Please enter Last Name of Author'style='color:white'/></p>");
            out.println("<p><input type='submit' value='Insert' /></p>");
            out.println("</form>");

            out.println ("</center> ");      
            out.println("</div>");
            out.println("</body>");
            out.println ("</html>");
            
            String isbn=(String) page.getRequest().getParameter("ISBN");
            String Title=(String)page.getRequest().getParameter("Title");
            String edition=(String)page.getRequest().getParameter("EditionNumber");
            String copyright=(String)page.getRequest().getParameter("Copyright");
            String firstname=(String)page.getRequest().getParameter("firstname");
            String lastname=(String)page.getRequest().getParameter("lastname");
            
            boolean  status=JDBCconnection.AddData(isbn, Title, edition, copyright, firstname, lastname, con);

            if(status==true){
                res.sendRedirect("./dbUpdate.jsp");
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    else if(TYPE.equals("delete")){
        try{
            boolean delete=JDBCconnection.deleteData(ISBN, con);
            if(delete==true){
                out.println("Book deleted successfully");
                res.sendRedirect("./dbUpdate.jsp");
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    else if(TYPE.equals("edit")){
        out.clear();
        try{
           rs=JDBCconnection.edit(con, ISBN);
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='utf-8'>");
            out.println("<meta http-equiv='X-UA-Compatible' content='IE=edge'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1'>");
            out.println("<link rel='stylesheet' href='css/menu1.css'>");
            out.println("<script src='http://code.jquery.com/jquery-latest.min.js' type='text/javascript'></script>");
            out.println("<script src='script.js'></script>");
            out.println("<link rel='stylesheet' href='css/form.css'>");
            out.println("<title>Update Data</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div id='cssmenu'>");
            out.println("<ul>");
            out.println("<li><a href='./dbUpdate.jsp '><span>Maintain</span></a></li>");
            out.println("<li class='active has-sub'><a href='./dbUpdate.jsp?TYPE=Insert'><span>Insert</span></a>");

            out.println("<li><a href='./dbQuery.jsp?TYPE=search'><span>Search</span></a></li>");
            out.println("<li><a href='./dbQuery.jsp?TYPE=Asearch'><span>Advanced Search</span></a></li>");
            out.println("<li class='last' style='float:right;'><a href='./dbQuery.jsp?TYPE=logout'><span>Logout</span></a></li>");
            out.println("</ul>");
            out.println("</div>");

            out.println("<section class='container'>");
            out.println("<div class='login'>");
            out.println("<h1>Update Book</h1>");
            out.println("<center>");
            out.println("<form method='post' ");
            while(rs.next()){
               
                String Title=rs.getString("Title");
                String EditionNumber=rs.getString("EditionNumber");
                String Copyright=rs.getString("Copyright");
                String FirstName=rs.getString("FirstName");
                String LastName=rs.getString("LastName");
                out.println("<p>ISBN: <input type='text' style='background:white' name='ISBN' value='"+ISBN+"' readonly/></p>");
                out.println("<p>Title: <input type='text' style='background:white' name='Title' size='35' value='"+Title+"' /></p>");
                out.println("<p>EditionNumber: <input type='text'style='background:white' name='EditionNumber' value='"+EditionNumber+"'/></p>");
                out.println("<p>Copyright: <input type='text' style='background:white' name='Copyright' value='"+Copyright+"' /></p>");
                out.println("<p>FirstName: <input type='text'style='background:white' name='FirstName' value='"+FirstName+"' /></p>");
                out.println("<p>LastName: <input type='text' style='background:white' name='LastName' value='"+LastName+"' /></p>");
            
            out.println("<p><input type='submit' value='Update' /></p>");
            out.println("</form>");
            out.println ("</center> ");      
            out.println("</div>");
            out.println("</BODY></HTML>") ;
            }
            String isbn=(String) page.getRequest().getParameter("ISBN");
            String Title=(String)page.getRequest().getParameter("Title");
            String edition=(String)page.getRequest().getParameter("EditionNumber");
            String copyright=(String)page.getRequest().getParameter("Copyright");
            String firstname=(String)page.getRequest().getParameter("FirstName");
            String lastname=(String)page.getRequest().getParameter("LastName");
            boolean update=JDBCconnection.UpdateData(isbn, Title, edition, copyright, firstname, lastname, con);
            if(update==true){
                res.sendRedirect("./dbUpdate.jsp");
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}


    
}
