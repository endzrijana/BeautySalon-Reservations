/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to update this template
 */
package repository.db.impl;

import domain.AbstractDomainObject;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import repository.db.DbConnectionFactory;
import repository.db.DbRepository;

/**
 *
 * @author Ana
 */
public class DbRepositoryGeneric implements DbRepository<AbstractDomainObject> {

    @Override
    public List<AbstractDomainObject> getAll(AbstractDomainObject param, String uslov) throws Exception {
        List<AbstractDomainObject> lista = new ArrayList<>();
        String upit = "SELECT * FROM " + param.vratiNazivTabele();
        if (param instanceof domain.Klijent) {
            upit += " k JOIN tipklijenta tk ON k.idTipKlijent = tk.idTipKlijenta";
        }
        if (param instanceof domain.PotvrdaRezervacije) {
            upit += " pr JOIN klijent k ON pr.idKlijent = k.idKlijent"
                    + " JOIN kozmeticar koz ON pr.idKozmeticar = koz.idKozmeticar"
                    + " JOIN tipklijenta tk ON k.idTipKlijent = tk.idTipKlijenta";
        }
        if (param instanceof domain.StavkaRezervacije) {
            upit += " sr JOIN usluga u ON sr.idUsluga = u.idUsluga "
                    + "JOIN potvrdarezervacije pr ON pr.idPotvrdaRezervacije = sr.idPotvrdaRezervacije";
                    
        }
        if (uslov != null) {
            upit += uslov;
        }
        System.out.println(upit);
        Statement st = DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = st.executeQuery(upit);
        lista = param.vratiListu(rs);

        rs.close();
        st.close();
        return lista;
    }

    @Override
    public void add(AbstractDomainObject param) throws Exception {
        String upit = "INSERT INTO " + param.vratiNazivTabele() + " (" + param.vratiKoloneZaUbacivanje() + ") VALUES( " + param.vratiVrednostiZaUbacivanje() + " )";
        System.out.println(upit);
        Statement st = DbConnectionFactory.getInstance().getConnection().createStatement();
        st.executeUpdate(upit, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = st.getGeneratedKeys();
        if (rs.next()) {
            param.setID(rs.getInt(1));
        }
        rs.close();
        st.close();
    }

    @Override
    public void update(AbstractDomainObject param) throws Exception {
        String upit = "UPDATE " + param.vratiNazivTabele() + " SET " + param.vratiVrednostiZaIzmenu() + " WHERE " + param.vratiPrimarniKljuc();
        System.out.println(upit);
        Statement st = DbConnectionFactory.getInstance().getConnection().createStatement();
        st.executeUpdate(upit);
        st.close();
    }

    @Override
    public void delete(AbstractDomainObject param) throws Exception {
        String upit = "DELETE FROM " + param.vratiNazivTabele() + " WHERE " + param.vratiPrimarniKljuc();
        System.out.println(upit);
        Statement st = DbConnectionFactory.getInstance().getConnection().createStatement();
        st.executeUpdate(upit);
        st.close();
    }

    @Override
    public List<AbstractDomainObject> getAll() {//TODO
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int addReturnKey(AbstractDomainObject param) throws Exception {
        String upit = "INSERT INTO " + param.vratiNazivTabele() + " (" + param.vratiKoloneZaUbacivanje() + ") VALUES ("
                + param.vratiVrednostiZaUbacivanje() + ")";
        Statement st = DbConnectionFactory.getInstance().getConnection().createStatement();
        st.executeUpdate(upit, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = st.getGeneratedKeys();
        int generatedId = -1;
        if (rs.next()) {
            generatedId = rs.getInt(1);
        }
        rs.close();
        st.close();
        return generatedId;
    }

}
