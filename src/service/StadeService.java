/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import entities.Stade;
import util.DB;

/**
 *
 * @author melek
 */
public class StadeService {
    
    static DB ds =DB.getInstance(); 
    
    
    public static void insererStade (Stade s)
    {
    String req="INSERT INTO stade (nom_stade,region,adresse,date_construction,surface,capacite,image) VALUES(?,?,?,?,?,?,?)" ; 
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
             
            ste.setString(1,s.getNom_stade()) ; 
            ste.setString(2,s.getRegion()) ; 
            ste.setString(3,s.getAdresse());
            ste.setDate(4,s.getDate_construction());
            ste.setString(5,s.getSurface());
            ste.setInt(6,s.getCapacite());
            ste.setString(7,s.getImage());
            
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
        }
    
    }
    
    public static void updateStade (Stade s )
    {
    String req="UPDATE stade SET nom_stade=?,region=?,adresse=?,date_construction=?,surface=?,capacite=?,image=? WHERE ref =?" ; 
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
             
           ste.setString(1,s.getNom_stade()) ; 
            ste.setString(2,s.getRegion()) ; 
            ste.setString(3,s.getAdresse());
            ste.setDate(4,s.getDate_construction());
            ste.setString(5,s.getSurface());
            ste.setInt(6,s.getCapacite());
            ste.setString(7,s.getImage());
            
            
            ste.setInt(8,s.getRef()); 
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
        }
    
    }
    
    
    public static void DeletStadeByNom (String s)
    {
    String req="DELETE  from Stade where  nom_stade =?" ; 
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
             
            
            ste.setString(1,s) ;
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
        }
    
      }
    
    
     public static List<Stade> selectStade ()
    {
        List<Stade> list =new ArrayList<>() ; 
    String req ; 
        req = "SELECT *  FROM stade ";
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
             ResultSet result =ste.executeQuery() ; 
            while (result.next()){
            list.add(new Stade(
                                    result.getInt("ref"),
                                    result.getString("nom_stade"),
                                    result.getString("region"),
                                    result.getString("adresse"),
                                    result.getDate("date_construction"),
                                    result.getString("surface"),
                                    result.getInt("capacite"),
                                    result.getString("image")
            )); 
            }
            
        } catch (SQLException ex) {
            
        }
    return list ; 
      }
     
    
    
    
    
}
    
    

