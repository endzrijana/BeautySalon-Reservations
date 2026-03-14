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
public class koS implements AbstractDomainObject{
    private Kozmeticar kozmeticar;
    private Sertifikat sertifikat;
    private Date datumIzdavanja;

    public koS() {
    }

    public koS(Kozmeticar kozmeticar, Sertifikat sertifikat, Date datumIzdavanja) {
        this.kozmeticar = kozmeticar;
        this.sertifikat = sertifikat;
        this.datumIzdavanja = datumIzdavanja;
    }

    public Kozmeticar getKozmeticar() {
        return kozmeticar;
    }

    public Sertifikat getSertifikat() {
        return sertifikat;
    }

    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setKozmeticar(Kozmeticar kozmeticar) {
        this.kozmeticar = kozmeticar;
    }

    public void setSertifikat(Sertifikat sertifikat) {
        this.sertifikat = sertifikat;
    }

    public void setDatumIzdavanja(Date datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final koS other = (koS) obj;
        if (!Objects.equals(this.kozmeticar, other.kozmeticar)) {
            return false;
        }
        if (!Objects.equals(this.sertifikat, other.sertifikat)) {
            return false;
        }
        return Objects.equals(this.datumIzdavanja, other.datumIzdavanja);
    }

    @Override
    public String toString() {
        return "koS{" + "kozmeticar=" + kozmeticar + ", sertifikat=" + sertifikat + ", datumIzdavanja=" + datumIzdavanja + '}';
    }

    @Override
    public String vratiNazivTabele() {
        return "koS";
    }

    @Override
    public List<AbstractDomainObject> vratiListu(ResultSet rs) throws Exception {
        List<AbstractDomainObject> lista= new ArrayList<>();
        while(rs.next()){
            int sertifikat = rs.getInt("sertifikat");
            
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "kozemticar,sertifikat,datumIzdavanja";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return kozmeticar.getIdKozmeticar()+","+sertifikat.getIdSertifikat()+","+datumIzdavanja;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "kozmeticar= "+kozmeticar.getIdKozmeticar()+" AND sertifikat= "+sertifikat.getIdSertifikat();
    }

    @Override
    public AbstractDomainObject vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "kozmeticar="+kozmeticar+",sertifikat="+sertifikat+",datumOzdavanja="+datumIzdavanja;
    }

    @Override
    public void setID(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
