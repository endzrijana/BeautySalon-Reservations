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
public class Usluga implements AbstractDomainObject{
    private int idUsluga;
    private String nazivUsluge;
    private double cena;
    private int trajanje;

    public Usluga() {
    }

    public Usluga(int idUsluga, String nazivUsluge, double cena, int trajanje) {
        this.idUsluga = idUsluga;
        this.nazivUsluge = nazivUsluge;
        this.cena = cena;
        this.trajanje = trajanje;
    }
    

    public int getIdUsluga() {
        return idUsluga;
    }

    public String getNazivUsluge() {
        return nazivUsluge;
    }

    public double getCena() {
        return cena;
    }

    public int getTrajanje() {
        return trajanje;
    }

    public void setIdUsluga(int idUsluga) {
        this.idUsluga = idUsluga;
    }

    public void setNazivUsluge(String nazivUsluge) {
        this.nazivUsluge = nazivUsluge;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public void setTrajanje(int trajanje) {
        this.trajanje = trajanje;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public String toString() {
        return nazivUsluge;
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
        final Usluga other = (Usluga) obj;
        if (this.trajanje != other.trajanje) {
            return false;
        }
        return Objects.equals(this.nazivUsluge, other.nazivUsluge);
    }

    @Override
    public String vratiNazivTabele() {
        return "usluga";
    }

    @Override
    public List<AbstractDomainObject> vratiListu(ResultSet rs) throws Exception {
        List<AbstractDomainObject> lista= new ArrayList<>();
        while(rs.next()){
            int idUsluga=rs.getInt("idUsluga");
            String nazivUsluge = rs.getString("nazivUsluge");
            double cena = rs.getDouble("cena");
            int trajanje = rs.getInt("trajanje");
            Usluga usluga = new Usluga(idUsluga, nazivUsluge, cena, trajanje);
            lista.add(usluga);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "nazivUsluge,cena,trajanje";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+nazivUsluge+"',"+cena+","+trajanje;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "usluga.idUsluga = "+idUsluga;
    }

    @Override
    public AbstractDomainObject vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "nazivUsluge='"+nazivUsluge+"',"+"cena="+cena+",trajanje="+trajanje;
    }

    @Override
    public void setID(int id) {
        this.idUsluga = id;
    }
    
    
}
