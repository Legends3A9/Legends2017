/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalTime;
/**
 *
 * @author ali hamouda
 */
public class Match {
    private int id;
    private String equipe1;
    private String equipe2;
    private Date date_match;
    private Time heure;
    private String stade;
    private String type_phase;
    private String type_match;

    public Match(int id, String equipe1, String equipe2, Date date_match, Time heure, String stade, String type_phase, String type_match) {
        this.id = id;
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
        this.date_match = date_match;
        this.heure = heure;
        this.stade = stade;
        this.type_phase = type_phase;
        this.type_match = type_match;
    }

    public Match(String equipe1, String equipe2, Date date_match, Time heure, String stade, String type_phase, String type_match) {
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
        this.date_match = date_match;
        this.heure = heure;
        this.stade = stade;
        this.type_phase = type_phase;
        this.type_match = type_match;
    }

   

    

    public Time getHeure() {
        return heure;
    }

    public void setHeure(Time heure) {
        this.heure = heure;
    }
    

   

    public String getType_phase() {
        return type_phase;
    }

    public void setType_phase(String type_phase) {
        this.type_phase = type_phase;
    }

    public String getType_match() {
        return type_match;
    }

    public void setType_match(String type_match) {
        this.type_match = type_match;
    }


    

    

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getStade() {
        return stade;
    }

    public void setStade(String stade) {
        this.stade = stade;
    }

    @Override
    public String toString() {
        return "Match{" + "id=" + id + ", equipe1=" + equipe1 + ", equipe2=" + equipe2 + ", date_match=" + date_match + ", heure=" + heure + ", stade=" + stade + ", type_phase=" + type_phase + ", type_match=" + type_match + '}';
    }

    
    
    
   
    
    
    
    
}
