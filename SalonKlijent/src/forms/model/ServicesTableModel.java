/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forms.model;

import domain.Usluga;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ana
 */
public class ServicesTableModel extends AbstractTableModel {

    List<Usluga> usluge;
    List<Usluga> sveUsluge;
    String[] columns = {"id", "naziv", "cena", "trajanje"};

    public ServicesTableModel(List<Usluga> usluge) {
        this.usluge = usluge;
        this.sveUsluge = usluge;
    }

    @Override
    public int getRowCount() {
        return usluge.size();
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
        Usluga u = usluge.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return u.getIdUsluga();
            case 1:
                return u.getNazivUsluge();
            case 2:
                return u.getCena();
            case 3:
                return u.getTrajanje();
            default:
                return "NA";
        }
    }

    public void pretrazi(double donjaC, double gornjaC, double gornjaT) {
        List<Usluga> filteredList = sveUsluge.stream()
                .filter(k -> (donjaC == 0 || k.getCena() >= donjaC))
                .filter(k -> (gornjaC == 0 || k.getCena() <= gornjaC))
                .filter(k -> (gornjaT == 0 || k.getTrajanje() <= gornjaT))
                .collect(Collectors.toList());
        this.usluge = filteredList;
        fireTableDataChanged();
    }

    public List<Usluga> getUsluge() {
        return usluge;
    }

    public void removeUsluga(int red) {
        this.usluge.remove(red);
        fireTableRowsDeleted(red, red);
    }

    
    
}
