/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import communication.Communication;
import communication.FormState;
import coordinator.Coordinator;
import domain.Usluga;
import forms.UpdateServiceForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Ana
 */
public class UpdateServiceController {

    private UpdateServiceForm usf;

    public UpdateServiceController(UpdateServiceForm usf) {
        this.usf = usf;
        addActionListener();
        usf.setVisible(true);
    }


    public void openForm(FormState formState) {
        
        switch (formState) {
            case ADD:
                usf.getAzurirajBtn().setVisible(false);
                usf.getDodajBtn().setVisible(true);
                usf.getDodajBtn().setEnabled(true);

                break;
            case UPDATE:
                usf.getAzurirajBtn().setVisible(true);
                usf.getDodajBtn().setVisible(false);
                usf.getAzurirajBtn().setEnabled(true);
                Usluga u = (Usluga) Coordinator.getInstance().vratiParams("usluga");
                usf.getNazivTxt().setText(u.getNazivUsluge());
                usf.getCenaTxt().setText(u.getCena() + "");
                usf.getTrajanjeTxt().setText(u.getTrajanje() + "");

                break;
        }
    }

    private void addActionListener() {
        usf.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String naziv = usf.getNazivTxt().getText().trim();
                    double cena = Double.parseDouble(usf.getCenaTxt().getText().trim());
                    int trajanje = Integer.parseInt(usf.getTrajanjeTxt().getText().trim());

                    Usluga u = new Usluga();
                    u.setNazivUsluge(naziv);
                    u.setCena(cena);
                    u.setTrajanje(trajanje);

                    boolean uspeh = Communication.getInstance().kreirajUslugu(u);
                    if (uspeh) {
                        JOptionPane.showMessageDialog(usf, "Sistem je zapamtio uslugu", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                        usf.dispose();
                    } else {
                        JOptionPane.showMessageDialog(usf, "Sistem ne moze da zapamti uslugu", "Greska", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(UpdateServiceController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        usf.azurirajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Usluga usl = (Usluga) Coordinator.getInstance().vratiParams("usluga");
                    String naziv = usf.getNazivTxt().getText();
                    double cena = Double.parseDouble(usf.getCenaTxt().getText());
                    int trajanje = Integer.parseInt(usf.getTrajanjeTxt().getText());
                    Usluga u = new Usluga();
                    u.setID(usl.getIdUsluga());
                    u.setNazivUsluge(naziv);
                    u.setCena(cena);
                    u.setTrajanje(trajanje);
                    boolean uspeh = Communication.getInstance().azurirajUslugu(u);
                    if (uspeh) {
                        JOptionPane.showMessageDialog(usf, "Usluga uspesno azurirana", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(usf, "Usluga nije uspesno azurirana", "GRESKA", JOptionPane.ERROR_MESSAGE);
                    }
                    usf.dispose();

                } catch (Exception ex) {
                    Logger.getLogger(UpdateServiceController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

}
