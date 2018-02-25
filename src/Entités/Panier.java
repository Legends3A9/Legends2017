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
public class Panier {
    private int idpanier;
    private int iduser;

    
    public Panier(){}

    public Panier(int iduser) {
        this.iduser = iduser;
    }
    
    public Panier(int idpanier, int iduser) {
        this.idpanier = idpanier;
        this.iduser = iduser;
    }

    public int getIdpanier() {
        return idpanier;
    }

    public void setIdpanier(int idpanier) {
        this.idpanier = idpanier;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    @Override
    public String toString() {
        return "Panier{" + "idpanier=" + idpanier + ", iduser=" + iduser + '}';
    }
    
}
