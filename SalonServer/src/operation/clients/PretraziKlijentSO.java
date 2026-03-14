/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.clients;

import domain.Klijent;
import operation.AbstractGenericOperation;

/**
 *
 * @author Ana
 */
public class PretraziKlijentSO extends AbstractGenericOperation {

    private Klijent kl;

    @Override
    protected void validate(Object param) throws Exception {
        if (param == null || !(param instanceof Klijent)) {
            throw new Exception("Sistem ne moze da pretrazi klijenta.");
        }
        Klijent k = (Klijent) param;
        if (k.getIdKlijent() <= 0) {
            throw new Exception("Neispravan ID klijenta.");
        }
    }

    @Override
    protected void executeOperation(Object param, String key) throws Exception {
        Klijent k = (Klijent) param;
        String uslov = " WHERE k.idKlijent=" + k.getIdKlijent();
        this.kl = (Klijent) broker.getAll(k, uslov).get(0);
    }

    public Klijent getKl() {
        return kl;
    }

}
