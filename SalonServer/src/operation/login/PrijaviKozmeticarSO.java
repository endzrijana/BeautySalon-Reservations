/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to update this template
 */
package operation.login;

import domain.Kozmeticar;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Ana
 */
public class PrijaviKozmeticarSO extends AbstractGenericOperation {

    Kozmeticar kozm;

    @Override
    protected void validate(Object param) throws Exception {
        if (param == null || !(param instanceof Kozmeticar)) {
            throw new Exception("Sistem ne moze da prijavi kozmeticara.");
        }
        Kozmeticar k = (Kozmeticar) param;
        if (k.getKorisnickoIme() == null || k.getKorisnickoIme().isEmpty()) {
            throw new Exception("Korisnicko ime ne sme biti prazno.");
        }
        if (k.getSifra()== null || k.getSifra().isEmpty()) {
            throw new Exception("Sifra ne sme biti prazna.");
        }
    }

    @Override
    protected void executeOperation(Object param, String key) throws Exception {
        List<Kozmeticar> sviKozmeticari = broker.getAll((Kozmeticar) param, null);
        System.out.println("KLASA LoginOperacija SO" + sviKozmeticari);

        if (sviKozmeticari.contains((Kozmeticar) param)) {
            for (Kozmeticar k : sviKozmeticari) {
                if (k.equals((Kozmeticar) param)) {
                    kozm = k;
                    return;
                }
            }
        } else {
            kozm = null;
        }
    }

    public Kozmeticar getKozm() {
        return kozm;
    }

}
