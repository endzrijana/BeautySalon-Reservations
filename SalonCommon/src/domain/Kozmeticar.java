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
public class Kozmeticar implements AbstractDomainObject{
    private int idKozmeticar;
    private String imePrezime;
    private Date datumZaposlenja;
    private String korisnickoIme;
    private String sifra;
    private Sertifikat sertifikat;

    public Kozmeticar() {
    }

    public Kozmeticar(int idKozmeticar, String imePrezime, Date datumZaposlenja, String korisnickoIme, String sifra) {
        this.idKozmeticar = idKozmeticar;
        this.imePrezime = imePrezime;
        this.datumZaposlenja = datumZaposlenja;
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
    }
    
    public int getIdKozmeticar() {
        return idKozmeticar;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public Date getDatumZaposlenja() {
        return datumZaposlenja;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public String getSifra() {
        return sifra;
    }

    public void setIdKozmeticar(int idKozmeticar) {
        this.idKozmeticar = idKozmeticar;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    public void setDatumZaposlenja(Date datumZaposlenja) {
        this.datumZaposlenja = datumZaposlenja;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    @Override
    public String toString() {
        return imePrezime;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Kozmeticar other = (Kozmeticar) obj;
        if (!Objects.equals(this.korisnickoIme, other.korisnickoIme)) {
            return false;
        }
        return Objects.equals(this.sifra, other.sifra);
    }

    

    @Override
    public String vratiNazivTabele() {
        return "kozmeticar";
    }

    @Override
    public List<AbstractDomainObject> vratiListu(ResultSet rs) throws Exception {
        List<AbstractDomainObject> lista= new ArrayList<>();
        while(rs.next()){
            int idKozmeticar=rs.getInt("idKozmeticar");
            String imePrezime = rs.getString("imePrezime");
            Date datumZaposlenja = rs.getDate("datumZaposlenja");
            String korisnickoIme = rs.getString("korisnickoIme");
            String sifra = rs.getString("sifra");
            Kozmeticar k = new Kozmeticar(idKozmeticar, imePrezime, datumZaposlenja, korisnickoIme, sifra);
            lista.add(k);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "imePrezime,datumZaposlenja,korisnickoIme,sifra";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+imePrezime+"',"+datumZaposlenja+",'"+korisnickoIme+"','"+sifra+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "kozmeticar.idKozmeticar = "+idKozmeticar;
    }

    @Override
    public AbstractDomainObject vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "imePrezime='"+imePrezime+"',datumZaposlenja="+datumZaposlenja+",korisnickoIme='"+korisnickoIme+"',sifra='"+sifra+"'";
    }

    @Override
    public void setID(int id) {
        this.idKozmeticar=id;
    }

    public Sertifikat getSertifikat() {
        return sertifikat;
    }

    public void setSertifikat(Sertifikat sertifikat) {
        this.sertifikat = sertifikat;
    }
    
    
}
