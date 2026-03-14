/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to update this template
 */
package operation.clients;

import domain.Klijent;
import operation.AbstractGenericOperation;

/**
 *
 * @author Ana
 */
public class ObrisiKlijentSO extends AbstractGenericOperation {

    @Override
    protected void validate(Object param) throws Exception {
        if (param == null || !(param instanceof Klijent)) {
            throw new Exception("Sistem ne moze da obrise klijenta.");
        }
        Klijent k = (Klijent) param;
        if (k.getIdKlijent() <= 0) {
            throw new Exception("Neispravan ID klijenta.");
        }
    }

    @Override
    protected void executeOperation(Object param, String key) throws Exception {
        broker.delete((Klijent) param);
    }

}
