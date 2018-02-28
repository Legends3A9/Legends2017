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
public class Ticket {
    private int ref;
    private int nombre;
    private String stade;
    private String match;
    private Date date_match;
    private String place;
    private float prix;

    public Ticket(int ref, int nombre, String stade, String match, Date date_match, String place, float prix) {
        this.ref = ref;
        this.nombre = nombre;
        this.stade = stade;
        this.match = match;
        this.date_match = date_match;
        this.place = place;
        this.prix = prix;
    }
    public Ticket(int nombre, String stade, String match, Date date_match, String place, float prix) {
      
        this.nombre = nombre;
        this.stade = stade;
        this.match = match;
        this.date_match = date_match;
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

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    public Date getDate_match() {
        return date_match;
    }

    public void setDate_match(Date date_match) {
        this.date_match = date_match;
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
    public String toString() {
        return "Ticket{" + "ref=" + ref + ", nombre=" + nombre + ", stade=" + stade + ", match=" + match + ", date_match=" + date_match + ", place=" + place + ", prix=" + prix + '}';
    }
    
    
    
    
    
}
