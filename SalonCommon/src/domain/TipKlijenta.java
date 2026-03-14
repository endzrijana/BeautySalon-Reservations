/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Ana
 */
public class TipKlijenta implements AbstractDomainObject{
    private int idTipKlijenta;
    private String naziv;
    private int popust;

    public TipKlijenta() {
    }

    public TipKlijenta(int idTipKlijenta, String naziv, int popust) {
        this.idTipKlijenta = idTipKlijenta;
        this.naziv = naziv;
        this.popust = popust;
    }
    

    public int getIdTipKlijenta() {
        return idTipKlijenta;
    }

    public String getNaziv() {
        return naziv;
    }

    public int getPopust() {
        return popust;
    }

    public void setIdTipKlijenta(int idTipKlijenta) {
        this.idTipKlijenta = idTipKlijenta;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setPopust(int popust) {
        this.popust = popust;
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
        final TipKlijenta other = (TipKlijenta) obj;
        return this.idTipKlijenta == other.idTipKlijenta;
    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public String vratiNazivTabele() {
        return "tipklijenta";
    }

    @Override
    public List<AbstractDomainObject> vratiListu(ResultSet rs) throws Exception {
        List<AbstractDomainObject> lista= new ArrayList<>();
        while(rs.next()){
            int idtipKl=rs.getInt("idtipKlijenta");
            String naziv = rs.getString("naziv");
            int popust = rs.getInt("popust");
            TipKlijenta t= new TipKlijenta(idtipKl, naziv, popust);
            lista.add(t);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "naziv,popust";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+naziv+"',"+popust;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "tipklijenta.idTipKlijenta = "+idTipKlijenta;
    }

    @Override
    public AbstractDomainObject vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "naziv='"+naziv+"',popust="+popust;
    }

    @Override
    public void setID(int id) {
        this.idTipKlijenta = id;
    }
}
