/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.potvrdaRezervacije;

import domain.PotvrdaRezervacije;
import domain.StavkaRezervacije;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import operation.AbstractGenericOperation;

/**
 *
 * @author Ana
 */
public class PromeniPotvrdaRezervacijeSO extends AbstractGenericOperation {

    @Override
    protected void validate(Object param) throws Exception {
        if (param == null || !(param instanceof PotvrdaRezervacije)) {
            throw new Exception("Sistem ne moze da zapamti potvrdu rezervacije");
        }

    }

    @Override
    protected void executeOperation(Object param, String key) throws Exception {
        PotvrdaRezervacije pr = (PotvrdaRezervacije) param;
        List<StavkaRezervacije> stavke = pr.getStavke();
        String uslov = " WHERE sr.idPotvrdaRezervacije=" + pr.getIdPotvrdaRezervacije();
        List<StavkaRezervacije> stb = broker.getAll(new StavkaRezervacije(), uslov);
        Map<StavkaRezervacije, Boolean> mapa = new HashMap<>();
        for (StavkaRezervacije sb : stb) {
            mapa.put(sb, false);
        }
        for (StavkaRezervacije s : stavke) {
            for (StavkaRezervacije sb : stb) {
                if (s.equals(sb)) {
                    mapa.put(sb, true);
                }
            }
        }
        for (Map.Entry<StavkaRezervacije, Boolean> entry : mapa.entrySet()) {
            if (!entry.getValue()) {
                broker.delete(entry.getKey());
            }
        }
        for (StavkaRezervacije sr : stavke) {
            sr.setPotvrdaRezervacije(pr);
            if (sr.getRedniBroj() <= 0) {
                broker.add(sr);
            } else {
                broker.update(sr);
            }
        }
        broker.update(pr);
    }

}
