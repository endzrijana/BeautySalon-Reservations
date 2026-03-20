/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forms.model;

import domain.Kozmeticar;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ana
 */
public class KozmeticariTableModel extends AbstractTableModel{
    List<Kozmeticar> kozmeticari ;
    List<Kozmeticar> sviKozmeticari;

    public KozmeticariTableModel(List<Kozmeticar> kozmeticari) {
        this.kozmeticari = kozmeticari;
    }
    String[] columns = {"ID","Ime i prezime","Datum zaposlenja", "Korisnicko ime", "Sertifikat"};
    @Override
    public int getRowCount() {
        return kozmeticari.size();
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
        Kozmeticar k = kozmeticari.get(rowIndex);
        switch(columnIndex){
            case 0 :
                return k.getIdKozmeticar();
            case 1:
                return k.getImePrezime();
            case 2:
                return k.getDatumZaposlenja();
            case 3:
                return k.getKorisnickoIme();
            case 4:
                return k.getSertifikat();
            default:
                return "NA";
        }
    }

    public List<Kozmeticar> getKozmeticari() {
        return kozmeticari;
    }
    
    
}
