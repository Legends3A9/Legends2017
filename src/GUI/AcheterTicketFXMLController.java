/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDJpeg;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.*;
import java.io.*;
import java.nio.file.Paths;
import java.util.Arrays;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import static GUI.AjouterTicketFormFXMLController.ds;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import Entitiés.Stade;
import Entitiés.Ticket;
import Entités.Utilisateur;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import Services.TicketService;
import javafx.scene.control.Separator;

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
     private static final String PDF_PATH =
            Paths.get("ticket.pdf").toAbsolutePath().toString();
    @FXML
    private VBox layout;
    
    private Label ll;
    @FXML
    private AnchorPane layouts;
    @FXML
    private ImageView imageView;
    @FXML
    private Label matchaR;
    @FXML
    private Label matcheR;
    @FXML
    private Label vs;
    @FXML
    private Label stadeR;
    @FXML
    private Label date_matchR;
    @FXML
    private Label heureR;
    private static Color backgroundColor = Color.WHITE;
    @FXML
    private TextField nbrti;
    @FXML
    private Label matchaR1;
    @FXML
    private Label vs1;
    @FXML
    private Label matcheR1;
    @FXML
    private Label place;
    @FXML
    private Label prix;
    @FXML
    private Label placeR;
    @FXML
    private Label prixR;
    @FXML
    private Label prixT;
    @FXML
    private Label p;
    @FXML
    private Separator se;
    @FXML
    private Label etoile;
    @FXML
    private Label nbt;
    @FXML
    private Label date_au;
    @FXML
    private Button retoura;
    @FXML
    private Label cl;
    @FXML
    private Label nd;
    @FXML
    private Button deco;

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
       
 File imageURL = new File("C:\\Users\\melek\\Documents\\NetBeansProjects\\russie2018\\Images\\ticket.png");
    
        

        
          Image ima=  new Image(imageURL.toURI().toString());
        
        imageView.setImage(ima);
        Button export = new Button("Export");
        
        
        export.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                exportToPDF(layout, PDF_PATH);
            }
        });
        layouts.getChildren().setAll(imageView,vs,matchaR,matcheR,stadeR,date_matchR,heureR,prixR,prixT,p,se,etoile,vs1,nbt,date_au,matchaR1,matcheR1,nd,cl
               
           );
        layout.setStyle("-fx-font-size: 20px;");
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.getChildren().setAll(
            layouts
        );
      
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
                                    matchaR.setText(matcha.valueProperty().getValue());
                                    
                                   
                                    
                 
                
            
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
                                    String placeA=resultas.getString("place");
                                    Integer prixA=resultas.getInt("prix");
                                    System.out.println(datemacth);
                                    //equipe2.getItems().addAll(equipe2A);
                                    stade.setText(stadeA);
                                    date_match.setText(datemacth);
                                    heure.setText(heureA);
                                    nombrea.setText(nbr.toString());
                                    matcheR.setText(matche.valueProperty().getValue());
                                    stadeR.setText(stade.getText());
                                    date_matchR.setText(date_match.getText());
                                    heureR.setText(heure.getText());
                                    place.setText(placeA);
                                    prix.setText(prixA.toString());
                 
                
            
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
       if (matcha.getValue()==null || matche.getValue()==null ||nbrti.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Votre demende d'achat de ticket n'est pas valide");
        alert.setHeaderText("remplir tout les champs");
       
        alert.showAndWait();
        }
        else{
        String req="UPDATE ticket SET nombre=? WHERE(equipe1=? AND equipe2=?)" ; 
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
             Integer a=Integer.parseInt(nombrea.getText());
             Integer b=Integer.parseInt(nbrti.getText());
             Integer c=a-b;
             String d=matcha.valueProperty().getValue();
             String e=matche.valueProperty().getValue();
             
           ste.setInt(1,c) ;
           ste.setString(2,d) ;
           ste.setString(3,e) ;
            
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
        }
        
        
        placeR.setText(place.getText());
        prixR.setText(prix.getText());
        nbt.setText(nbrti.getText());
        Integer t=Integer.parseInt(prixR.getText());
        Integer u=Integer.parseInt(nbrti.getText());
        Integer y=u*t;
        prixT.setText(y.toString());
        nbt.setText(nbrti.getText());
        String format = "dd/MM/yy H:mm:ss"; 

java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat( format ); 
java.util.Date date = new java.util.Date(); 

