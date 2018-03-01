/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
import java.sql.Date;

/**
 *
 * @author melek
 */
public class Stade {
    
    private int ref;
    private String nom_stade;
    private String region;
    private String adresse;
    private Date date_construction;
    private String surface;
    private int capacite;
    private String image;

    public Stade(int ref, String nom_stade, String region, String adresse, Date date_construction, String surface, int capacite, String image) {
        this.ref = ref;
        this.nom_stade = nom_stade;
        this.region = region;
        this.adresse = adresse;
        this.date_construction = date_construction;
        this.surface = surface;
        this.capacite = capacite;
        this.image = image;
    }
    public Stade(String nom_stade, String region, String adresse, Date date_construction, String surface, int capacite, String image) {
        this.nom_stade = nom_stade;
        this.region = region;
        this.adresse = adresse;
        this.date_construction = date_construction;
        this.surface = surface;
        this.capacite = capacite;
        this.image = image;
    }

    
    

    public int getRef() {
        return ref;
    }

    public String getNom_stade() {
        return nom_stade;
    }

    public String getRegion() {
        return region;
    }

    public String getAdresse() {
        return adresse;
    }

    
    public Date getDate_construction() {
        return date_construction;
    }

    public String getSurface() {
        return surface;
    }

    public int getCapacite() {
        return capacite;
    }

    public String getImage() {
        return image;
    }

    public void setRef(int ref) {
        this.ref = ref;
    }

    public void setNom_stade(String nom_stade) {
        this.nom_stade = nom_stade;
    }

    public void setRegion(String region) {
        this.region = region;
    }
    

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setDate_construction(Date date_construction) {
        this.date_construction = date_construction;
    }

    public void setSurface(String surface) {
        this.surface = surface;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Stade{" + "ref=" + ref + ", nom_stade=" + nom_stade + ", region=" + region + ", adresse=" + adresse + ", date_construction=" + date_construction + ", surface=" + surface + ", capacite=" + capacite + ", image=" + image + '}';
    }

   
    
    
    
    
    
    
}


