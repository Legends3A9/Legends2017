/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entités;

/**
 *
 * @author boussandel
 */
public class Participation {
    
    int idParticipation ; 
    int idUser ; 
    int idEvenement ; 

    public Participation(int idParticipation, int idUser, int idEvenement) {
        this.idParticipation = idParticipation;
        this.idUser = idUser;
        this.idEvenement = idEvenement;
    }

    public Participation(int idUser, int idEvenement) {
        this.idUser = idUser;
        this.idEvenement = idEvenement;
    }

    public Participation() {
    }

    public int getIdParticipation() {
        return idParticipation;
    }

    public void setIdParticipation(int idParticipation) {
        this.idParticipation = idParticipation;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdEvenement() {
        return idEvenement;
    }

    public void setIdEvenement(int idEvenement) {
        this.idEvenement = idEvenement;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.idParticipation;
        hash = 89 * hash + this.idUser;
        hash = 89 * hash + this.idEvenement;
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
        final Participation other = (Participation) obj;
        if (this.idParticipation != other.idParticipation) {
            return false;
        }
        if (this.idUser != other.idUser) {
            return false;
        }
        if (this.idEvenement != other.idEvenement) {
            return false;
        }
        return true;
    }
    
    
    
}
