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
public class Klijent implements AbstractDomainObject {

    private int idKlijent;
    private String ime;
    private String prezime;
    private String email;
    private TipKlijenta tipKlijenta;

    public Klijent() {
    }

    public Klijent(int idKlijent, String ime, String prezime, String email, TipKlijenta tipKlijenta) {
        this.idKlijent = idKlijent;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.tipKlijenta = tipKlijenta;
    }

    public int getIdKlijent() {
        return idKlijent;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getEmail() {
        return email;
    }

    public TipKlijenta getTipKlijenta() {
        return tipKlijenta;
    }

    public void setIdKlijent(int idKlijent) {
        this.idKlijent = idKlijent;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTipKlijenta(TipKlijenta tipKlijenta) {
        this.tipKlijenta = tipKlijenta;
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
        final Klijent other = (Klijent) obj;
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        if (!Objects.equals(this.prezime, other.prezime)) {
            return false;
        }
        return Objects.equals(this.email, other.email);
    }

    @Override
    public String toString() {
        return ime+" "+prezime;
    }

    @Override
    public String vratiNazivTabele() {
        return "klijent";
    }

    @Override
    public List<AbstractDomainObject> vratiListu(ResultSet rs) throws Exception {
        List<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            int idKlijent = rs.getInt("k.idKlijent");
            String ime = rs.getString("k.ime");
            String prezime = rs.getString("k.prezime");
            String email = rs.getString("k.email");

            int idTip = rs.getInt("k.idTipKlijent");
            String nazivTipa = rs.getString("tk.naziv");
            int popust = rs.getInt("tk.popust");
            TipKlijenta tk = new TipKlijenta();
            tk.setIdTipKlijenta(idTip);
            tk.setNaziv(nazivTipa);
            tk.setPopust(popust);
            Klijent klijent = new Klijent(idKlijent, ime, prezime, email, tk);
            lista.add(klijent);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime,prezime,email,idTipKlijent";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        Object tipId = (tipKlijenta == null) ? "NULL" : tipKlijenta.getIdTipKlijenta();
        return "'" + ime + "', '" + prezime + "', '" + email + "'," + tipId;

    }

    @Override
    public String vratiPrimarniKljuc() {
        return "idKlijent = " + idKlijent;
    }

    @Override
    public AbstractDomainObject vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {

        Object tipId = (tipKlijenta == null) ? "NULL" : tipKlijenta.getIdTipKlijenta();
        return "ime='" + ime + "',prezime='" + prezime + "',"
                + "email='" + email + "',"
                + "idTipKlijent=" + tipId;
    }

    @Override
    public void setID(int id) {
        this.idKlijent = id;
    }

}
