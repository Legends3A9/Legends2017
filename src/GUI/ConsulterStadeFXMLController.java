/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import util.Data;
import entities.Stade;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author melek
 */
public class ConsulterStadeFXMLController implements Initializable {
    
    private ObservableList<Stade> data;
     static Data ds =Data.getInstance(); 
    private HBox hbox;
    @FXML
    private VBox vbox;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox vbox1;
    @FXML
    private Label nom_stadeA;
    @FXML
    private Label regionA;
    @FXML
    private Label adresseA;
    @FXML
    private Label date_constructionA;
    @FXML
    private Label surfaceA;
    @FXML
    private Label capaciteA;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        data = FXCollections.observableArrayList();
       addHBox();
    }    

    public VBox addHBox() {
    
    vbox.setPadding(new Insets(5, 5, 5, 5));
    vbox.setSpacing(10);
    vbox.setStyle("-fx-background-color: #336699;");
    vbox1.setPadding(new Insets(15, 12, 15, 12));
    vbox1.setSpacing(10);
    vbox1.setStyle("-fx-background-color: #336699;");
    TextField[] text =new TextField[6];
    
    
    try {
            String url = "jdbc:mysql://localhost:3306/russie2018";
            Connection conn = DriverManager.getConnection(url, "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT * FROM stade");
            while (rs.next()) {
                
                int ref_e = rs.getInt("ref");
                String nom_stadeE = rs.getString("nom_stade");
                
                String regionE = rs.getString("region"); 
                String adresseE = rs.getString("adresse"); 
                Date date_constructionE = rs.getDate("date_construction");
                
                
                String surfaceE = rs.getString("surface");
                Integer capaciteE ;
                capaciteE = rs.getInt("capacite");
                String imageE = rs.getString("image");
                 File file = new File("C:\\Users\\melek\\Desktop\\Images\\",imageE);
            Button buttonCurrent = new Button("Details");
            buttonCurrent.setOnAction(new EventHandler<ActionEvent>() {
    @Override public void handle(ActionEvent e) {
        nom_stadeA.setText(nom_stadeE);
        regionA.setText(regionE);
        adresseA.setText(adresseE);
        date_constructionA.setText(date_constructionE.toLocalDate().toString());
        surfaceA.setText(surfaceE);
        capaciteA.setText(Integer.toString(capaciteE));
        
    }
});
    buttonCurrent.setPrefSize(100, 100);
            ImageView im =new ImageView();
        Image image = new Image(file.toURI().toString(),100,100, false, false);
        im.setImage(image);
         Label textField = new Label(nom_stadeE);
        textField.setMinWidth(120);
        
    vbox.getChildren().addAll(im,buttonCurrent,textField);
                
                data.add(new Stade(ref_e,nom_stadeE,regionE,adresseE,date_constructionE,surfaceE,capaciteE,imageE));
               
                
            }
            
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    
        
       scrollPane.setContent(vbox);
 
        // Always show vertical scroll bar
        scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        
        // Horizontal scroll bar is only displayed when needed
        scrollPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
    
    System.out.println("aea");

    return vbox;
}
    
    public void ImageList() throws SQLException{
            String url = "jdbc:mysql://localhost:3306/russie2018";
            Connection conn = DriverManager.getConnection(url, "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs;
            System.out.println("recup table view ok !");
            rs = stmt.executeQuery("SELECT * FROM stade");
            while (rs.next()) {
                
               
                String nom_stadeE = rs.getString("nom_stade");
                //combobox.getItems().addAll(nom_stadeE);
                
                
                
          
                
                
            }
            conn.close();
            
            
        
        
    }

}
