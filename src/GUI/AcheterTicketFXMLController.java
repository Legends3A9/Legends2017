/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.AjouterTicketFormFXMLController.ds;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entities.Stade;
import entities.Ticket;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import service.TicketService;

/**
 * FXML Controller class
 *
 * @author melek
 */
public class AcheterTicketFXMLController implements Initializable {
    
   
    @FXML
    private Label date_match;
    @FXML
    private Label heure;
    private Label reste;
    private Button retour;
    @FXML
    private ScrollPane scroll;
    @FXML
    private HBox hbox;
private ObservableList<Ticket> data;
    @FXML
    private ComboBox<String> matcha;
    @FXML
    private ComboBox<String> matche;
    @FXML
    private Label nombrea;
    @FXML
    private TextField datemat;
    @FXML
    private Button valider;
    @FXML
    private Label stade;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //load();
      
       /*match.valueProperty().addListener(new ChangeListener<String>() {
        @Override public void changed(ObservableValue ov, String t, String t1) {
        String sql="select * from ticket where rencontre= ?";
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(sql) ;
            ste.setString(1,t1) ;  
            ResultSet result =ste.executeQuery() ; 
            while (result.next()){
            
                                     
                                    String stadeA = result.getString("stade");
                                    Integer nombreA=result.getInt("nombre");
                                    String dateA=result.getString("date_match");
                                    
                                    
                stade.setText(stadeA);
                reste.setText(nombreA.toString());
                date_match.setText(dateA);
            
            }
            
        } catch (SQLException ex) {
            
        }
        }});*/
        addHBox();
        matcha.valueProperty().addListener(new ChangeListener<String>() {
        @Override public void changed(ObservableValue ov, String t, String t1) {
String sql="select * from ticket where (equipe1= ? AND date_match= ? )";
        try { String da=datemat.getText();
          matche.getItems().clear();
            PreparedStatement ste = ds.getConnection().prepareStatement(sql) ;
            ste.setString(1,t1) ; 
            ste.setString(2,da) ;  
            
            System.out.println(da);
            ResultSet resulta =ste.executeQuery() ; 
            
            while (resulta.next()){
            
                                     System.out.println("bbbb");
                                     String equipe2A=resulta.getString("equipe2");
                                     String heureA=resulta.getString("heure");
                                    String datemacth= resulta.getDate("date_match").toString();
                                    System.out.println(datemacth);
                                    matche.getItems().addAll(equipe2A);
                                    
                                    
                 
                
            
            }
            
        } catch (SQLException ex) {
            
        }
        }});
        matche.valueProperty().addListener(new ChangeListener<String>() {
        @Override public void changed(ObservableValue ov, String t, String t1) {
String sql="select * from ticket where (equipe1= ? AND equipe2= ? )";
        try { String equipe1A=matcha.valueProperty().getValue();
            PreparedStatement ste = ds.getConnection().prepareStatement(sql) ;
             ste.setString(1,equipe1A) ;
            ste.setString(2,t1) ;  
           
            ResultSet resultas =ste.executeQuery() ; 
            System.out.println(t1);
            while (resultas.next()){
            
                                     System.out.println("bbbb");
                                     String equipe2A=resultas.getString("equipe2");
                                     String stadeA=resultas.getString("stade");
                                     String heureA=resultas.getString("heure");
                                    String datemacth= resultas.getDate("date_match").toString();
                                    Integer nbr=resultas.getInt("nombre");
                                    System.out.println(datemacth);
                                    //equipe2.getItems().addAll(equipe2A);
                                    stade.setText(stadeA);
                                    date_match.setText(datemacth);
                                    heure.setText(heureA);
                                    nombrea.setText(nbr.toString());
                                    
                 
                
            
            }
            
        } catch (SQLException ex) {
            
        }
        }});
    }
     
      public HBox addHBox() {
  
    hbox.setPadding(new Insets(0, 0, 0, 0));
    hbox.setSpacing(5);
    //vbox.setStyle("-fx-background-color: #336699;");
    System.out.println("aaaaaaa"); 
    //vbox1.setPadding(new Insets(500, 500, 500, 500));
     
    //vbox1.setSpacing(10);
   // vbox1.setStyle("-fx-background-color: #336699;");
    TextField[] text =new TextField[6];
     
          for (int i = 14; i < 46; i++) {
              File file = new File("C:\\Users\\melek\\Documents\\NetBeansProjects\\russie2018\\Images\\calendrier\\"+i+".png");
              ImageView im =new ImageView();
              System.out.println(file);
        Image image = new Image(file.toURI().toString(),150,150, false, false);
        im.setImage(image);
        int j=0; 
        int k=06;
        
        j=i;      
        if (i==31) {
                  j=1;
                  k=07;
              }
        if (i==32) {
                  j=2;
                  k=07;
              }
        if (i==33) {
                  j=3;
                  k=07;
              }
        if (i==34) {
                  j=4;
                  k=07;
              }
        if (i==35) {
                  j=5;
                  k=07;
              }
        if (i==36) {
                  j=6;
                  k=07;
              }
        if (i==37) {
                  j=7;
                  k=07;
              }
        if (i==38) {
                  j=8;
                  k=07;
              }
        if (i==39) {
                  j=9;
                  k=07;
              }
        if (i==40) {
                  j=10;
                  k=07;
              }
        if (i==41) {
                  j=11;
                  k=07;
              }
        if (i==42) {
                  j=12;
                  k=07;
              }
        if (i==43) {
                  j=13;
                  k=07;
              }
        if (i==44) {
                  j=14;
                  k=07;
              }
        if (i==45) {
                  j=15;
                  k=07;
              }
        
        
        String d=("2018/"+k+"/"+j);
        im.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            
           
           String sql="select * from ticket where (date_match= ?)";
        try {
            PreparedStatement ste = ds.getConnection().prepareStatement(sql) ;
             ste.setString(1,d) ;
            matcha.getItems().clear();
                                     matche.getItems().clear();
           
            ResultSet resultas =ste.executeQuery() ; 
            System.out.println(d);
            while (resultas.next()){
                                     
                                    
                                     String equipe1A=resultas.getString("equipe1");
                                     String equipe2A=resultas.getString("equipe2");
                                    
                                     datemat.setText(d);
                                     System.out.println(equipe1A);
                                     
                                   // date_match.setText(datemacth);
                                    //heure.setText(heureA);
                                    matcha.getItems().addAll(equipe1A);
                 
                
            
            }
            
        } catch (SQLException ex) {
            
        }
         
        
                  
            
         event.consume();
     });
        hbox.getChildren().addAll(im); 
        scroll.setContent(hbox);
 
        // Always show vertical scroll bar
       scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        
        // Horizontal scroll bar is only displayed when needed
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
          }
    
                 
            
            
   
        
        
        
     
       // final Rating rating = new Rating();
   
    //vbox1.getChildren().addAll(textField);
              
                
                
          
            
           
    
        
       
    
    System.out.println("aea");

    return hbox;
}
     
     
     
     
    @FXML
    private void ActionValider(ActionEvent event) {
        
         
    Document document = new Document();
      try
      { 
         PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Ticket.pdf"));
         document.open();
         document.add(new Paragraph("Ticket reserver."));
          
         PdfPTable table = new PdfPTable(3); // 3 columns.
        table.setWidthPercentage(100); //Width 100%
        table.setSpacingBefore(10f); //Space before table
        table.setSpacingAfter(10f); //Space after table
 
        //Set Column widths
        float[] columnWidths = {1f, 1f, 1f};
        table.setWidths(columnWidths);
         
        PdfPCell cell1 = new PdfPCell(new Paragraph(stade.getText()));
        cell1.setBorderColor(BaseColor.BLUE);
        cell1.setPaddingLeft(100);
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
         
        PdfPCell cell2 = new PdfPCell(new Paragraph(stade.getText()));
        cell2.setBorderColor(BaseColor.GREEN);
        cell2.setPaddingLeft(100);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
       
        PdfPCell cell3 = new PdfPCell(new Paragraph(datemat.getText()));
        cell3.setBorderColor(BaseColor.RED);
        cell3.setPaddingLeft(100);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
       
        //To avoid having the cell border and the content overlap, if you are having thick cell borders
        //cell1.setUserBorderPadding(true);
        //cell2.setUserBorderPadding(true);
        //cell3.setUserBorderPadding(true);
 
  PdfPCell cell4 = new PdfPCell(new Paragraph(heure.getText()));
        cell4.setBorderColor(BaseColor.RED);
        cell4.setPaddingLeft(100);
        cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
       
        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        table.addCell(cell4);
       
        

       
        document.add(table);
         document.close();
         writer.close();
      } catch (DocumentException e)
      {
         e.printStackTrace();
      } catch (FileNotFoundException e)
      {
         e.printStackTrace();
      }
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Votre demende de reservation a ete prix en compte");
        alert.setHeaderText("Reserver");
       
        alert.showAndWait();
       // match.setValue("");
        stade.setText("");
        date_match.setText("");
        heure.setText("");
        
    }

    private void RetourAction(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource(("MenuFrontStadeFXML.fxml")));
        Parent root = loader.load();
        MenuFrontStadeFXMLController MenuFrontStadeControler = loader.getController();
        Scene scene = retour.getScene();
        scene.setRoot(root);
    }
    
}
