/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import communication.Communication;
import communication.FormState;
import domain.PotvrdaRezervacije;
import domain.StavkaRezervacije;
import domain.Usluga;
import forms.PromeniPotvrdaForm;
import forms.model.StavkeTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author Ana
 */
public class PromeniPotvrdaController {

    private PotvrdaRezervacije pr;
    private final PromeniPotvrdaForm sf;

    public PromeniPotvrdaController(PromeniPotvrdaForm sf) {
        this.sf = sf;
        addActionListener();
    }

    private void prepareForm() {
        List<Usluga> usluge = Communication.getInstance().ucitajUsluge();
        for (Usluga usluga : usluge) {
            sf.getUslugaCombo().addItem(usluga);

        }
        sf.getIznosTxt().setText(pr.getUkupanIznos() + "");
        JComboBox<Usluga> uslugaCombo = new JComboBox<>();
        for (Usluga u : usluge) {
            uslugaCombo.addItem(u);
        }

        sf.getStavkeTable().getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(uslugaCombo));
    }

    public void openForm(FormState state) {
        prepareForm();
        sf.setVisible(true);
        if (state == FormState.UPDATE) {
            sf.getPotvrdaRezervacijeIdTxt().setText(pr.getIdPotvrdaRezervacije() + "");
            sf.getPotvrdaRezervacijeIdTxt().setEditable(false);

            StavkeTableModel stm = new StavkeTableModel(pr.getStavke());
            sf.getStavkeTable().setModel(stm);
            stm.addTableModelListener(e -> {
                double ukupno = 0;
                for (StavkaRezervacije s : stm.getStavke()) {
                    ukupno += s.getIznosSt();
                }
                sf.getIznosTxt().setText(String.format("%.2f", ukupno));
                pr.setUkupanIznos(ukupno);
            });
            JComboBox<Usluga> uslugaCombo = new JComboBox<>();
            for (Usluga u : Communication.getInstance().ucitajUsluge()) {
                uslugaCombo.addItem(u);
            }
            sf.getStavkeTable().getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(uslugaCombo));
        }
    }

    private void addActionListener() {

        sf.obrisiBtnAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = sf.getStavkeTable().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(sf, "Nije izabran red");
                } else {
                    StavkeTableModel stm = (StavkeTableModel) sf.getStavkeTable().getModel();
                    stm.removeStavka(red);
                    double ukupno = 0;
                    for (StavkaRezervacije s : stm.getStavke()) {
                        ukupno += s.getIznosSt();
                    }
                    sf.getIznosTxt().setText(String.format("%.2f", ukupno));

                }

            }
        });

        sf.azurirajBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    StavkeTableModel stm = (StavkeTableModel) sf.getStavkeTable().getModel();
                    List<StavkaRezervacije> stavke = stm.getStavke();
                    pr.setUkupanIznos(Double.parseDouble(sf.getIznosTxt().getText()));
                    pr.setStavke(stavke);
                    Communication.getInstance().zapamtiPotvrduRezervacije(pr);
                    JOptionPane.showMessageDialog(sf, "Sistem je zapamtio potvrdu rezervacije", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    sf.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(sf, "Sistem ne moze da zapamti potvrdu rezervacije", "Greska", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        sf.dodajBtnActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usluga u = (Usluga) sf.getUslugaCombo().getSelectedItem();
                StavkaRezervacije sr = new StavkaRezervacije();
                sr.setUsluga(u);
                sr.setCena(u.getCena());
                sr.setPotvrdaRezervacije(pr);

                int popust = pr.getKlijent().getTipKlijenta().getPopust();
                try {
                    sr.setPopust(popust);
                    double iznos = u.getCena() - (u.getCena() * popust / 100.0);
                    sr.setIznosSt(iznos);
                    StavkeTableModel sm = (StavkeTableModel) sf.getStavkeTable().getModel();
                    sm.dodajStavku(sr);
                    List<StavkaRezervacije> stavke = sm.getStavke();
                    double izn = 0;
                    for (StavkaRezervacije s : stavke) {
                        izn += s.getIznosSt();
                    }
                    sf.getIznosTxt().setText(izn + "");
                    pr.setUkupanIznos(izn);
                } catch (Exception ex) {
                }
            }

        });

    }

    private void prepareForm(FormState state) {
        List<Usluga> usluge = Communication.getInstance().ucitajUsluge();
        for (Usluga usluga : usluge) {
            sf.getUslugaCombo().addItem(usluga);
        }

    }

    public void setPr(PotvrdaRezervacije pr) {
        this.pr = pr;
    }

}
