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
public class Data {
    private   String url="jdbc:mysql://localhost:3306/russie2018?autoReconnect=true&useSSL=false" ; 
    private String user="root" ; 
   private String password =""; 
   static Data instance;
    Connection connection ; 
    public static Data getInstance(){
        if(instance==null){
            instance = new Data();
        }
        return instance;
    }
    
    private Data() {


      try { 
              Class.forName("com.mysql.jdbc.Driver");
          connection=DriverManager.getConnection(url, user, password) ;
      } catch (ClassNotFoundException | SQLException ex) {
          Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
      }
    
    }

    public Connection getConnection() {
        return connection;
    }
    
    
}
