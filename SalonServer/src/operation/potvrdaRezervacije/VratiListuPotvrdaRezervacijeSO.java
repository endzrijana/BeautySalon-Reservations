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
public class VratiListuPotvrdaRezervacijeSO extends AbstractGenericOperation {

    List<PotvrdaRezervacije> rezervacije;

    @Override
    protected void validate(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param, String key) throws Exception {
        rezervacije = broker.getAll(new PotvrdaRezervacije(), null);
        if (rezervacije != null) {
            for (PotvrdaRezervacije p : rezervacije) {
                String uslovSt = " WHERE sr.idPotvrdaRezervacije = " + p.getIdPotvrdaRezervacije();
                List<StavkaRezervacije> stavke = broker.getAll(new StavkaRezervacije(), uslovSt);
                p.setStavke(stavke);
            }
        }
    }

    public List<PotvrdaRezervacije> getRezervacije() {
        return rezervacije;
    }

}
