/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forms.model;

import domain.Klijent;
import domain.PotvrdaRezervacije;
import domain.TipKlijenta;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ana
 */
public class ReservationsTableModel extends AbstractTableModel {

    List<PotvrdaRezervacije> rezervacije;
    List<PotvrdaRezervacije> sveRezervacije;
    String[] columns = {"id", "datum rezervacije", "ukupan iznos", "kozmeticar", "klijent"};

    public ReservationsTableModel(List<PotvrdaRezervacije> rezervacije) {
        this.rezervacije = rezervacije;
        this.sveRezervacije = rezervacije;
    }

    @Override
    public int getRowCount() {
        return rezervacije.size();
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
        PotvrdaRezervacije r = rezervacije.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return r.getIdPotvrdaRezervacije();
            case 1:
                return r.getDatumRezervacije();
            case 2:
                return r.getUkupanIznos();
            case 3:
                return r.getKozmeticar();
            case 4:
                return r.getKlijent();
            default:
                return "NA";
        }
    }

    public List<PotvrdaRezervacije> getRezervacije() {
        return rezervacije;
    }

    public void removeReservation(int red) {

    }

    public void setReservation(List<PotvrdaRezervacije> rezervacije) {
        this.rezervacije = rezervacije;
        fireTableDataChanged();
    }

    public void updateReservation(int row, PotvrdaRezervacije updatedReservation) {
        this.rezervacije.set(row, updatedReservation);
        fireTableRowsUpdated(row, row);
    }

    public void setRezervacije(List<PotvrdaRezervacije> rezervacije) {
        this.rezervacije = rezervacije;
        this.sveRezervacije = rezervacije;
        fireTableDataChanged();

    }

    public void removePotvrdaRezervacije(int red) {
        this.rezervacije.remove(red);
        fireTableRowsDeleted(red, red);
    }

    public List<PotvrdaRezervacije> getSveRezervacije() {
        return sveRezervacije;
    }

   

}
