/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

/**
 *
 * @author ali hamouda
 */
public class Equipe {

   public int id;
    private String nom_equipe;
    private String nom_poule;
    private String continent;
    private int nbr_joueur;
    private int nbr_coupe;
    private int nbr_participation;
    private double taux_victoire;

    public Equipe(int id, String nom_equipe, String nom_poule, String continent, int nbr_joueur, int nbr_coupe, int nbr_participation, double taux_victoire) {
        this.id = id;
        this.nom_equipe = nom_equipe;
        this.nom_poule = nom_poule;
        this.continent = continent;
        this.nbr_joueur = nbr_joueur;
        this.nbr_coupe = nbr_coupe;
        this.nbr_participation = nbr_participation;
        this.taux_victoire = taux_victoire;
    }

    

    public Equipe(String nom_equipe, String nom_poule, String continent, int nbr_joueur, int nbr_coupe, int nbr_participation, double taux_victoire) {
        this.nom_equipe = nom_equipe;
        this.nom_poule = nom_poule;
        this.continent = continent;
        this.nbr_joueur = nbr_joueur;
        this.nbr_coupe = nbr_coupe;
        this.nbr_participation = nbr_participation;
        this.taux_victoire = taux_victoire;
    }

    public Equipe(String nom_equipe, String nom_poule, String continent, int nbr_joueur, int nbr_coupe, int nbr_participation) {
        this.nom_equipe = nom_equipe;
        this.nom_poule = nom_poule;
        this.continent = continent;
        this.nbr_joueur = nbr_joueur;
        this.nbr_coupe = nbr_coupe;
        this.nbr_participation = nbr_participation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_poule() {
        return nom_poule;
    }

    public void setNom_poule(String nom_poule) {
        this.nom_poule = nom_poule;
    }

   
    

    
    

    public int getNbr_joueur() {
        return nbr_joueur;
    }

    public void setNbr_joueur(int nbr_joueur) {
        this.nbr_joueur = nbr_joueur;
    }

    public int getNbr_coupe() {
        return nbr_coupe;
    }

    public void setNbr_coupe(int nbr_coupe) {
        this.nbr_coupe = nbr_coupe;
    }

    public int getNbr_participation() {
        return nbr_participation;
    }

    public void setNbr_participation(int nbr_participation) {
        this.nbr_participation = nbr_participation;
    }


    

   

    public String getNom_equipe() {
        return nom_equipe;
    }

    public void setNom_equipe(String nom_equipe) {
        this.nom_equipe = nom_equipe;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public double getTaux_victoire() {
        return taux_victoire;
    }

   
  @Override
    public String toString() {
        return         this.getNom_equipe()+"           "+this.getContinent()+"                         "+this.getNbr_joueur()+"                    "+this.getNbr_coupe()+"                     "+this.getNbr_participation()+"      "+this.getTaux_victoire();
    }
   
    
    
    

    
}
