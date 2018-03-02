/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Entités.Stade;
import DB.*;
import Entités.Equipe;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ali hamouda
 */
public class StadeService {
    static DB ds =DB.getInstance(); 
    public  List<Stade> getAll() {
        String req = "select nom_stade from stade";

        List<Stade> stades = new ArrayList<>();

        try {
            PreparedStatement ste = ds.getConnection().prepareStatement(req) ;
            ResultSet result =ste.executeQuery() ;
            while (result.next()) {

                Stade stade = new Stade(result.getString(1));

                stades.add(stade);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return  stades;
    }
    
}
