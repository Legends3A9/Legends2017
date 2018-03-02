/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entitiés;

import java.sql.Date;

/**
 *
 * @author melek
 */
public class Ticket {
    private int ref;
    private int nombre;
    private String stade;
    private String equipe1;
    private String equipe2;
    private Date date_match;
    private String heure;
    private String place;
    private float prix;

    public Ticket(int ref, int nombre, String stade, String equipe1, String equipe2, Date date_match, String heure, String place, float prix) {
        this.ref = ref;
        this.nombre = nombre;
        this.stade = stade;
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
        this.date_match = date_match;
        this.heure = heure;
        this.place = place;
        this.prix = prix;
    }
    
    public Ticket(int nombre, String stade, String equipe1, String equipe2, Date date_match, String heure, String place, float prix) {
        
        this.nombre = nombre;
        this.stade = stade;
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
        this.date_match = date_match;
        this.heure = heure;
        this.place = place;
        this.prix = prix;
    }

    public int getRef() {
        return ref;
    }

    public void setRef(int ref) {
        this.ref = ref;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public String getStade() {
        return stade;
    }

    public void setStade(String stade) {
        this.stade = stade;
    }

    public String getEquipe1() {
        return equipe1;
    }

    public void setEquipe1(String equipe1) {
        this.equipe1 = equipe1;
    }

    public String getEquipe2() {
        return equipe2;
    }

    public void setEquipe2(String equipe2) {
        this.equipe2 = equipe2;
    }

    public Date getDate_match() {
        return date_match;
    }

    public void setDate_match(Date date_match) {
        this.date_match = date_match;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

   

    @Override
    public int hashCode() {
        return 6;
    }

    
    
    
    
    
    
    
}
