package Services;


import DataBase.DB;
import Entités.Evenement;
import Entités.Utilisateur;
import IServices.IEvenement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EvenementServices implements IEvenement {
    private Connection connection;
    private PreparedStatement ps;
    private static ResultSet r;
  
    public EvenementServices() {
        connection = DB.getInstance().getConnection();
    }

    @Override
    public void ajouterEvenement(Evenement ev) {
 String req = "insert into evenement (nomEvenement,description,dateEvenement,duree,destination,type,etat,image,nombrePlace,prix,idUser) values (?,?,?,?,?,?,?,?,?,?,?)";
 
        try {
            ps = connection.prepareStatement(req);
              
               
               ps.setString(1,ev.getNomEvenement());
               ps.setString(2,ev.getDescription());
               ps.setDate(3,  ev.getDateEvenement());
               ps.setString(4,ev.getDuree());
               ps.setString(5,ev.getDestination());
               
               ps.setString(6,ev.getType());
               
               ps.setString(7,ev.getEtat());
               ps.setString(8,ev.getImage());
               ps.setInt(9,ev.getNbrPlaces());
               ps.setDouble(10,ev.getPrix());
               ps.setInt(11,Utilisateur.getIdd());
               System.out.println("evenement ajouté");
            
            ps.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
   
  

    @Override
    public void supprimerEvenement(Integer idEvenement) {
       
        String req = "delete from evenement where idEvenement =?";
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1,idEvenement);
            ps.executeUpdate();
            System.out.println("evenement supprimé");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    @Override
    public void modifierEvenement(Evenement ev,int id) {
     try {
            String req;
            req = "UPDATE evenement SET nomEvenement=?,description=?,dateEvenement=?,duree=?,destination=?,type=?,etat=?,image=?,nombrePlace=?,prix=?,idUser=? WHERE idEvenement=?";
      ps = connection.prepareStatement(req);
               
               ps.setString(1,ev.getNomEvenement());
               ps.setString(2,ev.getDescription());
               ps.setDate(3,  ev.getDateEvenement());
               ps.setString(4,ev.getDuree());
               ps.setString(5,ev.getDestination());
               
               ps.setString(6,ev.getType());
               
               ps.setString(7,ev.getEtat());
               ps.setString(8,ev.getImage());
               ps.setInt(9,ev.getNbrPlaces());
               ps.setDouble(10,ev.getPrix());
               ps.setInt(11,ev.getId_user());
               ps.setInt(12, id);
               System.out.println("evenement modifier");
     
            ps.executeUpdate();
           
        } catch (SQLException ex) {
            System.out.println(ps);
  Logger.getLogger(Evenement.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
  
    
 

    @Override
    public Evenement rechercherById(Integer r) {
        String req = "select * from evenement where idEvenement = ?";
        Evenement evenement = null ; 
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, r);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                evenement = new Evenement(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getDate(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9),resultSet.getInt(10),resultSet.getFloat(11),resultSet.getInt(12));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return evenement ;
        
    }
    
    public List<Evenement> findAll()
        {
        List<Evenement> listeEvenement = new ArrayList<>();

        String requete = "select idEvenement,"
                + "nomEvenement,"
                +"description,"
                +"dateEvenement,"
                + "duree,"
                +"destination,"
                + "type,"
                
                
                +"etat,"
                + "image,"
                +"nombrePlace,"
                +"prix,"
                +"idUser from evenement";
        try {
            Statement statement = connection.createStatement();
            r = statement.executeQuery(requete);

            while (r.next()) {
                Evenement f = new Evenement();
                
               f.setIdEvenement(r.getInt(1));
               f.setNomEvenement(r.getString(2));
               f.setDescription(r.getString(3));
               f.setDateEvenement(r.getDate(4));
               f.setDuree(r.getString(5));
               f.setDestination(r.getString(6));
               f.setType(r.getString(7));
               
               f.setEtat(r.getString(8));
               f.setImage(r.getString(9));
               
               f.setNbrPlaces(r.getInt(10));
               f.setPrix(r.getFloat(11));
               f.setId_user(r.getInt(12));
               
                     
                
               
                listeEvenement.add(f);
            }
            return listeEvenement;
            
            
        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex);
            return null;
        }
        
    }
    
    public Evenement recher(String nom) {
        boolean exist = false;
       Evenement u = null;
      Evenement uc = null;
        String req = "SELECT * FROM evenement WHERE nomEvenement=?";
        try {
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(req);
            ps.setString(1, nom);
       
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                u = new Evenement(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getDate(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9),resultSet.getInt(10),resultSet.getFloat(11),resultSet.getInt(12));
            }
            exist = true;
            System.out.println("Evenement trouvé");
            
        } catch (SQLException ex) {
            Logger.getLogger(EvenementServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

    @Override
    public ObservableList<Evenement> afficher() {
      

            List<Evenement> listeEvenement = new ArrayList<>();

        String requete = "select * from evenement";
          try {
            Statement statement = connection.createStatement();
            r = statement.executeQuery(requete);

            while (r.next()) {
                Evenement f = new Evenement();
                
               f.setIdEvenement(r.getInt(1));
               f.setNomEvenement(r.getString(2));
               f.setDescription(r.getString(3));
               f.setDateEvenement(r.getDate(4));
               f.setDuree(r.getString(5));
               f.setDestination(r.getString(6));
               f.setType(r.getString(7));
               
               f.setEtat(r.getString(8));
               f.setImage(r.getString(9));
               
               f.setNbrPlaces(r.getInt(10));
               f.setPrix(r.getFloat(11));
               f.setId_user(r.getInt(12));
               
                     
                
               
                listeEvenement.add(f);
                System.out.println("Recupération ok");

                
                
                
            }
            ObservableList<Evenement> log = FXCollections.observableArrayList(listeEvenement);
            return log;
        } catch (SQLException ex) {
            Logger.getLogger(Services.EvenementServices.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
     public ObservableList<Evenement> AfficherMesAnnonces() {
        try {

            List<Evenement> evenements = new ArrayList<>();
            int id = Utilisateur.getInstance().getIdUser();
            String requete = "Select * from Evenement where idEvenement="+id;
            Statement statement = connection.createStatement();
            r = statement.executeQuery(requete);
            
            while (r.next()) {
                Evenement f = new Evenement();
                f.setIdEvenement(r.getInt("idEvenement"));
               f.setNomEvenement(r.getString("nomEvenement"));
               f.setDescription(r.getString("description"));
               f.setDateEvenement(r.getDate("dateEvenement"));
               f.setDuree(r.getString("duree"));
               f.setDestination(r.getString("destination"));
               f.setType(r.getString("typeEvenement"));
               
               f.setEtat(r.getString("etat"));
               f.setImage(r.getString("image"));
               
               f.setNbrPlaces(r.getInt("nombrePlace"));
               f.setPrix(r.getFloat("prix"));
               f.setId_user(r.getInt("idUser"));
                
            }
            ObservableList<Evenement> log = FXCollections.observableArrayList(evenements);
            return log;
        } catch (SQLException ex) {
            Logger.getLogger(Services.EvenementServices.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public void decrementation_nbrPlaces(int idev , int nbrPlace) {
        try{
        String req = "UPDATE `evenement` SET `nombrePlace`=(?) WHERE `idEvenement`=(?)";
        PreparedStatement pstmt = connection.prepareStatement(req);
        pstmt.setInt(1,nbrPlace);
        pstmt.setInt(2,idev);

         pstmt.executeUpdate();
            System.out.println("decrementation nbr place effectué ");
        } catch (SQLException ex) {
            Logger.getLogger(EvenementServices.class.getName()).log(Level.SEVERE, null, ex);
        }
     }

    @Override
    public void decrEtChangementDetat(int idev) {
         try{
        String req = "UPDATE `evenement` SET `nombrePlace`=(?) ,`etat`=(?) WHERE `idEvenement`=(?)";
        PreparedStatement pstmt = connection.prepareStatement(req);
        pstmt.setInt(1,0);
        pstmt.setString(2,"réservé");
        pstmt.setInt(3,idev);
        

         pstmt.executeUpdate();
            System.out.println("decrementation nbr place et etat effectué ");
        } catch (SQLException ex) {
            Logger.getLogger(EvenementServices.class.getName()).log(Level.SEVERE, null, ex);
        }
     }

        
    }
    
    
    
     
    
        

  

  
    
    
    
    
    

    
    
    
    
