/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entités;

/**
 *
 * @author Dambo
 */
public class Joueur {
    private String nom_equipe;
    private String nom_joueur;
    private String post;

    public Joueur(String nom_equipe, String nom_joueur, String post) {
        this.nom_equipe = nom_equipe;
        this.nom_joueur = nom_joueur;
        this.post = post;
    }

    public String getNom_equipe() {
        return nom_equipe;
    }

    public void setNom_equipe(String nom_equipe) {
        this.nom_equipe = nom_equipe;
    }

    public String getNom_joueur() {
        return nom_joueur;
    }

    public void setNom_joueur(String nom_joueur) {
        this.nom_joueur = nom_joueur;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }
    
    
    
}
