/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.ServiceProduitPanier;
import java.io.IOException;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *
 * @author GlaDio007
 */
public class Russia2018 extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException, SQLException{
        //FXMLLoader loader=new FXMLLoader(getClass().getResource("Menu.fxml"));
        FXMLLoader loader=new FXMLLoader(getClass().getResource("Menu.fxml"));
        
         ServiceProduitPanier service = new ServiceProduitPanier();
            Parent root = loader.load();
            Scene scene=new Scene(root);
            Stage stage= new Stage();
            stage.setTitle("Gestion Magasin");
            stage.setScene(scene);
            stage.show();
       
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
