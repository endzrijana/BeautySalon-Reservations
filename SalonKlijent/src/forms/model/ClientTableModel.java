/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forms.model;

import domain.Klijent;
import domain.TipKlijenta;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ana
 */
public class ClientTableModel extends AbstractTableModel {

    List<Klijent> clients;
    List<Klijent> allClients;
    String[] columns = {"id", "ime", "prezime", "email", "tip klijenta"};

    public ClientTableModel(List<Klijent> clients) {
        this.clients = clients;
        this.allClients = clients;
    }

    @Override
    public int getRowCount() {
        return clients.size();
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
        Klijent k = clients.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return k.getIdKlijent();
            case 1:
                return k.getIme();
            case 2:
                return k.getPrezime();
            case 3:
                return k.getEmail();
            case 4:
                return k.getTipKlijenta();
            default:
                return "NA";
        }
    }

    public List<Klijent> getClients() {
        return clients;
    }

    public void removeClient(int red) {
        this.clients.remove(red);
        fireTableRowsDeleted(red, red);
    }

    public void setClients(List<Klijent> clients) {
        this.allClients = clients;
        this.clients = clients;
        fireTableDataChanged();
    }

    public void updateClient(int row, Klijent updatedClient) {
        this.clients.set(row, updatedClient);
        fireTableRowsUpdated(row, row);
    }

    public void pretrazi(String ime, String prezime, String email, TipKlijenta tipKl) {
        List<Klijent> filteredList = allClients.stream()
                .filter(k -> (ime == null || ime.isEmpty() || k.getIme().toLowerCase().contains(ime.toLowerCase())))
                .filter(k -> (prezime == null || prezime.isEmpty() || k.getPrezime().toLowerCase().contains(prezime.toLowerCase())))
                .filter(k -> (email == null || email.isEmpty() || k.getEmail().toLowerCase().contains(email.toLowerCase())))
                .filter(k -> (tipKl == null || k.getTipKlijenta().equals(tipKl)))
                .collect(Collectors.toList());
        this.clients=filteredList;
        fireTableDataChanged();

    }

}
