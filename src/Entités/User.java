package Entités;

import java.util.Objects;

/**
 *
 * @author Dambo
 */
public class User {
    
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
   
    private static User instance;
    public static int idd;
    public static String nomParticipant ; 
    public  static String prenomParticipant ; 
    public static String emailEv ; 

    public static String getEmailEv() {
        return emailEv;
    }

    public static void setEmailEv(String emailEv) {
        User.emailEv = emailEv;
    }

    public static String getNomParticipant() {
        return nomParticipant;
    }

    public static void setNomParticipant(String nomParticipant) {
        User.nomParticipant = nomParticipant;
    }

    public static String getPrenomParticipant() {
        return prenomParticipant;
    }

    public static void setPrenomParticipant(String prenomParticipant) {
        User.prenomParticipant = prenomParticipant;
    }
    

    public static int getIdd() {
        return idd;
    }

    public static void setIdd(int idd) {
        User.idd = idd;
    }
    public static int etat_compte;

    public static int getEtat_compte() {
        return etat_compte;
    }

    public static void setEtat_compte(int etat_compte) {
        User.etat_compte = etat_compte;
    }
    
   
   public User () {
       
   }

    public User(String nom, String prenom, String email, String password, String telephone, String nationalite, String role, String login, String Photo) {
       
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

    public User(String nom, String prenom, String email, String password, String telephone, String nationalite, String role, String login) {
        this.nom=nom ; 
        this.prenom=prenom ; 
        this.email = email ; 
        this.password=password ; 
        this.telephone=telephone; 
        this.nationalite=nationalite; 
        this.role=role ; 
        this.login=login ;
    }

    public User(int aInt, String string, String string0) {
        this.idUser=aInt; 
        this.nom=string ; 
        this.prenom=string0;
    }

    public User(User user) {
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

    public User(int idUser, String nom, String prenom, String email) {
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
    
    public static User getInstance(){
        if (instance == null)
            instance = new User();
        
        
    return instance;
    }
    
     
    public static void setInstance(User user){
    instance = new User(user);
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
        final User other = (User) obj;
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