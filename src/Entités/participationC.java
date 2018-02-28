/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entités;

/**
 *
 * @author Alaa
 */
public class participationC {
    private int idc;
    private int ido;
    private int iduser;

    public participationC() {
    }

    public participationC(int idc, int ido, int iduser) {
        this.idc = idc;
        this.ido = ido;
        this.iduser = iduser;
    }

    public participationC(int i, int ido) {
        this.ido = ido;
    }

    public int getIdc() {
        return idc;
    }

    public void setIdc(int idc) {
        this.idc = idc;
    }

    public int getIdo() {
        return ido;
    }

    public void setIdo(int ido) {
        this.ido = ido;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final participationC other = (participationC) obj;
        if (this.idc != other.idc) {
            return false;
        }
        if (this.ido != other.ido) {
            return false;
        }
        if (this.iduser != other.iduser) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "participationC{" + "idc=" + idc + ", ido=" + ido + ", iduser=" + iduser + '}';
    }
    
    
    
}
