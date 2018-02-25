/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author ali hamouda
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
          System.out.println("ok");
      } catch (ClassNotFoundException | SQLException ex) {
          Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
          System.out.println("erreur");
      }
    
    }

    public Connection getConnection() {
        return connection;
    }
    
   public static void main(String[]args)
   {
       DB data=DB.getInstance();
   }

   
    
}
