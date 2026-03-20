/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import communication.Communication;
import coordinator.Coordinator;
import domain.Usluga;
import forms.ServicesViewForm;
import forms.model.ServicesTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Ana
 */
public class ServicesViewController {

    private ServicesViewForm svf;
    private List<Usluga> usluge = Communication.getInstance().ucitajUsluge();

    public ServicesViewController(ServicesViewForm svf) {
        this.svf = svf;
    }

    public void openForm() {
        prepareForm();
        svf.setVisible(true);
        addActionListener();
    }

    private void prepareForm() {
        ServicesTableModel stm = new ServicesTableModel(usluge);
        svf.getSveUslugeTable().setModel(stm);
    }

    private void addActionListener() {
        svf.pretraziAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double donjaC = svf.getDonjaCenaTxt().getText().isEmpty() ? 0 : Double.parseDouble(svf.getDonjaCenaTxt().getText().trim());
                double gornjaC = svf.getGornjaGranicaTxt().getText().isEmpty() ? 0 : Double.parseDouble(svf.getGornjaGranicaTxt().getText().trim());
                double gornjaT = svf.getGornjaTrajanjeTxt().getText().isEmpty() ? 0 : Double.parseDouble(svf.getGornjaTrajanjeTxt().getText().trim());
                ServicesTableModel stm = (ServicesTableModel) svf.getSveUslugeTable().getModel();
                stm.pretrazi(donjaC, gornjaC, gornjaT);
            }
        });

        svf.resetujAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                svf.getDonjaCenaTxt().setText("");
                svf.getGornjaGranicaTxt().setText("");
                svf.getGornjaTrajanjeTxt().setText("");
                ServicesTableModel stm = new ServicesTableModel(usluge);
                svf.getSveUslugeTable().setModel(stm);
            }
        });

        svf.azurirajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = svf.getSveUslugeTable().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(svf, "Nije izabrana usluga", "Greska", JOptionPane.ERROR_MESSAGE);
                } else {
                    ServicesTableModel ctm = (ServicesTableModel) svf.getSveUslugeTable().getModel();
                    Usluga u = ctm.getUsluge().get(red);
                    Coordinator.getInstance().dodajParams("usluga", u);
                    Coordinator.getInstance().openUpdateServiceForm();
                }
            }
        });

        svf.obrisiAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = svf.getSveUslugeTable().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(svf, "Nije izabrana usluga", "Greska", JOptionPane.ERROR_MESSAGE);
                } else {
                        try {
                            ServicesTableModel stm = (ServicesTableModel) svf.getSveUslugeTable().getModel();
                            Usluga u = stm.getUsluge().get(red);
                            boolean uspeh = communication.Communication.getInstance().obrisiUslugu(u);
                            try {
                                if(uspeh){
                                    JOptionPane.showMessageDialog(svf, "Usluga je uspesno obrisana","Uspeh",JOptionPane.INFORMATION_MESSAGE);
                                    stm.removeUsluga(red);
                                }
                            } catch (Exception ex) {
                                Logger.getLogger(ServicesViewController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } catch (Exception ex) {
                        Logger.getLogger(ServicesViewController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

    }

}
