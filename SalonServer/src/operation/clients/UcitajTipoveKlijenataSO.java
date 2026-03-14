/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.clients;

import domain.TipKlijenta;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Ana
 */
public class UcitajTipoveKlijenataSO extends AbstractGenericOperation{
    List<TipKlijenta> types;
    @Override
    protected void validate(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param, String key) throws Exception {
        types = broker.getAll(new TipKlijenta(),"");
    }

    public List<TipKlijenta> getTypes() {
        return types;
    }
    
}
