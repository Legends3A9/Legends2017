/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entités;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author boussandel
 */
public class Utilisateur {
    
   private int idUser ; 
   private String nom ; 
   private String prenom ; 
   private String email ; 
   private String password ; 
   private String telephone ; 
   private String nationalite ; 
   private String role ; 
   private String login ;
   private String Photo;
   
    private static Utilisateur instance;
    public static int idd;
    public static String nomParticipant ; 
    public  static String prenomParticipant ; 
    public static String emailEv ; 
    public static String roleUser ; 

    public static String getRoleUser() {
        return roleUser;
    }

    public static void setRoleUser(String roleUser) {
        Utilisateur.roleUser = roleUser;
    }
    

    public static String getEmailEv() {
        return emailEv;
    }

    public static void setEmailEv(String emailEv) {
        Utilisateur.emailEv = emailEv;
    }

    public static String getNomParticipant() {
        return nomParticipant;
    }

    public static void setNomParticipant(String nomParticipant) {
        Utilisateur.nomParticipant = nomParticipant;
    }

    public static String getPrenomParticipant() {
        return prenomParticipant;
    }

    public static void setPrenomParticipant(String prenomParticipant) {
        Utilisateur.prenomParticipant = prenomParticipant;
    }
    

    public static int getIdd() {
        return idd;
    }

    public static void setIdd(int idd) {
        Utilisateur.idd = idd;
    }
    public static int etat_compte;

    public static int getEtat_compte() {
        return etat_compte;
    }

    public static void setEtat_compte(int etat_compte) {
        Utilisateur.etat_compte = etat_compte;
    }
    
   
   public Utilisateur () {
       
   }

    public Utilisateur(int idUser, String nom, String prenom, String email, String password, String telephone, String nationalite, String role, String login, String Photo) {
        this.idUser = idUser;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.telephone = telephone;
        this.nationalite = nationalite;
        this.role = role;
        this.login = login;
        this.Photo = Photo;
    }

    public Utilisateur(String nom, String prenom, String email, String password, String telephone, String nationalite, String role, String login, String Photo) {
       
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.telephone = telephone;
        this.nationalite = nationalite;
        this.role = role;
        this.login = login;
        this.Photo = Photo;
    }

    public Utilisateur(String nom, String prenom, String email, String password, String telephone, String nationalite, String role, String login) {
        this.nom=nom ; 
        this.prenom=prenom ; 
        this.email = email ; 
        this.password=password ; 
        this.telephone=telephone; 
        this.nationalite=nationalite; 
        this.role=role ; 
        this.login=login ;
    }

    public Utilisateur(int aInt, String string, String string0) {
        this.idUser=aInt; 
        this.nom=string ; 
        this.prenom=string0;
    }

    public Utilisateur(Utilisateur user) {
        this.idUser = idUser;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.telephone = telephone;
        this.nationalite = nationalite;
        this.role = role;
        this.login = login;
    }

    public Utilisateur(int idUser, String nom, String prenom, String email) {
        this.idUser = idUser;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

   

   
    

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
    public static Utilisateur getInstance(){
        if (instance == null)
            instance = new Utilisateur();
        
        
    return instance;
    }
    
     
    public static void setInstance(Utilisateur user){
    instance = new Utilisateur(user);
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String Photo) {
        this.Photo = Photo;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Utilisateur other = (Utilisateur) obj;
        if (this.idUser != other.idUser) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.telephone, other.telephone)) {
            return false;
        }
        if (!Objects.equals(this.nationalite, other.nationalite)) {
            return false;
        }
        if (!Objects.equals(this.role, other.role)) {
            return false;
        }
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        if (!Objects.equals(this.Photo, other.Photo)) {
            return false;
        }
        return true;
    }
    
    public  void sedeconnecter(){
    instance=null;
    }
    
    
}