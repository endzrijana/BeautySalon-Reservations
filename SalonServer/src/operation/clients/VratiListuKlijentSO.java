/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to update this template
 */
package operation.clients;

import domain.Klijent;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Ana
 */
public class VratiListuKlijentSO extends AbstractGenericOperation{
    List<Klijent> clients;
    @Override
    protected void validate(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param, String key) throws Exception {
        clients = broker.getAll(new Klijent(),"");
    }

    public List<Klijent> getClients() {
        return clients;
    }
    
}
