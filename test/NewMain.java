
import Entités.Equipe;
import Entités.Joueur;
import java.sql.SQLException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import Services.ServiceEquipe;
import Services.ServiceJoueur;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dambo
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
     /*   ServiceEquipe se= new ServiceEquipe();
        Equipe E=new Equipe(19, "aaa", "A", "Afrique", 0, 0, 0, 0);
        se.Update(E);
     int i= se.nbrequipePoule("A");
        System.out.println(i);
List<Equipe> equipes = new ArrayList<>();
equipes=se.equipeParPoule("A");
        System.out.println(equipes);*/
        ServiceJoueur j=new ServiceJoueur();
        List<Joueur> equipes = new ArrayList<>();

        equipes=j.getParEquipe("Bresil");
        System.out.println(equipes);
        
    }
    
}
