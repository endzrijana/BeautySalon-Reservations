/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.potvrdaRezervacije;

import domain.PotvrdaRezervacije;
import domain.StavkaRezervacije;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Ana
 */
public class PretraziPotvrdaRezervacijeSO extends AbstractGenericOperation {

    private PotvrdaRezervacije pr;

    @Override
    protected void validate(Object param) throws Exception {
        if (param == null || !(param instanceof PotvrdaRezervacije)) {
            throw new Exception("Sistem ne moze da pretrazi potvrdu rezervacije.");
        }
        PotvrdaRezervacije pr = (PotvrdaRezervacije) param;
        if (pr.getIdPotvrdaRezervacije() <= 0) {
            throw new Exception("Neispravan ID potvrde rezervacije.");
        }
    }

    @Override
    protected void executeOperation(Object param, String key) throws Exception {
        PotvrdaRezervacije pr = (PotvrdaRezervacije) param;
        String uslov = " WHERE pr.idPotvrdaRezervacije=" + pr.getIdPotvrdaRezervacije();
        this.pr = (PotvrdaRezervacije) broker.getAll(pr, uslov).get(0);
        String uslovSt = " WHERE sr.idPotvrdaRezervacije = " + this.pr.getIdPotvrdaRezervacije();
        List<StavkaRezervacije> stavke = broker.getAll(new StavkaRezervacije(), uslovSt);
        this.pr.setStavke(stavke);
    }

    public PotvrdaRezervacije getPr() {
        return pr;
    }
}
