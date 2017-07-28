/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.mysql.jdbc.Connection;
import java.sql.ResultSet;

/**
 *
 * @author rajarshi yelamati
 */

public class dbConnectionBean {
    private String username;
    private String password;
    Connection con=null;
    ResultSet rs=null;

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public Connection DBConnection(){
        try{
            con = JDBCconnection.DBConnection();
            return con;
        }catch(Exception e){
            e.printStackTrace();
            
        }
        return con;
    }
    
    public boolean LoginValidation(){
        return this.username.equals("raja")&&this.password.equals("raja123");
    }
    
}
