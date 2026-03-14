/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.services;

import domain.Usluga;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Ana
 */
public class UcitajUslugeSO extends AbstractGenericOperation{
    List<Usluga> usluge;
    @Override
    protected void validate(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param, String key) throws Exception {
        usluge = broker.getAll(new Usluga(),"");
    }

    public List<Usluga> getUsluge() {
        return usluge;
    }
    
}
