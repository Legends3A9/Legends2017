/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DB.DB;
import Entités.Match;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author ali hamouda
 */
public class MatchService {
     static DB ds =DB.getInstance(); 
    
    
    public void insererMatch (Match m)
    {
    String req="INSERT INTO matchs (equipe1,equipe2,date_match,stade,heure,type_phase,type_match) VALUES(?,?,?,?,?,?,?)" ; 
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
             
            ste.setString(1,m.getEquipe1()) ; 
            ste.setString(2,m.getEquipe2()) ; 
            ste.setDate(3,m.getDate_match());
            ste.setTime(5, m.getHeure());
            ste.setString(4,m.getStade());
            ste.setString(6,m.getType_phase());
            ste.setString(7,m.getType_match());
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
        }
    
    }
    
     
    public void updateMatch (Match m )
    {
    String req="UPDATE matchs SET equipe1=?,equipe2=?,date_match=?,heure=?,stade=?,type_phase=?,type_match=? WHERE id=?" ; 
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
             
            ste.setString(1,m.getEquipe1()) ; 
            ste.setString(2,m.getEquipe2()) ; 
            ste.setDate(3,m.getDate_match());
            ste.setTime(4, m.getHeure());
            ste.setString(6,m.getType_phase());
            ste.setString(7,m.getType_match());
            ste.setString(5,m.getStade());
            ste.setInt(8,m.getId()) ;
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
        }
    
    }
    
     public  void DeleteMatch (Match m)
    {
    
        try { 
            String req="DELETE  from matchs where  stade =?" ; 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
             
            
            ste.setString(1,m.getStade()) ;
            ste.executeUpdate() ; 
            
            
        } catch (SQLException ex) {
        }
    
      }
     public  void DeletAebitreByID (int m)
    {
    String req="DELETE  from matchs where  id =?" ; 
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
             
            
            ste.setInt(1,m) ;
            ste.executeUpdate() ; 
            
        } catch (SQLException ex) {
        }
    
      }
    public List<Match> selectMatch()
    {
        List<Match> list =new ArrayList<>() ; 
        String req ; 
        req = "SELECT *  FROM matchs ";
        try { 
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
             ResultSet result =ste.executeQuery() ; 
            while (result.next()){
            list.add(new Match(
                                    result.getInt("id"),
                                    result.getString("equipe1"),
                                    result.getString("equipe2"),
                                    result.getDate("date_match"),
                                    result.getTime("heure"),
                                    result.getString("stade"),
                                    result.getString("type_phase"),
                                    result.getString("type_match")
                                    
                                    
                                    
            )); 
            }
            
        } catch (SQLException ex) {
            
        }
    return list ; 
      }
    
    public List<Match> equipeParTypeMatch(String match) throws SQLException{
      MatchService se=new MatchService();
        List<Match> eqs = new ArrayList<>();

      eqs = se.selectMatch().stream().filter(e->e.getType_match().equals(match)).collect(Collectors.toList());
       return eqs;
   }
    
}
