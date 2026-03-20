/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forms.model;

import domain.Sertifikat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ana
 */
public class CertificateTableModel extends AbstractTableModel{
    
    List<Sertifikat> sertifikati;
    List<Sertifikat> sviSertifikati;

    public CertificateTableModel(List<Sertifikat> sertifikati) {
        this.sertifikati = sertifikati;
        this.sviSertifikati = sertifikati;
    }
    
    String[] columns = {"id","naziv","instititucija"};

    @Override
    public int getRowCount() {
        return sertifikati.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Sertifikat s = sertifikati.get(rowIndex);
        switch(columnIndex){
            case 0 :
                return s.getIdSertifikat();
            case 1:
                return s.getNaziv();
            case 2:
                return s.getInstitucija();
            default:
                return "NA";
        }
    }
    
}
