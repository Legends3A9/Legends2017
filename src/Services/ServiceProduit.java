/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataBase.DB;
import Entités.Produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author GlaDio007
 */
public class ServiceProduit {
    static Connection connection;
    private PreparedStatement ps;
    public ServiceProduit() {
    connection =DB.getInstance().getConnection(); 
    }

  
    
       public void Add(Produit p) {
 String req = "INSERT INTO produit (label,type,prix,quantite,image,taille,marque,couleur,description) VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            ps = connection.prepareStatement(req);
            
            ps.setString(1,p.getLabel());
            ps.setString(2, p.getType());
            ps.setFloat(3, p.getPrix());
            ps.setInt(4, p.getQuantite());
            ps.setString(5, p.getImage());
            ps.setString(6, p.getTaille());
            ps.setString(7, p.getMarque());
            ps.setString(8, p.getCouleur());
            ps.setString(9, p.getDescription());
            

            
            ps.executeUpdate();
            System.err.println("oksssssssss");
        } catch (SQLException e) {
            System.err.println("erreur");
            e.printStackTrace();
        }
    }
    
  
       
       
       
    public void Remove(int id)
    {
        String req="DELETE FROM produit where id=?";
         try {
         ps = connection.prepareStatement(req);
         ps.setInt(1,id);
            
     
        ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
   
    public List<Produit> selectProduits() {
        
        List<Produit> produits = new ArrayList<>();
        
    
        Statement state;
        try {
            state = connection.createStatement();
            ResultSet rest= state.executeQuery("SELECT * FROM produit");
            while (rest.next()){
                Produit p= new Produit();
                p.setId(rest.getInt(1));
                p.setLabel(rest.getString(2));
                p.setType(rest.getString(3));
                p.setPrix(rest.getFloat(4));
                p.setQuantite(rest.getInt(5));
                p.setImage(rest.getString(6));
                p.setTaille(rest.getString(7));
                p.setMarque(rest.getString(8));
                p.setCouleur(rest.getString(9));
                p.setDescription(rest.getString(10));
                produits.add(p);
            }
            System.err.println("select avec succes");
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("probleme select");
        }
        return produits;
    }
        public List<Produit> selectProduitsDispo() {
        
        List<Produit> produits = new ArrayList<>();
        
    
        Statement state;
        try {
            state = connection.createStatement();
            ResultSet rest= state.executeQuery("SELECT * FROM produit WHERE quantite>=1");
            while (rest.next()){
                Produit p= new Produit();
                p.setId(rest.getInt(1));
                p.setLabel(rest.getString(2));
                p.setType(rest.getString(3));
                p.setPrix(rest.getFloat(4));
                p.setQuantite(rest.getInt(5));
                p.setImage(rest.getString(6));
                p.setTaille(rest.getString(7));
                p.setMarque(rest.getString(8));
                p.setCouleur(rest.getString(9));
                p.setDescription(rest.getString(10));
                produits.add(p);
            }
            System.err.println("select avec succes");
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("probleme select");
        }
        return produits;
    }
   
    
    public List<Produit> selectProduitParType(String type)
    {
        List<Produit> produits = new ArrayList<>();
        
        String req="SELECT * FROM produit Where tpye=?";
        Statement state;
        try {
            ps = connection.prepareStatement(req);
            ps.setString(1, type);
            ResultSet rest=ps.executeQuery();
            
            while (rest.next()){
                Produit p= new Produit();
                p.setId(rest.getInt(1));
                p.setLabel(rest.getString(2));
                p.setType(rest.getString(3));
                p.setPrix(rest.getFloat(4));
                p.setQuantite(rest.getInt(5));
                p.setImage(rest.getString(6));
                p.setTaille(rest.getString(7));
                p.setMarque(rest.getString(8));
                p.setCouleur(rest.getString(9));
                p.setDescription(rest.getString(10));
                produits.add(p);
            }
            System.err.println("select avec succes");
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("probleme select");
        }
        return produits;
        
    }
    
   
    
    
    public List<Produit> selectProduitParMarque(String marque)
    {
        List<Produit> produits = new ArrayList<>();
        String req="SELECT * FROM produit Where marque=?";
        
        
        try {
            ps = connection.prepareStatement(req);
            ps.setString(1,marque);
            ResultSet rest=ps.executeQuery();
        
            while (rest.next()){
                Produit p= new Produit();
                p.setId(rest.getInt(1));
                p.setLabel(rest.getString(2));
                p.setType(rest.getString(3));
                p.setPrix(rest.getFloat(4));
                p.setQuantite(rest.getInt(5));
                p.setImage(rest.getString(6));
                p.setTaille(rest.getString(7));
                p.setMarque(rest.getString(8));
                p.setCouleur(rest.getString(9));
                p.setDescription(rest.getString(10));
                produits.add(p);
            }
            System.err.println("select avec succes");
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("probleme select");
        }
        return produits;
        
    }

  
    public void modfierProduit(Produit p) {
      
        String req="UPDATE produit SET label=?, type=?, prix=?, quantite=? , image=?,taille=?,marque=?,couleur=?,description=?  WHERE id=? ";
         try {
            ps = connection.prepareStatement(req);
            ps.setString(1,p.getLabel());
            ps.setString(2, p.getType());
            ps.setFloat(3, p.getPrix());
            ps.setInt(4, p.getQuantite());
            ps.setString(5, p.getImage());
            ps.setString(6, p.getTaille());
            ps.setString(7, p.getMarque());
            ps.setString(8, p.getCouleur());
            ps.setString(9, p.getDescription());
            ps.setInt(10, p.getId());
            
            ps.executeUpdate();
        System.err.println("Produit Modifié");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("probleme de modification");
        }
    }
    

    
}
