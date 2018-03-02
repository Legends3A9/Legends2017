/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.application.Application;

import javafx.stage.Stage;



import java.io.*;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;


//      the pdf in an appropriate pdf viewer (if the user has such a viewer installed).
public class PdfWithImageCreator extends Application {
 public void start(Stage primaryStage) {
   FXMLLoader loader = new FXMLLoader(getClass().getResource("PDFticketFXML.fxml"));
        
        
        try {
           Parent root =  loader.load() ; 
           Scene scene = new Scene(root);
           Stage stage = new Stage () ; 
           stage.setScene(scene);
           stage.show();
            
            
        }
        catch(IOException ex) {
              

            
            
            
        }
    }
    

      public static void main(String[] args) { launch(args); }

   
   
}


