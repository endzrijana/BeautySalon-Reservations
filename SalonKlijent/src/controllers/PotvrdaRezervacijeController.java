/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import communication.Communication;
import coordinator.Coordinator;
import domain.Klijent;
import domain.Kozmeticar;
import domain.PotvrdaRezervacije;
import domain.StavkaRezervacije;
import domain.Usluga;
import forms.PotvrdaRezervacijeForm;
import forms.model.ReservationsTableModel;
import forms.model.StavkeRezervacijeTableModel;
import forms.model.StavkeTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Ana
 */
public class PotvrdaRezervacijeController {

    private PotvrdaRezervacijeForm prf;

    public PotvrdaRezervacijeController(PotvrdaRezervacijeForm prf) {
        this.prf = prf;
        addActionListener();
    }

    public void openForm() {
        prepareForm();
        prf.setVisible(true);
    }

    private void prepareForm() {
        List<PotvrdaRezervacije> rezervacije = communication.Communication.getInstance().ucitajPotvrdeRezervacije();
        ReservationsTableModel rtm = new ReservationsTableModel(rezervacije);
        prf.getPotvrdaRezervacijeTable().setModel(rtm);
        prf.getPotvrdeCombo().addItem(null);
        for (PotvrdaRezervacije pr : rezervacije) {
            prf.getPotvrdeCombo().addItem(pr);

        }
        prf.getKlijentCombo().addItem(null);

        List<Klijent> klijenti = Communication.getInstance().loadClients();
        for (Klijent kl : klijenti) {
            prf.getKlijentCombo().addItem(kl);

        }
        prf.getKozmeticarCombo().addItem(null);
        List<Kozmeticar> kozmeticari = Communication.getInstance().ucitajKozmeticare();
        for (Kozmeticar kozmeticar : kozmeticari) {
            prf.getKozmeticarCombo().addItem(kozmeticar);

        }
        prf.getUslugaCombo().addItem(null);

        List<Usluga> usluge = Communication.getInstance().ucitajUsluge();
        for (Usluga usluga : usluge) {
            prf.getUslugaCombo().addItem(usluga);
        }
        prf.getResetujBtn().setEnabled(false);
    }

    private void addActionListener() {
        prf.obrisiAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = prf.getPotvrdaRezervacijeTable().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(prf, "Nije izabrana potvrda rezervacije", "Greska", JOptionPane.ERROR_MESSAGE);
                } else {
                    ReservationsTableModel stm = (ReservationsTableModel) prf.getPotvrdaRezervacijeTable().getModel();
                    PotvrdaRezervacije pr = stm.getRezervacije().get(red);
                    try {
                        boolean uspeh = communication.Communication.getInstance().obrisiPotvrduRezervacije(pr);

                        if (uspeh) {
                            JOptionPane.showMessageDialog(prf, "Sistem je obrisao potvrdu rezervacije", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                            stm.removePotvrdaRezervacije(red);

                        }
                    } catch (Exception exce) {
                        JOptionPane.showMessageDialog(prf, "Sistem nije uspeo da obrise potvrdu rezervacije.", "Greska", JOptionPane.ERROR_MESSAGE);

                    }
                }
            }
        });

        prf.izmeniPotvrduAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int red = prf.getPotvrdaRezervacijeTable().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(prf, "Nije izabran red", "Greska", JOptionPane.ERROR_MESSAGE);
                } else {
                    ReservationsTableModel rtn = (ReservationsTableModel) prf.getPotvrdaRezervacijeTable().getModel();
                    PotvrdaRezervacije prez = rtn.getRezervacije().get(red);
                    PotvrdaRezervacije pr = Communication.getInstance().pretraziPrez(prez);
                    if (pr != null) {
                        JOptionPane.showMessageDialog(prf, "Sistem je nasao potvrdu rezervacije", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                        Coordinator.getInstance().otvoriPromeniPotvrdaForm(pr);

                    } else {
                        JOptionPane.showMessageDialog(prf, "Sistem ne moze da nadje potvrdu rezervacije", "Greska", JOptionPane.ERROR_MESSAGE);
                        prf.dispose();
                    }
                }

            }

        });

        prf.pretraziAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                PotvrdaRezervacije prez = (PotvrdaRezervacije) prf.getPotvrdeCombo().getSelectedItem();
                Klijent k = (Klijent) prf.getKlijentCombo().getSelectedItem();
                Kozmeticar koz = (Kozmeticar) prf.getKozmeticarCombo().getSelectedItem();
                Usluga u = (Usluga) prf.getUslugaCombo().getSelectedItem();

                PotvrdaRezervacije krit = new PotvrdaRezervacije();
                if (prez != null) {
                    krit.setIdPotvrdaRezervacije(prez.getIdPotvrdaRezervacije());
                }
                if (k != null) {
                    krit.setKlijent(k);
                }
                if (koz != null) {
                    krit.setKozmeticar(koz);
                }
                if (u != null) {
                    StavkaRezervacije sr = new StavkaRezervacije();
                    sr.setUsluga(u);
                    List<StavkaRezervacije> stavke = new ArrayList<>();
                    stavke.add(sr);
                    krit.setStavke(stavke);
                }

                List<PotvrdaRezervacije> lista = Communication.getInstance().ucitajPotvrde(krit);
                if (lista != null && !lista.isEmpty()) {
                    JOptionPane.showMessageDialog(prf, "Sistem je nasao potvrde rezervacija po zadatim kriterijumima.", "USPEH", JOptionPane.INFORMATION_MESSAGE);
                    prf.getPotvrdaRezervacijeTable().setModel(new ReservationsTableModel(lista));
                } else {
                    JOptionPane.showMessageDialog(prf, "Sistem ne moze da nadje potvrde rezervacije po zadatim kriterijumima.", "GRESKA", JOptionPane.ERROR_MESSAGE);
                }
                prf.getResetujBtn().setEnabled(true);

            }
        });
       
        prf.resetujPretraguAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                List<PotvrdaRezervacije> rezervacije = communication.Communication.getInstance().ucitajPotvrdeRezervacije();
                ReservationsTableModel rtm = new ReservationsTableModel(rezervacije);
                prf.getPotvrdaRezervacijeTable().setModel(rtm);
                prf.getAzurirajBtn().setEnabled(false);
                prf.getObrisiBtn().setEnabled(false);

            }

        });

    }

}
