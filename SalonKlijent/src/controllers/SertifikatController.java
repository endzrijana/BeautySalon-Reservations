/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import communication.Communication;
import domain.Sertifikat;
import forms.SertifikatForm;
import forms.model.SertifikatTableModel;
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
public class SertifikatController {

    private SertifikatForm sf;

    public SertifikatController(SertifikatForm sf) {
        this.sf = sf;
        addActionListener();
    }

    private void addActionListener() {
        sf.dodajActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String naziv = sf.getNazivTxt().getText();
                    String inst = sf.getInstitucijaTxt().getText();
                    Sertifikat s = new Sertifikat();
                    s.setNaziv(naziv);
                    s.setInstitucija(inst);
                    boolean uspeh = Communication.getInstance().zapamtiSertifikat(s);
                    if (uspeh) {
                        SertifikatTableModel stm = (SertifikatTableModel) sf.getSertifikatiTable().getModel();
                        stm.dodajSertifikat(s);
                        JOptionPane.showMessageDialog(sf, "Sistem je zapamtio sertifikat", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                        sf.getNazivTxt().setText("");
                        sf.getInstitucijaTxt().setText("");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(sf, "Sistem ne moze da zapamti sertifikat", "Greska", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                    Logger.getLogger(SertifikatController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void openForm() {
        prepareForm();
        sf.setVisible(true);
    }

    private void prepareForm() {
        List<Sertifikat> sertifikati = Communication.getInstance().ucitajSertifikate();
        SertifikatTableModel stm = new SertifikatTableModel(sertifikati);
        sf.getSertifikatiTable().setModel(stm);
    }

}
