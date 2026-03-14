/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.certificate;

import domain.Sertifikat;
import java.util.List;
import operation.AbstractGenericOperation;

/**
 *
 * @author Ana
 */
public class VratiListuSertifikatSO extends AbstractGenericOperation{
    List<Sertifikat> sertifikati;
    @Override
    protected void validate(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param, String key) throws Exception {
        sertifikati = broker.getAll(new Sertifikat(),"");
    }

    public List<Sertifikat> getSertifikati() {
        return sertifikati;
    }
    
    
    
}