System.out.println( formater.format( date ).toString() ); 
String dateaa=formater.format( date ).toString();
date_au.setText(dateaa);
String ut=Utilisateur.getNomParticipant();
cl.setText(ut);
Integer ccc=Utilisateur.getIdd();
String cccc=ccc.toString();
nd.setText(cccc);
    exportToPDF(layout, PDF_PATH);     
    Document document = new Document();

    try {
        PdfWriter.getInstance(document,
                new FileOutputStream("ticket.pdf"));
        document.open();

        com.itextpdf.text.Image image1 = com.itextpdf.text.Image.getInstance("output.jpg");
        document.add(image1);

        
            

        document.close();
    } catch(Exception e){
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
        nbrti.setText("");
        matcha.getItems().clear();
        matche.getItems().clear();
        stadeR.setText("");
        date_matchR.setText("");
        heureR.setText("");
        matchaR.setText("");
        matcheR.setText("");
        nbrti.setText("");
        
    }
    }

    private void RetourAction(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource(("MenuFrontStadeFXML.fxml")));
        Parent root = loader.load();
        MenuFrontStadeFXMLController MenuFrontStadeControler = loader.getController();
        Scene scene = retour.getScene();
        scene.setRoot(root);
    }
    private void exportToPDF(Node node, String filePath){
        PDDocument doc = null;
        PDPage page = null;
        PDPageContentStream content = null;
        PDXObjectImage ximage = null;
        WritableImage wi;

        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(backgroundColor);
        int imageWidth = (int) node.getBoundsInLocal().getWidth();
        int imageHeight = (int) node.getBoundsInLocal().getHeight();

        wi = new WritableImage(imageWidth, imageHeight);


        
        try {
            // snapshot the node and convert it to an awt buffered image.
            Image fxImage = node.snapshot(parameters, wi);
            BufferedImage bufferedImage = SwingFXUtils.fromFXImage(fxImage, null);

            // create a pdf containing the snapshot image.
            doc = new PDDocument();
            page = new PDPage();
            doc.addPage(page);
            
           //content = new PDPageContentStream(doc, page);
           
            // alternate path A => try to create a PDJpeg from a jpegInputStream.
          //  ximage = createPDJpegFromJpegStream(doc, bufferedImage);
ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        ImageOutputStream jpegImageOutputStream = ImageIO.createImageOutputStream(jpegOutputStream);
        ImageIO.write(bufferedImage, "png", jpegImageOutputStream);
       

        // output created jpg file for debugging purposes
        // => when you view it is pink due to (I believe) an ImageIO bug.
        // you can see how the resultant image is pink by opening the image file named in system.out in any image viewer.
        // this improper encoding of the jpeg data may be why the subsequent use of it to generate a pdf
        // will generate a an invalid pdf.
        File file = new File("output.jpg");
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(Arrays.copyOf(jpegOutputStream.toByteArray(), jpegOutputStream.size()));
        System.out.println(file.getAbsolutePath());
            // alternate path B => try to create a PDJpeg from directly from a BufferedImage directly.
            // ximage = createPDJpegFromBufferedImage(doc, bufferedImage);

           

            // save the created image to disk.
            doc.save(filePath);

            System.out.println("Exported PDF to: " + filePath);

            // show the generated pdf in a web browser.
            // (if the browser is pdf enabled, this will display the pdf in the web browser).
           
        } catch(IOException ie) {
            ie.printStackTrace();
        } finally {
            try {
                if (content != null) { content.close(); }
                if (doc     != null) { doc.close(); }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // #### THIS METHOD DOES NOT FUNCTION AS EXPECTED
    // alternate path => try to create a PDJpeg from a jpegInputStream.
    // when using a jpeg stream this doesn't work, the created pdf is not well formed and
    // you end up with adobe pdf reader running out of memory trying to read the resultant pdf.
    // Also outputs a weird message that I currently don't understand =>
    //    INFO: About to return NULL from unhandled branch. filter = COSName{DCTDecode}
    private PDXObjectImage createPDJpegFromJpegStream(PDDocument doc, BufferedImage bufferedImage) throws IOException {
        // provide the buffered image data as input to a jpeg input stream.
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        ImageOutputStream jpegImageOutputStream = ImageIO.createImageOutputStream(jpegOutputStream);
        ImageIO.write(bufferedImage, "jpeg", jpegImageOutputStream);
        InputStream jpegInputStream = new ByteArrayInputStream(
                Arrays.copyOf(jpegOutputStream.toByteArray(), jpegOutputStream.size())
        );

        // output created jpg file for debugging purposes
        // => when you view it is pink due to (I believe) an ImageIO bug.
        // you can see how the resultant image is pink by opening the image file named in system.out in any image viewer.
        // this improper encoding of the jpeg data may be why the subsequent use of it to generate a pdf
        // will generate a an invalid pdf.
        File file = new File("output.jpg");
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(Arrays.copyOf(jpegOutputStream.toByteArray(), jpegOutputStream.size()));
        System.out.println(file.getAbsolutePath());

        return new PDJpeg(doc, jpegInputStream);
    }

    // #### THIS METHOD DOES NOT FUNCTION AS EXPECTED
    // alternate path => try to create a PDJpeg from directly from a BufferedImage directly, get the following exception:
    // Exception in thread "JavaFX Application Thread" java.lang.IllegalArgumentException: Raster IntegerInterleavedRaster: width = 552 height = 616 #Bands = 1 xOff = 0 yOff = 0 dataOffset[0] 0 is incompatible with ColorModel ColorModel: #pixelBits = 8 numComponents = 1 color space = java.awt.color.ICC_ColorSpace@125fe1b6 transparency = 1 has alpha = false isAlphaPre = false
    //    at java.awt.image.BufferedImage.<init>(BufferedImage.java:630)
    // Browsing the awt PDJpeg and awt code it appears that the BufferedImage returned by JavaFX uses a
    // SinglePixelPackedSampleModel, but PDJpeg required the buffered image to use a ComponentColorModel
    // and the two are incompatible.  So the bufferedimage needs to be re-encoded to a compatible
    // raster format that utilizes a SampleModel (i.e. a ComponentColorModel) that is acceptable by PDJpeg.
    //
    private PDXObjectImage createPDJpegFromBufferedImage(PDDocument doc, BufferedImage bufferedImage) throws IOException {
        return new PDJpeg(doc, bufferedImage);
    }

    @FXML
    private void ActionRetour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(("MenuFrontStadeFXML.fxml")));
        Parent root = loader.load();
        MenuFrontStadeFXMLController MenuFrontStadeControler = loader.getController();
        Scene scene = retoura.getScene();
        scene.setRoot(root);
    }

    @FXML
    private void decoAction(ActionEvent event) {
    }
    
}
