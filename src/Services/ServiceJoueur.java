/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import database.DB;
import Entités.Equipe;
import Entités.Joueur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author Dambo
 */
public class ServiceJoueur {

    private final Connection connection;
    private PreparedStatement ps;

    public ServiceJoueur() {
        connection = DB.getInstance().getConnection();
    }

    public void add(Joueur e) {
        String sql = "insert into joueur (nom_equipe,nom_joueur,post) values (?,?,?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, e.getNom_equipe());
            ps.setString(2, e.getNom_joueur());
            ps.setString(3, e.getPost());

            ps.executeUpdate();
        } catch (SQLException ex) {
        }

    }

    public void delete(String nom_equipe) {
        String req = "delete from joueur where nom_equipe =?";
        try {
            ps = connection.prepareStatement(req);
            ps.setString(1, nom_equipe);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Joueur> getAll() {
        String req = "select * from joueur";

        List<Joueur> joueurs = new ArrayList<>();

        try {
            ps = connection.prepareStatement(req);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {

                Joueur joueur = new Joueur(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));

                joueurs.add(joueur);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return joueurs;
    }

    public List<Joueur> getParEquipe(String nom_equipe) {
//ServiceJoueur sj=new ServiceJoueur();
      //  List<Joueur> joueurs = new ArrayList<>();
        
      //  joueurs = sj.getAll().stream().filter(e -> e.getNom_equipe().equals(nom_equipe)).collect(Collectors.toList());
       // return joueurs;
          String req = "select * from joueur where nom_equipe=?";

        List<Joueur> joueurs = new ArrayList<>();

        try {
              ps = connection.prepareStatement(req);
            ps.setString(1, nom_equipe);
           
           
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {

                Joueur joueur = new Joueur(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));

                joueurs.add(joueur);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return joueurs;
    }
}
