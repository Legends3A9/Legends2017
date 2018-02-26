/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entités.ProduitPanier;
import Entités.Utilisateur;
import Services.ServiceProduitPanier;
import Services.UserService;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author GlaDio007
 */
public class PanierController implements Initializable {

    @FXML
    private ListView<ProduitPanier> list;
    ObservableList<ProduitPanier> data ;
    @FXML
    private Label total;
    @FXML
    private Button supprimer;
    private boolean selected= false;
    @FXML
    private Label idprod;
    @FXML
    private Button btnpayer;

    @FXML
    private void supprimer(ActionEvent event) {
         if (selected == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("No Object selected");
            alert.setContentText("S'il vous plait selectionner un produit a supprimer.");
            alert.showAndWait();
        } else {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Suppression");
            alert.setContentText("Vouler vous vraiment supprimer ce produit?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                ServiceProduitPanier service=new ServiceProduitPanier();
        int var = Integer.valueOf(idprod.getText());
        
        service.Remove(var);
        
        list.getItems().clear();
        data.clear();
        loadDataFromDatabase();
        selected=false;
        }
        

    }
       
        
    }

    @FXML
    private void payer(ActionEvent event) {
        float somme=Float.parseFloat(total.getText());
        UserService service = new UserService();
        Utilisateur user=service.findById(1);
        System.out.println(user);
        service.modifierArgentUser(user,somme);
        ServiceProduitPanier serv = new ServiceProduitPanier();
        serv.deleteAll();
        data.clear();
        loadDataFromDatabase();
        
    }

    /**
     * Initializes the controller class.
     */
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     * 
     */
    
    
   static public class Poules extends ListCell<ProduitPanier> { 
  
    
  
    public Poules() { 
       
    } 
  
  
    @Override
    protected void updateItem(ProduitPanier item, boolean bln) { 
         super.updateItem(item, bln);
                       
                            
                        if (item != null) {
                           
                           
                            
                            VBox vBox = new VBox(new Text(item.getLabel()),new Text(item.getMarque()), new Text(String.valueOf(item.getPrix())));
                             File file = new File(item.getImage());
            
                            Image image = new Image(file.toURI().toString());
                            ImageView img = new ImageView(image); 
                            img.setFitHeight(100);
                            img.setFitWidth(80);
                            
                            HBox hBox = new HBox(img, vBox);
                           
                             
                            Text t2 =new Text(item.getLabel());
                            Text t3 =new Text(item.getMarque());
                            Text t4 =new Text(String.valueOf(item.getPrix()));
                            
                             t2.setStyle("-fx-font-size: 25 arial;");
                              t3.setStyle("-fx-font-size: 20 arial;");
                            hBox.setSpacing(10);
                            setGraphic(hBox);
    } 
}   }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data = FXCollections.observableArrayList();
       

                  data = FXCollections.observableArrayList();
                  loadDataFromDatabase();
                  
                  list.setCellFactory(lv -> new PanierController.Poules()); 
                  setcellValue();
    }    
    
     private void loadDataFromDatabase() {
         float totall=0;
        try {
            
            ServiceProduitPanier service=new ServiceProduitPanier();
            List<ProduitPanier> rs = service.selectAll();
            for(ProduitPanier p : rs)
            {
                
                
                
                data.add(p);
                totall+=p.getPrix();
                System.out.println("recup table view ok !");
               
            }
          
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        list.setItems(data);
        total.setText(String.valueOf(totall));
        
    }   
     
         private void setcellValue() {
        list.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                ProduitPanier p = list.getItems().get(list.getSelectionModel().getSelectedIndex());
                
                int id=p.getId();
                idprod.setText(String.valueOf(id));
                selected=true;
                
            }
        });
    }
     
}
