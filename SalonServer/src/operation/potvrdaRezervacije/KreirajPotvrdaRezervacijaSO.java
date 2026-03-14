/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.potvrdaRezervacije;

import domain.AbstractDomainObject;
import domain.PotvrdaRezervacije;
import operation.AbstractGenericOperation;

/**
 *
 * @author Ana
 */
public class KreirajPotvrdaRezervacijaSO extends AbstractGenericOperation {

    private PotvrdaRezervacije result;

    @Override
    protected void validate(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param, String key) throws Exception {
        PotvrdaRezervacije pr = new PotvrdaRezervacije();
        pr.setDatumRezervacije(new java.util.Date()); 
        broker.add(pr); 
        this.result = pr; 
    }

    public PotvrdaRezervacije getResult() {
        return result;
    }

}
