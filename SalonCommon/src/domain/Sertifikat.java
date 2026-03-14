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
public class Sertifikat implements AbstractDomainObject{
    private int idSertifikat;
    private String naziv;
    private String institucija;

    public Sertifikat() {
    }

    public Sertifikat(int idSertifikat, String naziv, String institucija) {
        this.idSertifikat = idSertifikat;
        this.naziv = naziv;
        this.institucija = institucija;
    }

    public int getIdSertifikat() {
        return idSertifikat;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getInstitucija() {
        return institucija;
    }

    public void setIdSertifikat(int idSertifikat) {
        this.idSertifikat = idSertifikat;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setInstitucija(String institucija) {
        this.institucija = institucija;
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
        final Sertifikat other = (Sertifikat) obj;
        if (this.idSertifikat != other.idSertifikat) {
            return false;
        }
        if (!Objects.equals(this.naziv, other.naziv)) {
            return false;
        }
        return Objects.equals(this.institucija, other.institucija);
    }

    @Override
    public String toString() {
        return naziv+" "+institucija;
    }

    @Override
    public String vratiNazivTabele() {
        return "sertifikat";
    }

    @Override
    public List<AbstractDomainObject> vratiListu(ResultSet rs) throws Exception {
        List<AbstractDomainObject> lista= new ArrayList<>();
        while(rs.next()){
            int idSertifikat=rs.getInt("idSertifikat");
            String naziv = rs.getString("naziv");
            String institucija = rs.getString("institucija");
            Sertifikat s = new Sertifikat(idSertifikat, naziv, institucija);
            lista.add(s);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "naziv,institucija";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+naziv+"','"+institucija+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "sertifikat.idSertifikat = "+idSertifikat;
    }

    @Override
    public AbstractDomainObject vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "naziv='"+naziv+"',institucija="+institucija;
    }

    @Override
    public void setID(int id) {
        this.idSertifikat = id;
    }
    
}
