/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.potvrdaRezervacije;

import domain.Klijent;
import domain.PotvrdaRezervacije;
import domain.StavkaRezervacije;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Ana
 */
public class ObrisiPotvrduRezervacijeSO extends AbstractGenericOperation {

    @Override
    protected void validate(Object param) throws Exception {
        if (param == null || !(param instanceof PotvrdaRezervacije)) {
            throw new Exception("Sistem ne moze da obrise potvrdu rezervacije.");
        }
        PotvrdaRezervacije pr = (PotvrdaRezervacije) param;
        if (pr.getIdPotvrdaRezervacije() <= 0) {
            throw new Exception("Neispravan ID potvrde rezervacije.");
        }
    }

    @Override
    protected void executeOperation(Object param, String key) throws Exception {
        PotvrdaRezervacije pr = (PotvrdaRezervacije) param;
        String uslov = " WHERE sr.idPotvrdaRezervacije = " + pr.getIdPotvrdaRezervacije();
        List<StavkaRezervacije> stavke = broker.getAll(new StavkaRezervacije(), uslov);
        for (StavkaRezervacije sr : stavke) {
            broker.delete(sr);
        }

        broker.delete(pr);
    }
}
