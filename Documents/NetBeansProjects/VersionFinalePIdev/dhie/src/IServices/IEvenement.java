/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entités.Evenement;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author boussandel
 */
public interface IEvenement {
    public void ajouterEvenement (Evenement e); 
    public void supprimerEvenement(Integer idEvenement);
    public void modifierEvenement(Evenement e,int id) ;
    public Evenement rechercherById(Integer r) ;  
    public ObservableList<Evenement> afficher () ;
    public void decrementation_nbrPlaces(int idEv , int nbrPlace) ;
    public void decrEtChangementDetat(int idev);
public List<Evenement> mesPubs (int id);
   
    
}
