/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataBase.DB;
import Entités.Evaluation;
import Entités.Evenement;
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
 * @author boussandel
 */
public class EvaluationService {
    
     private  Connection connection;
    private PreparedStatement ps;
    private static ResultSet r;
    
    public EvaluationService() {
        connection = DB.getInstance().getConnection();
    }
    
             

        public void ajouter_Evaluation(Evaluation r) {

       try {
          String requete = "insert into `evaluation` (nomEvenement,nomParticipant,prenomParticipant,emailParticipant,noteEvenement) values (?,?,?,?,?)";

            ps = connection.prepareStatement(requete);
           // ps.setInt(1, r.getEvaluationId());
            ps.setString(1, r.getNomEvenement());
            ps.setString(2, r.getNomParticipant());
            ps.setString(3, r.getPrenomParticipant()) ;
            ps.setString(4, r.getEmailParticipant()) ;
            ps.setDouble(5, r.getNoteEvenement());
            
            System.out.println("Evaluation ajouté");
             
               
           ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Services.EvaluationService.class.getName()).log(Level.SEVERE, null, ex);

        }
        }
        
       public List<Evaluation> findAll()
        {
        List<Evaluation> listeEvenement = new ArrayList<>();

        String requete = "select * from evaluation ";
        try {
            Statement statement = connection.createStatement();
            r = statement.executeQuery(requete);

            while (r.next()) {
               Evaluation f = new Evaluation();
                
               f.setEvaluationId(r.getInt("evaluationId"));
               f.setNoteEvenement(r.getDouble("noteEvenement"));
               f.setEmailParticipant(r.getString("emailParticipant"));
               f.setNomParticipant(r.getString("nomParticipant"));
               f.setNomEvenement(r.getString("nomEvenement"));
               f.setPrenomParticipant(r.getString("prenomParticipant"));
               
               
               
               
                     System.out.println("recuperation liste");
                
               
                listeEvenement.add(f);
            }
            return listeEvenement;
            
            
        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex);
            return null;
        }
        
        
        
    }

    
}
