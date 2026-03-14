/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.kozmeticar;

import domain.Kozmeticar;
import operation.AbstractGenericOperation;

/**
 *
 * @author Ana
 */
public class ObrisiKozmeticarSO extends AbstractGenericOperation {

    @Override
    protected void validate(Object param) throws Exception {
        if(param==null || !(param instanceof Kozmeticar)){
            throw new Exception("Sistem ne moze da obrise klijenta");
        }
    }

    @Override
    protected void executeOperation(Object param, String key) throws Exception {
        broker.delete((Kozmeticar)param);
    }
}
