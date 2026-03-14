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
public class PromeniKlijentSO extends AbstractGenericOperation {

    @Override
    protected void validate(Object param) throws Exception {
        if (param == null || !(param instanceof Klijent)) {
            throw new Exception("Sistem ne moze da kreira klijenta.");
        }
        Klijent k = (Klijent) param;
        if (k.getIdKlijent() <= 0) {
            throw new Exception("Neispravan ID klijenta.");
        }
        if (k.getIme() == null || k.getIme().isEmpty()) {
            throw new Exception("Ime klijenta ne sme biti prazno.");
        }
        if (k.getPrezime() == null || k.getPrezime().isEmpty()) {
            throw new Exception("Prezime klijenta ne sme biti prazno.");
        }
        if (k.getEmail() == null || k.getEmail().isEmpty()) {
            throw new Exception("Email klijenta ne sme biti prazan.");
        }

    }

    @Override
    protected void executeOperation(Object param, String key) throws Exception {
        broker.update((Klijent) param);
    }

}
