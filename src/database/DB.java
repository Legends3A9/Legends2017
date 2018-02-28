/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;


import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author khmai
 */
public class DB {

    private String url = "jdbc:mysql://localhost:3306/russia2018";
    private String login = "root";
    private String password = "";
    private Connection connection;
    private static DB dataSource;

   public  DB() {
        try {
            connection = DriverManager.getConnection(url, login, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DB getInstance() {
        if (dataSource == null) {
            dataSource = new DB();
        }
        return dataSource;
    }
}
