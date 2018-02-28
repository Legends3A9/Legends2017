/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entités;

import java.util.Objects;

/**
 *
 * @author Alaa
 */
public class offreuser {
    private int id;
    private String type;
    private float prix;
    private String adresse;
    private int nb_place_dispo;
    private String description;
    private int tel;
    private String image;
    private String etat;
    private int iduser;

    public offreuser(int id, String type, float prix, String adresse, int nb_place_dispo, String description, int tel, String etat) {
        this.id = id;
        this.type = type;
        this.prix = prix;
        this.adresse = adresse;
        this.nb_place_dispo = nb_place_dispo;
        this.description = description;
        this.tel = tel;
        this.etat = etat;
    }

    public offreuser(String type, float prix, String adresse, int nb_place_dispo, String description, int tel,String image, String etat, int iduser) {
        this.type = type;
        this.prix = prix;
        this.adresse = adresse;
        this.nb_place_dispo = nb_place_dispo;
        this.description = description;
        this.tel = tel;
        this.image = image;
        this.etat = etat;
        this.iduser = iduser;
    }

    public offreuser(int id, String type, float prix, String adresse, int nb_place_dispo, String description, int tel, String image, String etat, int iduser) {
        this.id = id;
        this.type = type;
        this.prix = prix;
        this.adresse = adresse;
        this.nb_place_dispo = nb_place_dispo;
        this.description = description;
        this.tel = tel;
        this.image = image;
        this.etat = etat;
        this.iduser = iduser;
    }
    
    public offreuser(int id, String type, float prix, String adresse, int nb_place_dispo, String description, int tel, String image, String etat) {
        this.id = id;
        this.type = type;
        this.prix = prix;
        this.adresse = adresse;
        this.nb_place_dispo = nb_place_dispo;
        this.description = description;
        this.tel = tel;
        this.image = image;
        this.etat = etat;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    
    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public offreuser() {
        
    }

    
   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getNb_place_dispo() {
        return nb_place_dispo;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public void setNb_place_dispo(int nb_place_dispo) {
        this.nb_place_dispo = nb_place_dispo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        final offreuser other = (offreuser) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Float.floatToIntBits(this.prix) != Float.floatToIntBits(other.prix)) {
            return false;
        }
        if (this.nb_place_dispo != other.nb_place_dispo) {
            return false;
        }
        if (this.tel != other.tel) {
            return false;
        }
        if (this.iduser != other.iduser) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.adresse, other.adresse)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        if (!Objects.equals(this.etat, other.etat)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "offreuser{" + "id=" + id + ", type=" + type + ", prix=" + prix + ", adresse=" + adresse + ", nb_place_dispo=" + nb_place_dispo + ", description=" + description + ", tel=" + tel + ", image=" + image + ", etat=" + etat + ", iduser=" + iduser + '}';
    }
    

}