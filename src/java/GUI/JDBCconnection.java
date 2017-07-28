/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rajarshi
 */
package GUI;
import com.mysql.jdbc.Connection;
import javax.naming.spi.DirStateFactory.Result;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;


/**
 *
 * @author rajarshi yelamati
 */
public class JDBCconnection {
    private static final String url="jdbc:mysql://localhost:3306/books";
    private static final String name="ryelamati";
    private static final String password="mnagaraju9";
    ResultSet rs=null;
    Connection con=null;
    PreparedStatement pst=null;
    Object[][] data=null;
    String [] ColumnNames=new String[6];
    
    static public Connection DBConnection(){
        Connection con=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=(Connection) DriverManager.getConnection(url,name,password);
            //HttpSession sess=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            //sess.setAttribute("conn", con);
            //JOptionPane.showMessageDialog(null,"Database Connected Successfully..!!");
            return con;
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
            return null;
        } catch (ClassNotFoundException e) {
            Logger.getLogger(JDBCconnection.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    static ResultSet DisplayData(Connection con) {
        //Connection con=null;
        ResultSet rs;
        try{
            // FacesContext fc = FacesContext.getCurrentInstance();
            // HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();
            //HttpSession session = (HttpSession)req.getSession(false);
            //con = (Connection)session.getAttribute("conn");
            con=DBConnection();
            con=(Connection) DriverManager.getConnection(url,name,password);
            String sql="SELECT T.ISBN, T.Title, T.EditionNumber,T.Copyright,A.FirstName,A.LastName from Titles as T, AuthorISBN as I,Authors as A WHERE T.ISBN = I.ISBN AND I.AuthorID = A.AuthorID";
            PreparedStatement pst;
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            return rs;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    static ResultSet query(java.sql.Connection con, String searchkey) {
            PreparedStatement pst=null;
           // Connection con=null;
            ResultSet rs;
            try{
                // Class.forName("com.mysql.jdbc.Driver");
           // con=DriverManager.getConnection(url,name,password);
            String sql="select T.ISBN,T.Title,T.EditionNumber,T.Copyright,A.FirstName,A.LastName FROM Titles as T JOIN AuthorISBN as I ON T.ISBN=I.ISBN JOIN Authors as A ON A.AuthorID=I.AuthorID WHERE concat(T.ISBN,T.Title,T.EditionNumber,T.Copyright,A.FirstName,A.LastName) LIKE ? ";
          pst=con.prepareStatement(sql);
          pst.setString(1, "%"+searchkey+"%");
          
            rs=pst.executeQuery();
            return rs;
           
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    static ResultSet query2(java.sql.Connection con, String key1, String cond, String key2) {
            PreparedStatement pst=null;
           // Connection con=null;
            ResultSet rs = null;
           // System.out.println(constraint);
            
            try{
                // Class.forName("com.mysql.jdbc.Driver");
           // con=DriverManager.getConnection(url,name,password);
            String sql="select T.ISBN,T.Title,T.EditionNumber,T.Copyright,A.FirstName,A.LastName FROM Titles as T JOIN AuthorISBN as I ON T.ISBN=I.ISBN JOIN Authors as A ON A.AuthorID=I.AuthorID WHERE concat(T.ISBN,T.Title,T.EditionNumber,T.Copyright,A.FirstName,A.LastName)  LIKE ?  AND concat(T.ISBN,T.Title,T.EditionNumber,T.Copyright,A.FirstName,A.LastName) LIKE ? ";
          pst=con.prepareStatement(sql);
         pst.setString(1, "%"+key1+"%");
          //pst.setString(2, "%"+constraint+"%");
          pst.setString(2, "%"+key2+"%");
          
            rs=pst.executeQuery();
           // return rs;
           
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
        return rs;
    }

    static ResultSet query3(java.sql.Connection con, String key1, String cond, String key2) {
        PreparedStatement pst=null;
       // Connection con=null;
        ResultSet rs = null;
       // System.out.println(constraint);

        try{
            // Class.forName("com.mysql.jdbc.Driver");
       // con=DriverManager.getConnection(url,name,password);
        String sql="select T.ISBN,T.Title,T.EditionNumber,T.Copyright,A.FirstName,A.LastName FROM Titles as T JOIN AuthorISBN as I ON T.ISBN=I.ISBN JOIN Authors as A ON A.AuthorID=I.AuthorID WHERE concat(T.ISBN,T.Title,T.EditionNumber,T.Copyright,A.FirstName,A.LastName)  LIKE ?  OR concat(T.ISBN,T.Title,T.EditionNumber,T.Copyright,A.FirstName,A.LastName) LIKE ? ";
        pst=con.prepareStatement(sql);
        pst.setString(1, "%"+key1+"%");
        //pst.setString(2, "%"+constraint+"%");
        pst.setString(2, "%"+key2+"%");
      
        rs=pst.executeQuery();
        // return rs;
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
        return rs;
    }

    static ResultSet query4(java.sql.Connection con, String key1, String cond, String key2) {
                PreparedStatement pst=null;
           // Connection con=null;
            ResultSet rs = null;
           // System.out.println(constraint);
            
            try{
                // Class.forName("com.mysql.jdbc.Driver");
           // con=DriverManager.getConnection(url,name,password);
            String sql="select T.ISBN,T.Title,T.EditionNumber,T.Copyright,A.FirstName,A.LastName FROM Titles as T JOIN AuthorISBN as I ON T.ISBN=I.ISBN JOIN Authors as A ON A.AuthorID=I.AuthorID WHERE concat(T.ISBN,T.Title,T.EditionNumber,T.Copyright,A.FirstName,A.LastName)  LIKE ?  AND concat(T.ISBN,T.Title,T.EditionNumber,T.Copyright,A.FirstName,A.LastName) NOT LIKE ? ";
          pst=con.prepareStatement(sql);
         pst.setString(1, "%"+key1+"%");
          //pst.setString(2, "%"+constraint+"%");
          pst.setString(2, "%"+key2+"%");
          
            rs=pst.executeQuery();
           // return rs;
           
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
        return rs;
    }

    static boolean AddData(String ISBN, String Title, String Editionno, String Copyright, String Fname, String Lname, java.sql.Connection con) {
        //Connection con=null;
        try{
            //FacesContext fc = FacesContext.getCurrentInstance();
            //HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();
            //HttpSession session = (HttpSession)req.getSession(false);
            //con = (Connection)session.getAttribute("conn");
             //con=DBConnection();
            //con=DriverManager.getConnection(url,name,password);
            String query1 = "INSERT INTO Titles(ISBN, TITLE, EditionNumber,Copyright) values('"+ISBN+"','"+Title+"','"+Editionno+"','"+Copyright+"')";
            PreparedStatement pst1 = con.prepareStatement(query1);	
            String query2 = "INSERT INTO Authors(FirstName, LastName) values('"+Fname+"' , '"+Lname+"' ) ";
            PreparedStatement pst2 = con.prepareStatement(query2);

            //JOptionPane.showMessageDialog(null, "executed1!");
            String sql1=" SELECT ISBN from Titles where Title='"+Title+"' and EditionNumber='"+Editionno+"'";
            Statement st1=con.createStatement();
            ResultSet rs=st1.executeQuery(sql1);
            int Book_isbn;
            if(rs.next()){
                Book_isbn=rs.getInt("ISBN");
                // JOptionPane.showMessageDialog(null, "Executed 1!"+Book_isbn);
                //JOptionPane.showMessageDialog(null, "Executed 1..Book written by!");

                String sql2= "SELECT AuthorID from Authors WHERE FirstName= '"+Fname+"' AND LastName='"+Lname+"' ";
                Statement st2=con.createStatement();
                ResultSet rs1=st2.executeQuery(sql2);
                //JOptionPane.showMessageDialog(null, "Executed2!");
                int authId;
                int autoIncKeyFromFunc = -1;
                if(rs1.next()){
                    authId=rs1.getInt("AuthorID");
                    String Query3=" INSERT INTO AuthorISBN(AuthorID,ISBN) VALUES ('"+authId+"','"+Book_isbn+"')";
                    st2.executeUpdate(Query3);
                    // JOptionPane.showMessageDialog(null, "executed3!");
                    //JOptionPane.showMessageDialog(null, "Executed 1..Book read by!");
                }
                else{
                    pst2.executeUpdate();           
                    ResultSet rs3 =pst2.executeQuery("SELECT LAST_INSERT_ID()");
                    if (rs3.next()) {
                        autoIncKeyFromFunc = rs3.getInt(1);
                        String Query4=" INSERT INTO AuthorISBN(AuthorID,ISBN) VALUES ('"+autoIncKeyFromFunc+"','"+Book_isbn+"')";
                        PreparedStatement st3=con.prepareStatement(Query4);
                        st3.executeUpdate();
                        // JOptionPane.showMessageDialog(null, "executed4!");
                    }
                }
                // JOptionPane.showMessageDialog(null, "Book Added Successfully!");
            }
            else 
            {
                pst1.executeUpdate();
                String sql2= "SELECT AuthorID from Authors WHERE FirstName= '"+Fname+"' AND LastName='"+Lname+"' ";
                Statement st2=con.createStatement();
                ResultSet rs10=st2.executeQuery(sql2);
                //JOptionPane.showMessageDialog(null, "Executed5!");
                int authId;
                int autoIncKeyFromFunc = -1;
                if(rs10.next()){
                    authId=rs10.getInt("AuthorID");
                    String Query5=" INSERT INTO AuthorISBN(AuthorID,ISBN) VALUES ('"+authId+"','"+ISBN+"')";
                    st2.executeUpdate(Query5);
                    // JOptionPane.showMessageDialog(null, "executed6!");
                }
                else{
                    pst2.executeUpdate();           
                    Statement st3=con.createStatement();
                    ResultSet rs3 =pst2.executeQuery("SELECT LAST_INSERT_ID()");
                    // JOptionPane.showMessageDialog(null, "executed7!");
                    if (rs3.next()) {
                        autoIncKeyFromFunc = rs3.getInt(1);
                        String Query6=" INSERT INTO AuthorISBN(AuthorID,ISBN) VALUES ('"+autoIncKeyFromFunc+"','"+ISBN+"')";
                        st3.executeUpdate(Query6);
                        //JOptionPane.showMessageDialog(null, "executed8!");
                    }
                }
            }
            return true;
            // JOptionPane.showMessageDialog(null, "Book Added Successfully!");
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    static ResultSet edit(java.sql.Connection con, String ISBN) {
            PreparedStatement pst=null;
           // Connection con=null;
            ResultSet rs;
            try{
                // Class.forName("com.mysql.jdbc.Driver");
           // con=DriverManager.getConnection(url,name,password);
             String sql="select T.Title, T.EditionNumber, T.Copyright,A.FirstName,A.LastName from Titles as T  JOIN AuthorISBN as I on T.ISBN=I.ISBN JOIN Authors as A on A.AuthorID=I.AuthorID where T.ISBN='"+ISBN+"'";
             pst=con.prepareStatement(sql);
               rs=pst.executeQuery();
          
           
            return rs;
           
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    static boolean deleteData(String ISBN, java.sql.Connection con) {
        //Connection con=null;
        try{
            // con= DBConnection();

            ///HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();
            // HttpSession session = (HttpSession)req.getSession(false);
            //con = (Connection)session.getAttribute("conn");
            //con=DriverManager.getConnection(url,name,password);
            String sql1="DELETE from AuthorISBN where ISBN='"+ISBN+"'";
            String sql2="DELETE from Titles WHERE ISBN='"+ISBN+"'";
             
            PreparedStatement pst1=con.prepareStatement(sql1);
            PreparedStatement pst2=con.prepareStatement(sql2);
           
            String sql="SELECT * FROM AuthorISBN WHERE ISBN='"+ISBN+"'";
            PreparedStatement pst=con.prepareStatement(sql);
         
            ResultSet rs= pst.executeQuery();
            int AuthID;
            //double BookISBN;
            if(rs.next()){
                AuthID=rs.getInt(1);
                //bookISBN=rs.getDouble(2);
                String sql3="DELETE from Authors where AuthorID='"+AuthID+"'"; 
                PreparedStatement pst3=con.prepareStatement(sql3);
                String Query1="SELECT COUNT(ISBN) FROM AuthorISBN WHERE AuthorID='"+AuthID+"'";
                PreparedStatement pst4=con.prepareStatement(Query1);
                ResultSet rs1=pst4.executeQuery();
                if(rs1.next()){
                    int isbn=rs1.getInt(1);
                    // System.out.println(isbn);
                    if(isbn==0){
                        //int confirm = JOptionPane.showConfirmDialog(null, "No books found for this Author, Do you really want to delete this Author?", "Delete", JOptionPane.YES_NO_OPTION);
			//if(confirm == 0)
			//{
                        pst3.execute();
                        pst3.close();
                        //  JOptionPane.showMessageDialog(null," Book Deleted Successfully...!!");
			//}
                    }
                    else if (isbn==1){
                      //  int confirm = JOptionPane.showConfirmDialog(null, "Do you really want to delete this Author?", "Delete", JOptionPane.YES_NO_OPTION);
			//if(confirm == 0)
			//{
                        pst1.executeUpdate();
                        pst2.executeUpdate();
                        pst3.executeUpdate();
                        pst3.close();
                                
                        pst2.close();
                                
                        pst1.close();
                        //  JOptionPane.showMessageDialog(null," Book Deleted Successfully...!!");
			//}
                    }
                    else
                    {
                        
                        // int confirm = JOptionPane.showConfirmDialog(null, "Another Book found for this Author, Do you really want to delete this Author?", "Delete", JOptionPane.YES_NO_OPTION);
                        //	if(confirm == 0)
                        //	{
                        pst1.execute();
                        pst3.close();
                        pst2.execute();
                        pst2.close();
                        //JOptionPane.showMessageDialog(null," Book Deleted Successfully...!!");
                    }
                }
            }
            else{
                //JOptionPane.showMessageDialog(null, "Error!!");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public static boolean UpdateData(String ISBN, String Title, String EditionNumber, String Copyright, String FirstName, String LastName, java.sql.Connection con) {
        // Connection con=null;
        PreparedStatement pst1=null;
        try{
            // con= DBConnection();
            // FacesContext fc = FacesContext.getCurrentInstance();
            //HttpServletRequest req = (HttpServletRequest) fc.getExternalContext().getRequest();
            //HttpSession session = (HttpSession)req.getSession(false);
            //con = (Connection)session.getAttribute("conn");
            //con=DriverManager.getConnection(url,name,password);
            String sql1=" Update Titles SET Title= ? ,EditionNumber= ?,Copyright=? WHERE ISBN= ? ";
            String sql="SELECT AuthorID FROM AuthorISBN where ISBN='"+ISBN+"'";
            System.out.println("ISBN is"+ISBN);
            PreparedStatement pst3=con.prepareStatement(sql);
            ResultSet rs=pst3.executeQuery();
            int Authid=0;
            if(rs.next()){
                Authid=rs.getInt("AuthorID");
            }
           
            String sql2="Update Authors SET FirstName=? , LastName=? Where AuthorID='"+Authid+"' ";
            
            PreparedStatement pst=con.prepareStatement(sql1);
            PreparedStatement pst2=con.prepareStatement(sql2);
            pst.setString(1, Title);
            pst.setString(2,EditionNumber);
            pst.setString(3, Copyright);
            pst2.setString(1, FirstName);
            pst2.setString(2,LastName);
            pst.setString(4, ISBN);
            System.out.println("ISBN"+ISBN);
            pst.executeUpdate();
            pst2.executeUpdate();

            return true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
