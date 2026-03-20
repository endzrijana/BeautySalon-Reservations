/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import communication.Communication;
import coordinator.Coordinator;
import domain.Klijent;
import domain.TipKlijenta;
import forms.ClientViewForm;
import forms.model.ClientTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Ana
 */
public class ClientViewController {

    private ClientViewForm cvf;

    public ClientViewController(ClientViewForm cvf) {
        this.cvf = cvf;
        addActionListener();
    }

    public void openForm() {
        prepareForm();
        cvf.setVisible(true);
    }

    public void prepareForm() {
        List<Klijent> clients = communication.Communication.getInstance().loadClients();
        ClientTableModel ctm = new ClientTableModel(clients);
        cvf.getClientsTable().setModel(ctm);
        List<TipKlijenta> tipovi = communication.Communication.getInstance().loadType();
        cvf.getTipCombo().removeAllItems();
        cvf.getTipCombo().addItem(null);
        for (TipKlijenta tk : tipovi) {
            cvf.getTipCombo().addItem(tk);
        }
       cvf.getKlijentCombo().addItem(null);
        for (Klijent kl : clients) {
            cvf.getKlijentCombo().addItem(kl);
        }
        cvf.getUpdateBtn().setEnabled(false);
        cvf.getResetujBtn().setEnabled(false);
        cvf.getDeleteBtn().setEnabled(false);

    }

    private void addActionListener() {
        cvf.addDeleteBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = cvf.getClientsTable().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(cvf, "Nije izabran red", "Greska", JOptionPane.ERROR_MESSAGE);
                } else {
                    ClientTableModel ctm = (ClientTableModel) cvf.getClientsTable().getModel();
                    Klijent k = ctm.getClients().get(red);
                    try {
                        if (k != null) {
                            JOptionPane.showMessageDialog(cvf, "Sistem je nasao klijenta", "USPEH", JOptionPane.INFORMATION_MESSAGE);

                            boolean uspeh = communication.Communication.getInstance().obrisiKlijenta(k);
                            if (uspeh) {
                                JOptionPane.showMessageDialog(cvf, "Sistem je obrisao klijenta", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                                ctm.removeClient(red);
                            } else {
                                JOptionPane.showMessageDialog(cvf, "Sistem ne moze da obrise klijenta.", "Greska", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(cvf, "Sistem ne moze da nadje klijenta", "Greska", JOptionPane.ERROR_MESSAGE);
                        }

                    } catch (Exception exce) {
                        exce.printStackTrace();
                    }
                }
            }
        });
        cvf.addUpdateBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = cvf.getClientsTable().getSelectedRow();

                if (red == -1) {
                    JOptionPane.showMessageDialog(cvf, "Nije izabran red", "Greska", JOptionPane.ERROR_MESSAGE);
                } else {
                    ClientTableModel ctm = (ClientTableModel) cvf.getClientsTable().getModel();
                    Klijent kli = ctm.getClients().get(red);
                    Klijent k = Communication.getInstance().pretraziKlijenta(kli);
                    if (k != null) {
                        JOptionPane.showMessageDialog(cvf, "Sistem je nasao klijenta", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                        Coordinator.getInstance().dodajParams("klijent", k);
                        Coordinator.getInstance().otvoriUpdateClientForm();
                    } else {
                        JOptionPane.showMessageDialog(cvf, "Sistem ne moze da nadje klijenta", "Greska", JOptionPane.ERROR_MESSAGE);
                        cvf.dispose();
                    }
                }
            }
        });

        cvf.addPretraziBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cvf.getResetujBtn().setEnabled(true);

                Klijent kl = (Klijent) cvf.getKlijentCombo().getSelectedItem();
                TipKlijenta tk = (TipKlijenta) cvf.getTipCombo().getSelectedItem();

                Klijent krit = new Klijent();
                if (kl != null) {
                    krit = kl;
                }
                if (tk != null) {
                    krit.setTipKlijenta(tk);
                }
                List<Klijent> lista = Communication.getInstance().vratiKlijenteKrit(krit);

                if (lista != null && !lista.isEmpty()) {
                    JOptionPane.showMessageDialog(cvf, "Sistem je nasao klijente po zadatim kriterijumima.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    cvf.getClientsTable().setModel(new ClientTableModel(lista));
                    cvf.getDeleteBtn().setEnabled(true);
                    cvf.getUpdateBtn().setEnabled(true);

                } else {
                    JOptionPane.showMessageDialog(cvf, "Sistem ne moze da nadje klijente po zadatim kriterijumima.", "GRESKA", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cvf.addResetujBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cvf.getPretraziBtn().setEnabled(true);
                cvf.getKlijentCombo().setSelectedItem(null);
                cvf.getTipCombo().setSelectedItem(null);

                Coordinator.getInstance().osveziTabelu();
            }
        });
    }

    public ClientViewForm getCvf() {
        return cvf;
    }

}
