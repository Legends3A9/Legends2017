/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entités.ProduitPanier;
import Entités.Produit;
import Services.ServiceProduitPanier;
import Services.ServiceProduit;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author boussandel
 */
public class ClientProduitsController implements Initializable {

    ObservableList<Produit> data ;
    @FXML
    private ListView<Produit> list;
    @FXML
    private Label nomlbl;
    @FXML
    private Label taillelbl;
    @FXML
    private Label couleurlbl;
    @FXML
    private Label marquelbl;
    @FXML
    private Label prixlbl;
    @FXML
    private Label typelbl;
    @FXML
    private TextArea descriptionlbl;
    boolean selected = false;
    @FXML
    private Label cheminImage;
    @FXML
    private ImageView imageView;
    @FXML
    private Button btnAjouterPanier;
    @FXML
    private Button btnAfficherPanier;

    @FXML
    private void ajouterPanier(ActionEvent event) {
        
        
        if (selected == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("No Object selected");
            alert.setContentText("S'il vous plait selectionner un produit a ajouter.");
            alert.showAndWait();
        } else {
        ProduitPanier pan=new ProduitPanier();
        ServiceProduitPanier servicePanier = new ServiceProduitPanier();
        Produit p = list.getItems().get(list.getSelectionModel().getSelectedIndex());
        pan.setId(p.getId());
        pan.setLabel(p.getLabel());
        pan.setType(p.getType());
        pan.setPrix(p.getPrix());
        pan.setQuantite(p.getQuantite());
        pan.setMarque(p.getMarque());
        pan.setImage(p.getImage());
        pan.setCouleur(p.getCouleur());
        pan.setDescription(p.getDescription());
        pan.setTaille(p.getTaille());
        servicePanier.Add(pan);
        selected=false;
        }
    }

    @FXML
    private void afficherPanier(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("Panier.fxml"));
        
            Parent root = loader.load();
            Scene scene=new Scene(root);
            Stage stage= new Stage();
            stage.setTitle("Panier");
            stage.setScene(scene);
            stage.show();
       
    }

    @FXML
    private void Deconnection(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("Authentification.fxml"));
        Parent root = loader.load();
        Scene scene = couleurlbl.getScene();
        scene.setRoot(root);
    }


    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
   static public class Poules extends ListCell<Produit> { 
  
    
  
    public Poules() { 
       
    } 
  
  
    @Override
    protected void updateItem(Produit item, boolean bln) { 
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
}}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Services.ServiceProduit es = new Services.ServiceProduit();
        
        
       
                 
       

                  data = FXCollections.observableArrayList();
                  loadDataFromDatabase();
                  // setcellValue();
                  list.setCellFactory(lv -> new Poules());        
                  setcellValue();
                  
  
    }    
     private void loadDataFromDatabase() {
         
        try {
            ServiceProduit service=new ServiceProduit();
            List<Produit> rs = service.selectProduitsDispo();
            for(Produit p : rs)
            {
                
                
                
                data.add(p);
                System.out.println("recup table view ok !");
               
            }
          
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        list.setItems(data);
        
    }
     /* private void setcellValue() {
        list.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Equipe eqp = list.getItems().get(list.getSelectionModel().getSelectedIndex());
                    File file = new File("C:\\Users\\Dambo\\Desktop\\Russia2018\\"+eqp.getNom_equipe().toLowerCase()+".jpg");
            
        Image image = new Image(file.toURI().toString());
     File file2 = new File("C:\\Users\\Dambo\\Desktop\\Russia2018\\"+eqp.getContinent().toLowerCase()+".jpg");
            
        Image image2 = new Image(file2.toURI().toString());
            cont.setImage(image2); 
            flag.setImage(image); 
            eq.setText(eqp.getNom_equipe());
            ct.setText(eqp.getContinent());  
            }
        });
    }   */
   
    private void setcellValue() {
        list.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                Produit p = list.getItems().get(list.getSelectionModel().getSelectedIndex());
               
                nomlbl.setText(p.getLabel());
                prixlbl.setText(String.valueOf(p.getPrix()));
                typelbl.setText(p.getType());
                taillelbl.setText(p.getTaille());
                cheminImage.setText(p.getImage());
                marquelbl.setText(p.getMarque());
                couleurlbl.setText(p.getCouleur());
                descriptionlbl.setText(p.getDescription());
                     
                
                Image();
                selected=true;
                
            }
        });
    }
     private void Image(){
           
        File file = new File(cheminImage.getText());    
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
        
    }
}