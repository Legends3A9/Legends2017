/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataBase.DB;
import Entités.Evenement;
import Entités.Participation;
import IServices.IParticipation;
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
public class ParticipationService implements IParticipation{
    
     private  Connection connection;
    private PreparedStatement ps;
    private static ResultSet r;
    
    public ParticipationService() {
        connection = DB.getInstance().getConnection();
    }

    @Override
    public void ajouterParticipation(Participation p) {

    String req = "insert into participation(idEvenement,idUser) values (?,?)";
 
        try {
            ps = connection.prepareStatement(req);
              
               
               ps.setInt(1,p.getIdEvenement());
               ps.setInt(2,p.getIdUser());
                            ps.executeUpdate();

               System.out.println("participaation ajouté");
            

    }    catch (SQLException ex) {
             Logger.getLogger(ParticipationService.class.getName()).log(Level.SEVERE, null, ex);
         }
}

    @Override
    public List<Participation> participeDéja(int id) {
        List<Participation> listeParticipation = new ArrayList<>();

        String requete = "select * from participation WHERE idUser = "+id; 
                
        try {
            Statement statement = connection.createStatement();
            r = statement.executeQuery(requete);

            while (r.next()) {
                Participation f = new Participation();
                
               
               f.setIdParticipation(r.getInt(1));
               f.setIdUser(r.getInt(2));
               f.setIdEvenement(r.getInt(3));
                listeParticipation.add(f);
                
                System.out.println("recupération de id evenement "+listeParticipation.toString());
            }
            return listeParticipation;
            
            
            
        } catch (SQLException ex) {
            System.out.println("SQL Error: " + ex);
            return null;
        }
        
        
    }

   




}
