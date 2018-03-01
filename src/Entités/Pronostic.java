/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entités;

/**
 *
 * @author ali hamouda
 */
public class Pronostic {
    private int idUser;
    private int idMatch;
    private String pro;

    public Pronostic(int idUser, int idMatch, String pro) {
        this.idUser = idUser;
        this.idMatch = idMatch;
        this.pro = pro;
    }

    public Pronostic(int idUser, String pro) {
        this.idUser = idUser;
        this.pro = pro;
    }
    

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdMatch() {
        return idMatch;
    }

    public void setIdMatch(int idMatch) {
        this.idMatch = idMatch;
    }

    public String getPro() {
        return pro;
    }

    public void setPro(String pro) {
        this.pro = pro;
    }

    @Override
    public String toString() {
        return "pronostic{" + "idUser=" + idUser + ", idMatch=" + idMatch + ", pro=" + pro + '}';
    }
    
    
    
    
}
