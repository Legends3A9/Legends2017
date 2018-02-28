/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import database.DB;
import Entités.Equipe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
public class ServiceEquipe {
     private  Connection connection;
    private PreparedStatement ps;

    public ServiceEquipe() {
        connection = DB.getInstance().getConnection();
    }
    public void add(Equipe e){
    String sql="insert into equipe (nom_equipe,nom_poule,continent,nbr_joueur,nbr_participation,nbr_coupe,taux_victoire) values (?,?,?,?,?,?,?)";
     try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, e.getNom_equipe());
             ps.setString(2, e.getNom_poule());
             ps.setString(3, e.getContinent());
            ps.setInt(4, e.getNbr_joueur());
            ps.setInt(6, e.getNbr_coupe());
            ps.setInt(5,e.getNbr_participation());
            ps.setDouble(7,(((double)e.getNbr_coupe())/e.getNbr_participation())*100);
            
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
     
}
    public void delete(int id_equipe) {
        String req = "delete from equipe where id =?";
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, id_equipe);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        public void Update(Equipe E) {
String req = "update `equipe` set `nom_equipe`=? ,`nom_poule`=?,`continent`=? ,`nbr_joueur`=?,`nbr_participation`=? ,`nbr_coupe`=?  where id=?";
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(7, E.getId());
            ps.setString(1, E.getNom_equipe());
            ps.setString(2, E.getNom_poule());

             ps.setString(3, E.getContinent());

            ps.setInt(4, E.getNbr_joueur());
            ps.setInt(6, E.getNbr_coupe());
            ps.setInt(5, E.getNbr_participation());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }     }
 public  List<Equipe> getAll() {
        String req = "select * from equipe";

        List<Equipe> equipes = new ArrayList<>();

        try {
            ps = connection.prepareStatement(req);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {

                Equipe equipe = new Equipe(resultSet.getInt(1), resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getInt(5),resultSet.getInt(6),resultSet.getInt(7),resultSet.getDouble(8));

                equipes.add(equipe);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return  equipes;
    }
     public List<Equipe> FindByContinent(String continent) {
/*String req = "select * from equipe where continent = ?";

  List<Equipe> equipes = new ArrayList<>();

        try {
            ps = connection.prepareStatement(req);

            ps.setString(1, continent);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                
                Equipe equipe = new Equipe(resultSet.getInt(1), resultSet.getString(2),resultSet.getString(3),resultSet.getInt(4),resultSet.getInt(5),resultSet.getInt(6));
            equipes.add(equipe);
               
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return equipes;*/
ServiceEquipe se=new ServiceEquipe();
  List<Equipe> equipes = new ArrayList<>();

  return equipes =se.getAll().stream().filter(e->e.getContinent().equals(continent)).collect(Collectors.toList());

    }
   public  Map<String, List<Equipe>> getByContinent(){
ServiceEquipe se=new ServiceEquipe();
 return se.getAll().stream().collect(Collectors.groupingBy(Equipe::getContinent));
 
   
   }
     public int nbrequipePoule(String poule) throws SQLException{
      ServiceEquipe se=new ServiceEquipe();
      int i =(int) se.getAll().stream().filter(e->e.getNom_poule().equals(poule)).count();
       return i;
   }
       public List<Equipe> equipeParPoule(String poule) throws SQLException{
      ServiceEquipe se=new ServiceEquipe();
        List<Equipe> eqs = new ArrayList<>();

      eqs = se.getAll().stream().filter(e->e.getNom_poule().equals(poule)).collect(Collectors.toList());
       return eqs;
   }
 public int nbrequipeContinant(String Continent) throws SQLException{
      ServiceEquipe se=new ServiceEquipe();
      int i =(int) se.getAll().stream().filter(e->e.getContinent().equals(Continent)).count();
       return i;
   }
}
