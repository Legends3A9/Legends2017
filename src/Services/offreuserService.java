/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Entités.offreuser;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import DataBase.DB;

/**
 *
 * @author Alaa
 */
public class offreuserService {
    static DB ds = DB.getInstance();

    public static void insereroffre(offreuser o) {
        String req = "INSERT INTO offreuser (type,prix,adresse,nb_place_dispo,description,tel,image,etat,iduser) VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ste = ds.getConnection().prepareStatement(req);

            ste.setString(1, o.getType());
            ste.setFloat(2, o.getPrix());
            ste.setString(3, o.getAdresse());
            ste.setInt(4, o.getNb_place_dispo());
            ste.setString(5, o.getDescription());
            ste.setInt(6, o.getTel());
            ste.setString(7, o.getImage());
            ste.setString(8, o.getEtat());
            ste.setInt(9,1);
            ste.executeUpdate();

        } catch (SQLException ex) {
        }
    }

    public static void updateoffre(offreuser o) {
        String req = "UPDATE offreuser SET type=?,prix=?,adresse=?,nb_place_dispo=?,description=?,tel=?, image=?, etat=? WHERE id =?";
        try {
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ste.setString(1, o.getType());
            ste.setFloat(2, o.getPrix());
            ste.setString(3, o.getAdresse());
            ste.setInt(4, o.getNb_place_dispo());
            ste.setString(5, o.getDescription());
            ste.setInt(6, o.getTel());
            ste.setString(7, o.getImage());
            ste.setString(8, o.getEtat());
            ste.setInt(9, o.getId());
            ste.executeUpdate();
        } catch (SQLException ex) {
        }
    }

    public static List<offreuser> selectoffre() {
        List<offreuser> list = new ArrayList<>();
        String req;
        req = "SELECT *  FROM offreuser WHERE iduser="+1;
        try {
            PreparedStatement ste = ds.getConnection().prepareStatement(req);
            ResultSet result = ste.executeQuery();
            while (result.next()) {
                list.add(new offreuser(
                        result.getInt("id"),
                        result.getString("type"),
                        result.getFloat("prix"),
                        result.getString("adresse"),
                        result.getInt("nb_place_dispo"),
                        result.getString("description"),
                        result.getInt("tel"),
                        result.getString("image"),
                        result.getString("etat")
                ));
            }
        } catch (SQLException ex) {
        }
        return list;
    }

    public static void DeletoffreByID(int o) {
        String req = "DELETE  from offreuser where  id =?";
        try {
            PreparedStatement ste = ds.getConnection().prepareStatement(req);

            ste.setInt(1, o);
            ste.executeUpdate();

        } catch (SQLException ex) {
        }
    }
    public void decrementation_nbrPlaces(int ido , int nbrPlace) {
        try{
        String req = "UPDATE offreuser SET nb_place_dispo=(?) WHERE id=(?)";
        PreparedStatement pstmt = ds.getConnection().prepareStatement(req);
        pstmt.setInt(1,nbrPlace);
        pstmt.setInt(2,ido);

         pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(offreuserService.class.getName()).log(Level.SEVERE, null, ex);
        }
     }

    public void decrEtChangementDetat(int ido) {
         try{
        String req = "UPDATE offreuser SET nb_place_dispo=(?), etat=(?)  WHERE id=(?)";
        PreparedStatement pstmt = ds.getConnection().prepareStatement(req);
        pstmt.setInt(1,0);
        pstmt.setString(2,"complet");
        pstmt.setInt(3,ido);
        

         pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(offreuserService.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
}
