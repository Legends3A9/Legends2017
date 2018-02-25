/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entités.Participation;
import static java.util.Collections.list;
import java.util.List;

/**
 *
 * @author boussandel
 */
public interface IParticipation {
    
    public void ajouterParticipation (Participation p) ;
    public List<Participation> participeDéja (int iduser);
}
