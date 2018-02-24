/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataBase.DB;
import Entités.Evaluation;
import Entités.Evenement;
import Entités.Participation;
import IServices.IEvaluation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author boussandel
 */
public class EvaluationService implements IEvaluation{
    
     private  Connection connection;
    private PreparedStatement ps;
    private static ResultSet r;
    
    public EvaluationService() {
        connection = DB.getInstance().getConnection();
    }
    
             
        @Override
        public void ajouterEvaluation(Evaluation r) {

       try {
          String requete = "insert into `evaluation` (idUser,idEvenement,nomEvenement,nomParticipant,prenomParticipant,emailParticipant,noteEvenement) values (?,?,?,?,?,?,?)";

            ps = connection.prepareStatement(requete);
           // ps.setInt(1, r.getEvaluationId());
            ps.setInt(1,r.getIdUser());
            ps.setInt(2,r.getIdEvenement());
            ps.setString(3, r.getNomEvenement());
            ps.setString(4, r.getNomParticipant());
            ps.setString(5, r.getPrenomParticipant()) ;
            ps.setString(6, r.getEmailParticipant()) ;
            ps.setDouble(7, r.getNoteEvenement());
            
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
               f.setIdUser(r.getInt("idUser"));
               f.setIdEvenement(r.getInt("idEvenement"));
               f.setEvaluationId(r.getInt("evaluationId"));
               f.setNoteEvenement(r.getDouble("noteEvenement"));
               f.setEmailParticipant(r.getString("emailParticipant"));
               f.setNomParticipant(r.getString("nomParticipant"));
               f.setNomEvenement(r.getString("nomEvenement"));
               f.setPrenomParticipant(r.getString("prenomParticipant"));
               
               
               
               
                     System.out.println(f.toString());
                
               
                listeEvenement.add(f);
            }
            return listeEvenement;
            
            
        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex);
            return null;
        }}
       
       @Override
        public double moyByName(int id)
        {
        List<Evaluation> listeEvenement = new ArrayList<>();
        double sum = 0 ; 
        double moy =0 ;

        
      Services.EvaluationService se=new Services.EvaluationService();
        List<Evaluation> eqs = new ArrayList<>();

      eqs = se.findAll().stream().filter(e->e.getIdEvenement()==id).collect(Collectors.toList());
           
            for(Evaluation e : eqs) {
                sum = sum + e.getNoteEvenement();
                
            }
            System.out.println("recuperation by id"+moy);
          return  moy = sum/eqs.size(); 
        
   }
        
        @Override
    public List<Evaluation> evalueDéja(int id) {
        List<Evaluation> listeEvaluation = new ArrayList<>();

        String requete = "select * from evaluation WHERE idUser = "+id; 
                
        try {
            Statement statement = connection.createStatement();
            r = statement.executeQuery(requete);

            while (r.next()) {
                Evaluation f = new Evaluation();
                
               
               f.setEvaluationId(r.getInt(1));
               f.setEmailParticipant(r.getString(2));
               f.setNomParticipant(r.getString(3));
               f.setNomEvenement(r.getString(4));
               f.setPrenomParticipant(r.getString(5));
               f.setNoteEvenement(r.getDouble(6));
               f.setIdUser(r.getInt(7));
               f.setIdEvenement(r.getInt(8));
               
                listeEvaluation.add(f);
                
                System.out.println("recupération de id evenement "+listeEvaluation.toString());
            }
            return listeEvaluation;
            
            
            
        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex);
            return null;
        }

    
        
        
        
    }
    }

    

