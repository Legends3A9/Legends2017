/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DB.DB;
import Entites.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author ali hamouda
 */
public class PronosticService {
     static DB ds =DB.getInstance(); 
    
    
    public void insererPronostic (Pronostic p )
    {
    String req="INSERT INTO pronostic (idUser,idMatch,pro) VALUES(?,?,?)" ; 
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
             
            ste.setInt(1,p.getIdUser()) ; 
            ste.setInt(2,p.getIdMatch()) ; 
            ste.setString(3,p.getPro());
           
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
        }
    
    }
    
     
    public void updatePronostic (Pronostic p )
    {
    String req="UPDATE pronostic SET pro=? WHERE idUser=?" ; 
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
             
            ste.setString(1,p.getPro()) ; 
            ste.setInt(2,p.getIdUser()) ; 
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
        }
    
    }
    
    
   
    public List<Pronostic> selectPronostic()
    {
        List<Pronostic> list =new ArrayList<>() ; 
    String req ; 
        req = "SELECT *  FROM pronostic ";
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
            ResultSet result =ste.executeQuery() ; 
            while (result.next()){
            list.add(new Pronostic(
                                    result.getInt("idUser"),
                                    result.getInt("idMatch"),
                                    result.getString("pro")
                                    
                                    
                                    
                                    
            )); 
            }
            
        } catch (SQLException ex) {
            
        }
    return list ; 
      }
    
    
    public List<Pronostic> somme(int i, String p) throws SQLException{
      PronosticService pr=new PronosticService();
      List<Pronostic> nb= new ArrayList<Pronostic>();

      nb = pr.selectPronostic().stream().filter((e)->e.getPro().equals(p)&& e.getIdMatch()==i).collect(Collectors.toList());
       return nb;
   }
    public List<Pronostic> somme1(int i) throws SQLException{
      PronosticService pr=new PronosticService();
      List<Pronostic> nb= new ArrayList<Pronostic>();

      nb = pr.selectPronostic().stream().filter(e-> e.getIdMatch()==i).collect(Collectors.toList());
       return nb;
   }
    
     public int verif (int iu,  int im){
      PronosticService pr=new PronosticService();
      int nb = pr.selectPronostic().stream().filter((e)->e.getIdUser()==iu&& e.getIdMatch()==im).collect(Collectors.toList()).size();
       return nb;
   }
    
    
   
    
    
    
    
}
