/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataBase.DB;
import Entités.Panier;
import Entités.ProduitPanier;
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
public class ServiceProduitPanier {
    static Connection connection;
    private PreparedStatement ps;
    public ServiceProduitPanier() {
    connection =DB.getInstance().getConnection(); 
    }

  
    
       public void Add(ProduitPanier p) {
 String req = "INSERT INTO produitpanier (label,type,prix,quantite,image,taille,marque,couleur,description,idpanier) VALUES (?,?,?,?,?,?,?,?,?,?)";
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
            ps.setInt(10, selectIdPanier());
            

            
            ps.executeUpdate();
            System.err.println("oksssssssss");
        } catch (SQLException e) {
            System.err.println("erreur");
            e.printStackTrace();
        }
    }
    
         public int selectIdPanier() {
        
        
        
    int var = 0;
        Statement state;
        try {
            
            
            state = connection.createStatement();
            ResultSet rest= state.executeQuery("SELECT idpanier FROM panier INNER JOIN user ON user.iduser="+1);
            while(rest.next())
            {
                Panier p = new Panier();
                p.setIdpanier(rest.getInt(1));
                var=p.getIdpanier();
            }
           
            System.err.println("select avec succes");
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduitPanier.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("probleme select");
        }
        
        return var;
    }
  
       
       
       
    public void Remove(int s)
    {
        String req="DELETE FROM produitpanier where id=?";
         try {
         ps = connection.prepareStatement(req);
         ps.setInt(1,s);
            
     
        ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
   
    public List<ProduitPanier> selectAll() {
        
        List<ProduitPanier> listpanier = new ArrayList<>();
        
    
        Statement state;
        try {
            state = connection.createStatement();
            ResultSet rest= state.executeQuery("SELECT * FROM produitpanier WHERE idpanier="+selectIdPanier());
            while (rest.next()){
                ProduitPanier p= new ProduitPanier();
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
                listpanier.add(p);
            }
            System.err.println("select avec succes");
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduitPanier.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("probleme select");
        }
        return listpanier;
    }
    
    public void deleteAll()
    {
        
        
        String req="DELETE FROM produitpanier WHERE idpanier=?";
        try{
            ps=connection.prepareStatement(req);
            ps.setInt(1,selectIdPanier() );
            ps.executeUpdate();
            
        }
        catch(SQLException ex)
        {
           Logger.getLogger(ServiceProduitPanier.class.getName()).log(Level.SEVERE, null, ex); 
        }
    }
    
    
}
