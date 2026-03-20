/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.potvrdaRezervacije;

import domain.AbstractDomainObject;
import domain.Klijent;
import domain.Kozmeticar;
import domain.PotvrdaRezervacije;
import domain.StavkaRezervacije;
import domain.Usluga;
import java.util.ArrayList;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Ana
 */
public class VratiListuPotvrdaRezervacijeSO extends AbstractGenericOperation {
    
    private List<PotvrdaRezervacije> lista;
    private PotvrdaRezervacije kriterijum;

    @Override
    protected void validate(Object param) throws Exception {
        if (param != null && !(param instanceof PotvrdaRezervacije)) {
            throw new Exception("Neispravan kriterijum.");
        }
        kriterijum = (PotvrdaRezervacije) param;
    }

    @Override
    protected void executeOperation(Object param, String key) throws Exception {
        StringBuilder uslov = new StringBuilder();
        boolean whereDodat = false;

        if (kriterijum != null && kriterijum.getKlijent() != null) {
            uslov.append(whereDodat ? " AND " : " WHERE ");
            uslov.append("pr.idKlijent = ").append(kriterijum.getKlijent().getIdKlijent());
            whereDodat = true;
        }
        if (kriterijum != null && kriterijum.getKozmeticar() != null) {
            uslov.append(whereDodat ? " AND " : " WHERE ");
            uslov.append("pr.idKozmeticar = ").append(kriterijum.getKozmeticar().getIdKozmeticar());
            whereDodat = true;
        }
        if (kriterijum != null && kriterijum.getIdPotvrdaRezervacije() > 0) {
            uslov.append(whereDodat ? " AND " : " WHERE ");
            uslov.append("pr.idPotvrdaRezervacije = ").append(kriterijum.getIdPotvrdaRezervacije());
            whereDodat = true;
        }
        if (kriterijum != null && kriterijum.getStavke() != null && !kriterijum.getStavke().isEmpty()
                && kriterijum.getStavke().get(0).getUsluga() != null) {
            uslov.append(whereDodat ? " AND " : " WHERE ");
            uslov.append("EXISTS (SELECT 1 FROM stavkarezervacije sr WHERE sr.idPotvrdaRezervacije = pr.idPotvrdaRezervacije ");
            uslov.append("AND sr.idUsluga = ").append(kriterijum.getStavke().get(0).getUsluga().getIdUsluga()).append(")");
        }

        lista = broker.getAll(new PotvrdaRezervacije(), uslov.toString());
        if (lista == null) lista = new ArrayList<>();

        for (PotvrdaRezervacije p : lista) {
            String uslovSt = " WHERE sr.idPotvrdaRezervacije = " + p.getIdPotvrdaRezervacije();
            p.setStavke(broker.getAll(new StavkaRezervacije(), uslovSt));
        }
    }

    public List<PotvrdaRezervacije> getLista() {
        return lista;
    }
    
    

}
