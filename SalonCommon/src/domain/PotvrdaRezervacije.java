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
public class PotvrdaRezervacije implements AbstractDomainObject {

    private int idPotvrdaRezervacije;
    private Date datumRezervacije;
    private double ukupanIznos;
    private Kozmeticar kozmeticar;
    private Klijent klijent;
    private List<StavkaRezervacije> stavke;

    public PotvrdaRezervacije() {
    }

    public PotvrdaRezervacije(int idPotvrdaRezervacije, Date datumRezervacije, double ukupanIznos, Kozmeticar kozmeticar, Klijent klijent) {
        this.idPotvrdaRezervacije = idPotvrdaRezervacije;
        this.datumRezervacije = datumRezervacije;
        this.ukupanIznos = ukupanIznos;
        this.kozmeticar = kozmeticar;
        this.klijent = klijent;
    }

    public int getIdPotvrdaRezervacije() {
        return idPotvrdaRezervacije;
    }

    public Date getDatumRezervacije() {
        return datumRezervacije;
    }

    public double getUkupanIznos() {
        return ukupanIznos;
    }

    public Kozmeticar getKozmeticar() {
        return kozmeticar;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setIdPotvrdaRezervacije(int idPotvrdaRezervacije) {
        this.idPotvrdaRezervacije = idPotvrdaRezervacije;
    }

    public void setDatumRezervacije(Date datumRezervacije) {
        this.datumRezervacije = datumRezervacije;
    }

    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public void setKozmeticar(Kozmeticar kozmeticar) {
        this.kozmeticar = kozmeticar;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
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
        final PotvrdaRezervacije other = (PotvrdaRezervacije) obj;
        if (!Objects.equals(this.datumRezervacije, other.datumRezervacije)) {
            return false;
        }
        if (!Objects.equals(this.kozmeticar, other.kozmeticar)) {
            return false;
        }
        return Objects.equals(this.klijent, other.klijent);
    }

    @Override
    public String toString() {
        return datumRezervacije + "";
    }

    @Override
    public String vratiNazivTabele() {
        return "potvrdarezervacije";
    }

    @Override
    public List<AbstractDomainObject> vratiListu(ResultSet rs) throws Exception {
        List<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            Klijent klijent = new Klijent();
            klijent.setIdKlijent(rs.getInt("k.idKlijent"));
            klijent.setIme(rs.getString("k.ime"));
            klijent.setPrezime(rs.getString("k.prezime"));
            klijent.setEmail(rs.getString("k.email"));
            
            TipKlijenta tip = new TipKlijenta();
            tip.setIdTipKlijenta(rs.getInt("tk.idTipKlijenta"));
            tip.setNaziv(rs.getString("tk.naziv")); 
            tip.setPopust(rs.getInt("tk.popust")); 
            klijent.setTipKlijenta(tip);
            
            Kozmeticar kozmeticar = new Kozmeticar();
            kozmeticar.setIdKozmeticar(rs.getInt("koz.idKozmeticar"));
            kozmeticar.setImePrezime(rs.getString("koz.imePrezime"));

            PotvrdaRezervacije pr = new PotvrdaRezervacije();
            pr.setIdPotvrdaRezervacije(rs.getInt("pr.idPotvrdaRezervacije"));
            pr.setDatumRezervacije(rs.getDate("pr.datumRezervacije"));
            pr.setUkupanIznos(rs.getDouble("pr.ukupanIznos"));
            pr.setKlijent(klijent);
            pr.setKozmeticar(kozmeticar);

            lista.add(pr);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "datumRezervacije,ukupanIznos,idKozmeticar,idKlijent";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        String datum = (datumRezervacije != null)
                ? "'" + new java.sql.Date(datumRezervacije.getTime()) + "'"
                : "NULL";
        String idKozm = (kozmeticar != null)
                ? kozmeticar.getIdKozmeticar() + ""
                : "NULL";
        String idKlij = (klijent != null)
                ? klijent.getIdKlijent() + ""
                : "NULL";
        return datum + "," + ukupanIznos + "," + idKozm + "," + idKlij;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "potvrdarezervacije.idPotvrdaRezervacije = " + idPotvrdaRezervacije;
    }

    @Override
    public AbstractDomainObject vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        String datum = (datumRezervacije != null)
                ? "'" + new java.sql.Date(datumRezervacije.getTime()) + "'"
                : "NULL";
        String idKozm = (kozmeticar != null)
                ? kozmeticar.getIdKozmeticar() + ""
                : "NULL";
        String idKlij = (klijent != null)
                ? klijent.getIdKlijent() + ""
                : "NULL";

        return "datumRezervacije= " + datum + ",ukupanIznos=" + ukupanIznos + ",idKozmeticar=" + idKozm + ",idKlijent=" + idKlij;
    }

    @Override
    public void setID(int id) {
        this.idPotvrdaRezervacije = id;
    }

    public List<StavkaRezervacije> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaRezervacije> stavke) {
        this.stavke = stavke;
    }

}
