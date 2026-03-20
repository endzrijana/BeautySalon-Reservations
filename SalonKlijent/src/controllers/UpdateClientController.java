/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import communication.Communication;
import communication.FormState;
import coordinator.Coordinator;
import domain.Klijent;
import domain.Kozmeticar;
import domain.TipKlijenta;
import forms.UpdateClientForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Ana
 */
public class UpdateClientController {

    private final UpdateClientForm acf;

    public UpdateClientController(UpdateClientForm acf) {
        this.acf = acf;
        addActionListener();
    }

    public void prepareForm() {
        List<TipKlijenta> tip = communication.Communication.getInstance().loadType();
        acf.getTipKlCombo().removeAllItems();

        for (TipKlijenta tk : tip) {
            acf.getTipKlCombo().addItem(tk);
        }
    }

    public void openForm(FormState state) {
        prepareForm();
        prepareForm(state);
        acf.setVisible(true);
        if (state == FormState.ADD) {
            try {
                Klijent k = communication.Communication.getInstance().kreirajKlijenta();

                if (k != null) {
                    prepareForm();
                    acf.getTxtID().setText(String.valueOf(k.getIdKlijent()));
                    acf.getTxtID().setEditable(false);
                    JOptionPane.showMessageDialog(acf, "Sistem je kreirao klijenta", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    acf.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(acf, "Sistem ne moze da kreira klijenta", "Greška", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(acf, "Sistem ne moze da kreira klijenta", "Greška", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void addActionListener() {
        acf.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }

            private void dodaj(ActionEvent e) {
                try {
                    Klijent k = new Klijent();
                    String ime = acf.getImeTxt().getText().trim();
                    String prezime = acf.getPrezimeTxt().getText().trim();
                    String email = acf.getEmailTxt().getText().trim();
                    TipKlijenta tipKl = (TipKlijenta) acf.getTipKlCombo().getSelectedItem();
                    k.setIdKlijent(Integer.valueOf(acf.getTxtID().getText()));
                    k.setIme(ime);
                    k.setPrezime(prezime);
                    k.setEmail(email);
                    k.setTipKlijenta(tipKl);
                    boolean uspeh = Communication.getInstance().zapamtiKlijenta(k);
                    if (uspeh) {
                        JOptionPane.showMessageDialog(acf, "Sistem je zapamtio klijenta", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                        Coordinator.getInstance().osveziTabelu();
                        acf.dispose();
                    } else {
                        JOptionPane.showMessageDialog(acf, "Sistem ne moze da zapamti klijenta", "Greska", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        acf.azurirajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(true){
                        throw new Exception();
                    }
                    Klijent k = new Klijent();
                    k.setIdKlijent(Integer.valueOf(acf.getTxtID().getText()));
                    k.setIme(acf.getImeTxt().getText().trim());
                    k.setPrezime(acf.getPrezimeTxt().getText().trim());
                    k.setEmail(acf.getEmailTxt().getText().trim());
                    k.setTipKlijenta((TipKlijenta) acf.getTipKlCombo().getSelectedItem());

                    boolean uspeh = Communication.getInstance().zapamtiKlijenta(k);
                    if (uspeh) {
                        JOptionPane.showMessageDialog(acf, "Sistem je zapamtio klijenta", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                        Coordinator.getInstance().osveziTabelu();
                        acf.dispose();
                    }
                   
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(acf,"Sistem ne moze da zapamti klijenta.","Greska", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void prepareForm(FormState state) {
        switch (state) {
            case ADD:
                acf.getAzurirajBtn().setVisible(false);
                acf.getDodajBtn().setVisible(true);
                acf.getDodajBtn().setEnabled(true);

                break;
            case UPDATE:
                acf.getDodajBtn().setVisible(false);
                acf.getAzurirajBtn().setVisible(true);
                acf.getAzurirajBtn().setEnabled(true);
                Klijent k = (Klijent) Coordinator.getInstance().vratiParams("klijent");
                acf.getTxtID().setText(k.getIdKlijent() + "");
                acf.getTxtID().setEditable(false);
                acf.getImeTxt().setText(k.getIme());
                acf.getPrezimeTxt().setText(k.getPrezime());
                acf.getEmailTxt().setText(k.getEmail());
                acf.getTipKlCombo().setSelectedItem(k.getTipKlijenta());

                break;
            default:
                throw new AssertionError();
        }
    }
}
