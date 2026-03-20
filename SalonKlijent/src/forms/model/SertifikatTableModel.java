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
public class SertifikatTableModel extends AbstractTableModel{
    List<Sertifikat> sertifikati;
    String[] columns = {"ID", "Naziv","Institucija"};

    public SertifikatTableModel(List<Sertifikat> sertifikati) {
        this.sertifikati = sertifikati;
    }

    public List<Sertifikat> getSertifikati() {
        return sertifikati;
    }
    
    
    @Override
    public int getRowCount() {
        return sertifikati.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Sertifikat s = sertifikati.get(rowIndex);
        switch(columnIndex){
            case 0:
                return s.getIdSertifikat();
            case 1:
                return s.getNaziv();
            case 2:
                return s.getInstitucija();
            default:
                return "NA";
        }
    }

    public void dodajSertifikat(Sertifikat s) {
        sertifikati.add(s);
        fireTableDataChanged();
    }
    
}
