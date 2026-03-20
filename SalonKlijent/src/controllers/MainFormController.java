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
import domain.TipKlijenta;
import domain.Usluga;
import forms.MainForm;
import forms.model.StavkeRezervacijeTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Ana
 */
public class MainFormController {

    private final MainForm mf;
    private PotvrdaRezervacije pr;

    public MainFormController(MainForm mf) {
        this.mf = mf;
        addActionListeners();
    }

    private void prepareForm() {
        mf.getDatumTxt().setEditable(false);
        mf.getKlijentCombo().setEnabled(false);
        mf.getKozmeticarCombo().setEnabled(false);
        mf.getUslugeCombo().setEnabled(false);
        mf.getDodajStavkuBtn().setEnabled(false);
        mf.getObrisiStavkuBtn().setEnabled(false);
        mf.getZapamtiPotvrduBtn().setEnabled(false);
        mf.getIzmeniPotvrduBtn().setEnabled(false);
        popuniCombo();
    }

    public void openForm() {
        prepareForm();
        Kozmeticar k = coordinator.Coordinator.getInstance().getLoggedIn();
        String imePrezime = k.getImePrezime();
        mf.setVisible(true);
        mf.getUlogovaniTxt().setText(imePrezime);
        mf.getPopustTxt().setEditable(false);
        mf.getCenaTxt().setEditable(false);
        mf.getIznosTxt().setEditable(false);
        mf.getUkupanIznosTxt().setEditable(false);
        mf.getIdTxt().setEditable(false);
        List<StavkaRezervacije> stavke = new ArrayList<>();
        StavkeRezervacijeTableModel srtm = new StavkeRezervacijeTableModel(stavke);
        mf.getStavkeTable().setModel(srtm);

    }

    private void addActionListeners() {
        mf.dodajStavkuAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }

