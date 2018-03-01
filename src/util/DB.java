/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author melek
 */
public class DB {
    private   String url="jdbc:mysql://localhost:3306/russia2018?autoReconnect=true&useSSL=false" ; 
    private String user="root" ; 
   private String password =""; 
   static DB instance;
    Connection connection ; 
    public static DB getInstance(){
        if(instance==null){
            instance = new DB();
        }
        return instance;
    }
    
    private DB() {


      try { 
              Class.forName("com.mysql.jdbc.Driver");
          connection=DriverManager.getConnection(url, user, password) ;
      } catch (ClassNotFoundException | SQLException ex) {
          Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
      }
    
    }

    public Connection getConnection() {
        return connection;
    }
    
    
}
