/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entités.Utilisateur;
import java.util.List;

/**
 *
 * @author boussandel
 */
public interface IUserService {
public void add(Utilisateur user);
public Utilisateur findById(Integer idUser);
public List<Utilisateur> getAll();
public void delete(Integer idUser);
public Utilisateur authentication(String login, String password); 
public boolean RechLogin(String text) ;
    
    
}