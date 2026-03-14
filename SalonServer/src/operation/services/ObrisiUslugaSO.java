/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.services;

import domain.Usluga;
import operation.AbstractGenericOperation;

/**
 *
 * @author Ana
 */
public class ObrisiUslugaSO extends AbstractGenericOperation{

    @Override
    protected void validate(Object param) throws Exception {
        if(param==null || !(param instanceof Usluga)){
            throw new Exception("Sistem ne moze da obrise uslugu");
        } }

    @Override
    protected void executeOperation(Object param, String key) throws Exception {
        broker.delete((Usluga) param);
    }
    
}
