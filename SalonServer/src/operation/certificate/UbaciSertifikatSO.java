/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.certificate;

import domain.Sertifikat;
import operation.AbstractGenericOperation;

/**
 *
 * @author Ana
 */
public class UbaciSertifikatSO extends AbstractGenericOperation{

    @Override
    protected void validate(Object param) throws Exception {
        Sertifikat s = (Sertifikat) param;
        if(s.getNaziv().isEmpty() || s.getInstitucija().isEmpty()){
            throw new Exception("Naziv institucije i kursa ne smeju ostati prazni");
        }
    }

    @Override
    protected void executeOperation(Object param, String key) throws Exception {
        broker.add((Sertifikat) param);
    }
    
}
