/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forms.model;

import domain.StavkaRezervacije;
import domain.Usluga;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ana
 */
public class StavkeTableModel extends AbstractTableModel {

    List<StavkaRezervacije> stavke;
    String[] columns = {"popust", "cena", "iznosSt", "rezervacija", "usluga"};

    public StavkeTableModel(List<StavkaRezervacije> stavke) {
        this.stavke = stavke;
    }

    @Override
    public int getRowCount() {
        return stavke.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column]; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StavkaRezervacije sr = stavke.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return sr.getPopust();
            case 1:
                return sr.getCena();
            case 2:
                return sr.getIznosSt();
            case 3:
                return sr.getPotvrdaRezervacije();
            case 4:
                return sr.getUsluga();
            default:
                return "NA";

        }
    }

    public List<StavkaRezervacije> getStavke() {
        return stavke;
    }

    public void dodajStavku(StavkaRezervacije sr) {
        stavke.add(sr);
        fireTableDataChanged();
    }

    public void removeStavka(int red) {
        stavke.remove(red);
        fireTableDataChanged();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 4:
                return true;
            default:
                return false;
        }
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        StavkaRezervacije sr = stavke.get(rowIndex);
        switch (columnIndex) {
            case 4:
                if (value instanceof Usluga) {
                    Usluga u = (Usluga) value;
                    sr.setUsluga(u);
                    sr.setCena(u.getCena());
                    int popust = sr.getPopust();
                    double iznos = u.getCena() - (u.getCena() * popust / 100.0);
                    sr.setIznosSt(iznos);
                }
                break;
        }
        fireTableRowsUpdated(rowIndex, rowIndex);
    }
}
