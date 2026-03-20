/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forms.model;

import domain.StavkaRezervacije;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ana
 */
public class StavkeRezervacijeTableModel extends AbstractTableModel{
    List<StavkaRezervacije> stavke;
    String[] columns = {"id","popust","cena","iznosSt", "usluga"};
    public StavkeRezervacijeTableModel(List<StavkaRezervacije> stavke) {
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
        switch(columnIndex){
            case 0 :
                return sr.getRedniBroj();
            case 1:
                return sr.getPopust();
            case 2:
                return sr.getCena();
            case 3:
                return sr.getIznosSt();
            case 4:
                return sr.getUsluga();
            default:
                return "NA";
                
        }
    }

    public List<StavkaRezervacije> getStavke() {
        return stavke;
    }

    public void removeStavkaRezervacije(int red) {
        stavke.remove(red);
        fireTableDataChanged();
    }

    public void dodajStavku(StavkaRezervacije sr) {
        int rb= stavke.size();
        sr.setID(rb+1);
        stavke.add(sr);
        fireTableDataChanged();
    }
    
    
    
}
