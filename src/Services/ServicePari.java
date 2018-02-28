/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import database.DB;
import Entités.Equipe;
import Entités.Pari;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Dambo
 */
public class ServicePari {
     private  Connection connection;
    private PreparedStatement ps;

    public ServicePari() {
        connection = DB.getInstance().getConnection();
    }
        public void add(Pari p){
    String sql="insert into pari (iduser,nom_equipe) values (?,?)";
     try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, p.getIdUser());
             ps.setString(2, p.getNom_equipe());
   
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        }
     public  List<Pari> getAll() {
        String req = "select * from pari";

        List<Pari> paris = new ArrayList<>();

        try {
            ps = connection.prepareStatement(req);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {

                Pari pari = new Pari(resultSet.getInt(2), resultSet.getString(3));

                paris.add(pari);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return  paris;
    }
       public  List<Pari> getAllparuser(int id) {
       ServicePari pr = new ServicePari();
        List<Pari> paris = new ArrayList<>();
        paris=pr.getAll();
        return paris.stream().filter(e-> e.getIdUser()==id).collect(Collectors.toList());
       
       }
}
    

