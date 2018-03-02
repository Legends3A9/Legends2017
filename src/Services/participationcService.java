/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entités.participationC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import DataBase.DB;

/**
 *
 * @author Alaa
 */
public class participationcService {

    private Connection connection;
    private PreparedStatement ps;
    private static ResultSet r;

    public participationcService() {
        connection = DB.getInstance().getConnection();
    }

    public void ajouterParticipation(participationC p) {

        String req = "insert into participationc(idO,idUser) values (?,?)";

        try {
            ps = connection.prepareStatement(req);

            ps.setInt(1, p.getIdo());
            ps.setInt(2, 1);
            ps.executeUpdate();


        } catch (SQLException ex) {
            Logger.getLogger(participationcService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   

}
