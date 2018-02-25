/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entités;

/**
 *
 * @author GlaDio007
 */

/**
 *
 * @author GlaDio007
 */
public class Produit {
    
    
    private int id;
    private String label;
    private String type;
    private float prix;
    private int quantite;
    private String image;
    private String taille;
    private String marque;
    private String couleur;
    private String description;

    public Produit(){};

    public Produit(int id, String label, String type, float prix, int quantite, String image, String taille, String marque, String couleur,String description) {
        this.id = id;
        this.label = label;
        this.type = type;
        this.prix = prix;
        this.quantite = quantite;
        this.image = image;
        this.taille = taille;
        this.marque = marque;
        this.couleur = couleur;
        this.description=description;
    }
    public Produit(String label, String type, float prix, int quantite, String image, String taille, String marque, String couleur,String description) {
        this.label = label;
        this.type = type;
        this.prix = prix;
        this.quantite = quantite;
        this.image = image;
        this.taille = taille;
        this.marque = marque;
        this.couleur = couleur;
        this.description=description;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
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

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", nom=" + label + ", type=" + type + ", prix=" + prix + ", quantite=" + quantite + ", image=" + image + ", taille=" + taille + ", marque=" + marque + ", couleur=" + couleur + '}';
    }

  
    
 
    
}