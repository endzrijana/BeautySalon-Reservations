/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.kozmeticar;

import domain.Kozmeticar;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Ana
 */
public class VratiListuKozmeticarSO extends AbstractGenericOperation{
    List<Kozmeticar> kozmeticari;
    @Override
    protected void validate(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param, String key) throws Exception {
        kozmeticari= broker.getAll(new Kozmeticar(),"");
    }

    public List<Kozmeticar> getKozmeticari() {
        return kozmeticari;
    }
    
    
}