            private void dodaj(ActionEvent e) {
                try {
                    Klijent k = (Klijent) mf.getKlijentCombo().getSelectedItem();
                    int popust = Integer.parseInt(mf.getPopustTxt().getText());
                    double cena = Double.parseDouble(mf.getCenaTxt().getText());
                    double iznosSt = Double.parseDouble(mf.getIznosTxt().getText());
                    Usluga u = (Usluga) mf.getUslugeCombo().getSelectedItem();
                    StavkaRezervacije sr = new StavkaRezervacije();
                    sr.setPopust(popust);
                    sr.setCena(cena);
                    sr.setIznosSt(iznosSt);
                    sr.setUsluga(u);
                    StavkeRezervacijeTableModel stm = (StavkeRezervacijeTableModel) mf.getStavkeTable().getModel();
                    stm.dodajStavku(sr);
                    double ukupno = 0;
                    for (StavkaRezervacije s : stm.getStavke()) {
                        ukupno += s.getIznosSt();
                    }
                    mf.getUkupanIznosTxt().setText(String.format("%.2f", ukupno));

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        mf.getKlijentCombo().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Klijent k = (Klijent) mf.getKlijentCombo().getSelectedItem();
                if (k != null && k.getTipKlijenta() != null) {
                    mf.getPopustTxt().setText(String.valueOf(k.getTipKlijenta().getPopust()));

                    try {
                        double cena = Double.parseDouble(mf.getCenaTxt().getText());
                        int popust = k.getTipKlijenta().getPopust();
                        double iznos = cena - (cena * popust / 100.0);
                        mf.getIznosTxt().setText(String.format("%.2f", iznos));
                    } catch (Exception ex) {
                    }
                }
            }
        });
        mf.getUslugeCombo().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usluga u = (Usluga) mf.getUslugeCombo().getSelectedItem();
                if (u != null) {
                    mf.getCenaTxt().setText(String.valueOf(u.getCena()));
                    try {
                        double cena = u.getCena();
                        int popust = Integer.parseInt(mf.getPopustTxt().getText());
                        double iznos = cena - (cena * popust / 100.0);
                        mf.getIznosTxt().setText(String.format("%.2f", iznos));
                    } catch (Exception ex) {
                    }
                }
            }
        });

        mf.obrisiStavkuAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = mf.getStavkeTable().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(mf, "Nije izabrana ukloni stavka", "Greska", JOptionPane.ERROR_MESSAGE);
                } else {
                    StavkeRezervacijeTableModel sm = (StavkeRezervacijeTableModel) mf.getStavkeTable().getModel();
                    StavkaRezervacije sr = sm.getStavke().get(red);
                    sm.removeStavkaRezervacije(red);
                    double ukupno = 0;
                    for (StavkaRezervacije s : sm.getStavke()) {
                        ukupno += s.getIznosSt();
                    }
                    mf.getUkupanIznosTxt().setText(String.format("%.2f", ukupno));
                }

            }
        });
        mf.kreirajPotvrduAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               pr = Communication.getInstance().kreirajPotvrdaRezervacije();
                if (pr == null) {
                    JOptionPane.showMessageDialog(mf, "Sistem ne moze da kreira potvrdu rezervacije", "Greška", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(mf, "Sistem je kreirao potvrdu rezervacije", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    mf.getIdTxt().setText(pr.getIdPotvrdaRezervacije() + "");
                    mf.getDatumTxt().setEditable(true);
                    mf.getKlijentCombo().setEnabled(true);
                    mf.getKozmeticarCombo().setEnabled(true);
                    mf.getUslugeCombo().setEnabled(true);
                    mf.getDodajStavkuBtn().setEnabled(true);
                    mf.getObrisiStavkuBtn().setEnabled(true);
                    mf.getZapamtiPotvrduBtn().setEnabled(true);

                }
            }
        });

        mf.zapamtiPotvrduAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Double ukupanIznos = Double.parseDouble(mf.getUkupanIznosTxt().getText());
                    Klijent kl = (Klijent) mf.getKlijentCombo().getSelectedItem();
                    Kozmeticar ko = (Kozmeticar) mf.getKozmeticarCombo().getSelectedItem();
                    String datumStr = mf.getDatumTxt().getText();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date datum = sdf.parse(datumStr);
                    pr.setKlijent(kl);
                    pr.setKozmeticar(ko);
                    pr.setDatumRezervacije(datum);
                    pr.setUkupanIznos(ukupanIznos);

                    StavkeRezervacijeTableModel srtm = (StavkeRezervacijeTableModel) mf.getStavkeTable().getModel();
                    List<StavkaRezervacije> stavke = srtm.getStavke();
                    pr.setStavke(stavke);

                    boolean uspeh = Communication.getInstance().zapamtiPotvrduRezervacije(pr);
                    if (uspeh) {
                        JOptionPane.showMessageDialog(mf, "Sistem je zapamtio potvrdu rezervacije", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(mf, "Sistem ne moze da zapamti potvrdu ", "Greska", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(mf, "Sistem ne moze da zapamti potvrdu ", "Greska", JOptionPane.ERROR_MESSAGE);
                }
                mf.getIzmeniPotvrduBtn().setEnabled(true);

            }
        });
        
        mf.izmeniPotvrduActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Coordinator.getInstance().otvoriPromeniPotvrdaForm(pr);
            }
        });
        
    }

    private void popuniCombo() {
        List<Klijent> klijenti = Communication.getInstance().loadClients();
        mf.getKlijentCombo().addItem(null);
        for (Klijent k : klijenti) {
            mf.getKlijentCombo().addItem(k);
        }
        mf.getKlijentCombo().setSelectedItem(null);
        List<Usluga> usluge = Communication.getInstance().ucitajUsluge();
        mf.getUslugeCombo().addItem(null);
        
        for (Usluga usluga : usluge) {
            mf.getUslugeCombo().addItem(usluga);
        }
        mf.getUslugeCombo().setSelectedItem(null);
        
        mf.getKozmeticarCombo().addItem(null);
        List<Kozmeticar> kozmeticari = Communication.getInstance().ucitajKozmeticare();
        for (Kozmeticar koz : kozmeticari) {
            mf.getKozmeticarCombo().addItem(koz);
        }
        mf.getKozmeticarCombo().setSelectedItem(null);
    }

}
