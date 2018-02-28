/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Stade;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import entities.Ticket;
import util.Data;

/**
 *
 * @author melek
 */
public class TicketService {
    
    static Data ds =Data.getInstance(); 
    public static void insererTicket (Ticket t)
    {
    String req="INSERT INTO ticket (nombre,stade,rencontre,date_match,place,prix) VALUES(?,?,?,?,?,?)" ; 
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
             
            ste.setInt(1,t.getNombre()) ; 
            ste.setString(2,t.getStade()) ; 
            ste.setString(3,t.getMatch());
            ste.setDate(4,t.getDate_match());
            ste.setString(5,t.getPlace());
            ste.setFloat(6,t.getPrix());
            
            
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
        }
    
    }
    public static void updateTicket (Ticket t )
    {
    String req="UPDATE ticket SET nombre=?,stade=?,rencontre=?,date_match=?,place=?,prix=? WHERE ref =?" ; 
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
             
           ste.setInt(1,t.getNombre()) ; 
            ste.setString(2,t.getStade()) ; 
            ste.setString(3,t.getMatch());
            ste.setDate(4,t.getDate_match());
            ste.setString(5,t.getPlace());
            ste.setFloat(6,t.getPrix());
            
            
            
            ste.setInt(7,t.getRef()); 
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
        }
    
    }
    
    public static void DeletTicketByRef (int t)
    {
    String req="DELETE  from ticket where  ref =?" ; 
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
             
            
            ste.setInt(1,t) ;
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
        }
    
      }
    
    
    public static List<Ticket> selectTicket ()
    {
        List<Ticket> list =new ArrayList<>() ; 
    String req ; 
        req = "SELECT *  FROM ticket ";
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
             ResultSet result =ste.executeQuery() ; 
            while (result.next()){
            list.add(new Ticket(
                                    result.getInt("ref"),
                                    result.getInt("nombre"),
                                    result.getString("stade"),
                                    result.getString("match"),
                                    result.getDate("date_match"),
                                    result.getString("place"),
                                    result.getFloat("prix")
                                    
            )); 
            }
            
        } catch (SQLException ex) {
            
        }
    return list ; 
      }
}
