/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.clients;

import domain.Klijent;
import domain.TipKlijenta;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Ana
 */
public class VratiListuKlijentSO extends AbstractGenericOperation {

    private List<Klijent> lista;

    @Override
    protected void validate(Object param) throws Exception {
        if (param != null && !(param instanceof Klijent)) {
            throw new Exception("Parametar nije instanca odgovarajuce klase");
        }
    }

    @Override
    protected void executeOperation(Object param, String key) throws Exception {
         if (param == null) {
        lista = broker.getAll(new Klijent(), "");
        return;
    }
        Klijent k = (Klijent) param;
        StringBuilder uslov = new StringBuilder();
        boolean whereDodat = false;

        if (k.getIme() != null || k.getPrezime() != null || k.getEmail() != null) {
            uslov.append(" WHERE (k.ime='").append(k.getIme() != null ? k.getIme() : "").append("'")
                    .append(" OR k.prezime='").append(k.getPrezime() != null ? k.getPrezime() : "").append("'")
                    .append(" OR k.email='").append(k.getEmail() != null ? k.getEmail() : "").append("')");
            whereDodat = true;
        }
        if (k.getTipKlijenta() != null) {
            uslov.append(whereDodat ? " AND " : " WHERE ");
            uslov.append("k.idTipKlijent = ").append(k.getTipKlijenta().getIdTipKlijenta());
        }

        lista = broker.getAll(new Klijent(), uslov.toString());
    }

    public List<Klijent> getLista() {
        return lista;
    }

}
