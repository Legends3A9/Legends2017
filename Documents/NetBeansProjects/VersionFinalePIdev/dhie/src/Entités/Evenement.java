/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entités;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author boussandel
 */
public class Evenement {
    private int idEvenement;
    private String nomEvenement;
    private String destination;
    private String description;
    private Date dateEvenement;
    private String etat ;  //true : disponible ------ false : n'est pas disponible
    private String duree;
    private String type ; 
    private String image;
    private  int nbrPlaces; 
    private int id_user; 
    private double prix;
    
    
    public Evenement () {
        
    }
    
    public Evenement(int id,String n , String desc,Date da,String duree  , String dest , String type,String etat, String image , int nbp ,double prix ,int iduser )
    {
        this.idEvenement=id;
        this.nomEvenement=n ; 
        this.description=desc;
        this.destination=dest ; 
        this.dateEvenement=da ; 
        this.etat=etat ; 
        this.duree=duree ; 
        this.type=type ; 
        this.image=image ; 
        this.nbrPlaces=nbp ; 
        this.prix=prix ; 
        this.id_user=iduser ; 
    }
    


    public Evenement(String nomEvenement, String destination, String description, Date dateEvenement, String etat, String duree, String type, String image, int nbrPlaces, int id_user, double prix) {
        this.nomEvenement = nomEvenement;
        this.destination = destination;
        this.description = description;
        this.dateEvenement = dateEvenement;
        this.etat = etat;
        this.duree = duree;
        this.type = type;
        this.image = image;
        this.nbrPlaces = nbrPlaces;
        this.id_user = id_user;
        this.prix = prix;
    }

    public Evenement(String nomE, String dureeE, Date dateE, String dureeE0, String etatE, String typeE, String destinationE, String imaaageEva, double prixEvee) {

      this.nomEvenement=nomE ; 
      this.duree=dureeE ; 
      this.dateEvenement=dateE ; 
      this.description=dureeE0 ;
      this.etat=etatE ; 
      this.type=typeE ; 
      this.description=destinationE; 
      this.image=imaaageEva; 
      this.prix=prixEvee; 
    }

    public Evenement(String nomEvenement, String destination, String description, Date dateEvenement, String etat, String duree, String type, String image, int nbrPlaces, int id_user) {
        this.nomEvenement = nomEvenement;
        this.destination = destination;
        this.description = description;
        this.dateEvenement = dateEvenement;
        this.etat = etat;
        this.duree = duree;
        this.type = type;
        this.image = image;
        this.nbrPlaces = nbrPlaces;
        this.id_user = id_user;
    }

    public Evenement(String nomEvenement, String destination, String Description, Date dateev, String etatEvenement, String typeEvenement, String dureeEvenement, String imageEvenement, int nbrPlaces, float prixEvenement) {
       this.nomEvenement=nomEvenement; 
       this.destination=destination; 
       this.description=Description; 
       this.dateEvenement=dateev; 
       this.etat=etatEvenement ; 
       this.type=typeEvenement ; 
       this.duree=dureeEvenement; 
       this.image=imageEvenement; 
       this.nbrPlaces=nbrPlaces;
       this.prix=prix; 
       
       
    }

  

 

 
    
   

    
    

   
    public int getIdEvenement() {
        return idEvenement;
    }

    public void setIdEvenement(int idEvenement) {
        this.idEvenement = idEvenement;
    }

    public String getNomEvenement() {
        return nomEvenement;
    }

    public void setNomEvenement(String nomEvenement) {
        this.nomEvenement = nomEvenement;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateEvenement() {
        return dateEvenement;
    }

    public void setDateEvenement(Date dateEvenement) {
        this.dateEvenement = dateEvenement;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getNbrPlaces() {
        return nbrPlaces;
    }

    public void setNbrPlaces(int nbrPlaces) {
        this.nbrPlaces = nbrPlaces;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public float getPrix() {
        return (float) prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Evenement other = (Evenement) obj;
        if (this.idEvenement != other.idEvenement) {
            return false;
        }
        if (this.nbrPlaces != other.nbrPlaces) {
            return false;
        }
        if (Double.doubleToLongBits(this.prix) != Double.doubleToLongBits(other.prix)) {
            return false;
        }
        if (!Objects.equals(this.nomEvenement, other.nomEvenement)) {
            return false;
        }
        if (!Objects.equals(this.destination, other.destination)) {
            return false;
        }
        if (!Objects.equals(this.etat, other.etat)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.dateEvenement, other.dateEvenement)) {
            return false;
        }
        return true;
    }
    
    
    
    

    
    
    
    
    
    
}
