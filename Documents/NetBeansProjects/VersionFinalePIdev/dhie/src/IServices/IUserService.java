/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entités.User;
import java.util.List;

/**
 *
 * @author boussandel
 */
public interface IUserService {
public void add(User user);
public User findById(Integer idUser);
public List<User> getAll();
public void delete(Integer idUser);
public User authentication(String login, String password) ; 
public boolean RechLogin(String text) ;
    
    
}