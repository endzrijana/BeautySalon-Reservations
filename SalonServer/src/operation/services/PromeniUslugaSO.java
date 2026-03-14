/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.services;

import domain.Usluga;
import operation.AbstractGenericOperation;

/**
 *
 * @author Ana
 */
public class PromeniUslugaSO extends AbstractGenericOperation {

    @Override
    protected void validate(Object param) throws Exception {
        if (param == null || !(param instanceof Usluga)) {
            throw new Exception();
        }
        Usluga u = (Usluga) param;
        if (u.getNazivUsluge() == null || u.getNazivUsluge().isEmpty()) {
            throw new Exception("Greska: NAZIV");
        }
        if (u.getCena() <= 0) {
            throw new Exception("Cena usluge mora biti veća od 0.");
        }
        if (u.getTrajanje() <= 0) {
            throw new Exception("Trajanje usluge mora biti veće od 0.");
        }
    }

    @Override
    protected void executeOperation(Object param, String key) throws Exception {
        broker.update((Usluga) param);
    }

}
