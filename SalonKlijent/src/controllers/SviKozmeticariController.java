/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import communication.Communication;
import domain.Kozmeticar;
import domain.Sertifikat;
import forms.SviKozmeticariForm;
import forms.model.KozmeticariTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Ana
 */
public class SviKozmeticariController {

    
    private SviKozmeticariForm skf;

    public SviKozmeticariController(SviKozmeticariForm skf) {
        this.skf = skf;
        addActionListener();
    }

    private void addActionListener() {
        skf.obrisiAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = skf.getKozmTable().getSelectedRow();
                if(red==-1){
                    JOptionPane.showMessageDialog(skf, "Nije izabran kozmeticar, sistem ne moze da obrise","Greska",JOptionPane.ERROR);
                }
                else {
                    KozmeticariTableModel ctm = (KozmeticariTableModel) skf.getKozmTable().getModel();
                    Kozmeticar kozm = ctm.getKozmeticari().get(red);
                    try {
                        boolean uspeh = communication.Communication.getInstance().obrisiKozmeticara(kozm);

                        if (uspeh) {
                            JOptionPane.showMessageDialog(skf, "Sistem je obrisao kozmeticara", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(skf, "Sistem nije uspeo da obriše kozmeticara.", "Greška", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (Exception exce) {
                        JOptionPane.showMessageDialog(skf, "Sistem ne moze da nadje kozmeticara po zadatim kriterijumima", "Greska", JOptionPane.ERROR_MESSAGE);

                    }
                }
            }
        });
        
        
    }
    public void openForm() {
        prepareForm();
        skf.setVisible(true);
    }

    private void prepareForm() {
        List<Sertifikat> sertifikati = communication.Communication.getInstance().ucitajSertifikate();
        for (Sertifikat s : sertifikati) {
            skf.getSertifikatCombo().addItem(s);
        }
        List<Kozmeticar> kozmeticari = Communication.getInstance().ucitajKozmeticare();
        KozmeticariTableModel ktm = new KozmeticariTableModel(kozmeticari);
        skf.getKozmTable().setModel(ktm);
    }
}
