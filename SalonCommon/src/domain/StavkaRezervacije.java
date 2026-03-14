/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Ana
 */
public class StavkaRezervacije implements AbstractDomainObject {

    private int redniBroj;
    private int popust;
    private double cena;
    private double iznosSt;
    private Usluga usluga;
    private PotvrdaRezervacije potvrdaRezervacije;

    public StavkaRezervacije() {
    }

    public StavkaRezervacije(int redniBroj, int popust, double cena, double iznosSt, Usluga usluga, PotvrdaRezervacije potvrdaRezervacije) {
        this.redniBroj = redniBroj;
        this.popust = popust;
        this.cena = cena;
        this.iznosSt = iznosSt;
        this.usluga = usluga;
        this.potvrdaRezervacije = potvrdaRezervacije;
    }

    public int getRedniBroj() {
        return redniBroj;
    }

    public int getPopust() {
        return popust;
    }

    public double getCena() {
        return cena;
    }

    public double getIznosSt() {
        return iznosSt;
    }

    public Usluga getUsluga() {
        return usluga;
    }

    public PotvrdaRezervacije getPotvrdaRezervacije() {
        return potvrdaRezervacije;
    }

    public void setUsluga(Usluga usluga) {
        this.usluga = usluga;
    }

    public void setPotvrdaRezervacije(PotvrdaRezervacije potvrdaRezervacije) {
        this.potvrdaRezervacije = potvrdaRezervacije;
    }

    public void setRedniBroj(int redniBroj) {
        this.redniBroj = redniBroj;
    }

    public void setPopust(int popust) {
        this.popust = popust;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public void setIznosSt(double iznosSt) {
        this.iznosSt = iznosSt;
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
        final StavkaRezervacije other = (StavkaRezervacije) obj;
        if (this.popust != other.popust) {
            return false;
        }
        if (Double.doubleToLongBits(this.cena) != Double.doubleToLongBits(other.cena)) {
            return false;
        }
        if (Double.doubleToLongBits(this.iznosSt) != Double.doubleToLongBits(other.iznosSt)) {
            return false;
        }
        if (!Objects.equals(this.usluga, other.usluga)) {
            return false;
        }
        return Objects.equals(this.potvrdaRezervacije, other.potvrdaRezervacije);
    }

    @Override
    public String toString() {
        return popust + " " + cena + " " + iznosSt + " " + potvrdaRezervacije + " " + usluga;
    }

    @Override
    public String vratiNazivTabele() {
        return "stavkarezervacije";
    }

    @Override
    public List<AbstractDomainObject> vratiListu(ResultSet rs) throws Exception {
        List<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            Usluga us = new Usluga();
            us.setIdUsluga(rs.getInt("u.idUsluga"));
            us.setNazivUsluge(rs.getString("u.nazivUsluge"));
            us.setCena(rs.getDouble("u.cena"));
            us.setTrajanje(rs.getInt("u.trajanje"));
            PotvrdaRezervacije prez = new PotvrdaRezervacije();
            prez.setIdPotvrdaRezervacije(rs.getInt("pr.idPotvrdaRezervacije"));
            prez.setDatumRezervacije(rs.getDate("pr.datumRezervacije"));

            StavkaRezervacije sr = new StavkaRezervacije();
            sr.setRedniBroj(rs.getInt("sr.redniBroj"));
            sr.setPopust(rs.getInt("sr.popust"));
            sr.setCena(rs.getDouble("sr.cena"));
            sr.setIznosSt(rs.getDouble("sr.iznosSt"));
            sr.setUsluga(us);
            sr.setPotvrdaRezervacije(prez);

            lista.add(sr);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "popust, cena, iznosSt, idUsluga,idPotvrdaRezervacije";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return popust + "," + cena + "," + iznosSt + "," + usluga.getIdUsluga() + "," + potvrdaRezervacije.getIdPotvrdaRezervacije();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "stavkarezervacije.redniBroj = " + redniBroj;
    }

    @Override
    public AbstractDomainObject vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "popust=" + popust + ",cena=" + cena + ",iznosSt=" + iznosSt + ",idUsluga=" + usluga.getIdUsluga() + ",idPotvrdaRezervacije=" + potvrdaRezervacije.getIdPotvrdaRezervacije();
    }

    @Override
    public void setID(int id) {
        this.redniBroj = id;
    }
}
