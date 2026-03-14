/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.clients;

import domain.AbstractDomainObject;
import domain.Klijent;
import operation.AbstractGenericOperation;

/**
 *
 * @author Ana
 */
public class KreirajKlijentSO extends AbstractGenericOperation {

    private Klijent result;

    @Override
    protected void validate(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param, String key) throws Exception {
        broker.add((AbstractDomainObject) param);
        this.result = (Klijent) param;
    }

    public Klijent getResult() {
        return result;
    }

}
