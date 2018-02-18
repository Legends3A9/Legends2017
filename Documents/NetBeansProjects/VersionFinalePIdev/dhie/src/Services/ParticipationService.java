/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DataBase.DB;
import Entités.Participation;
import IServices.IParticipation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    
}}
