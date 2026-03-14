/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.util.List;
import java.sql.ResultSet;
/**
 *
 * @author Ana
 */
public interface AbstractDomainObject extends Serializable {
    public String vratiNazivTabele();
    public List<AbstractDomainObject> vratiListu(ResultSet rs) throws Exception;
    public String vratiKoloneZaUbacivanje();
    public String vratiVrednostiZaUbacivanje();
    public String vratiPrimarniKljuc();
    public AbstractDomainObject vratiObjekatIzRS(ResultSet rs) throws Exception;
    public String vratiVrednostiZaIzmenu();

    public void setID(int aInt);

}
