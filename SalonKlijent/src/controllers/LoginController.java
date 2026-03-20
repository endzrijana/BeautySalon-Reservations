/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import communication.Communication;
import coordinator.Coordinator;
import domain.Kozmeticar;
import forms.LoginForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Ana
 */
public class LoginController {

    private final LoginForm lf;

    public LoginController(LoginForm lf) {
        this.lf = lf;
        addActionListeners();
    }

    private void addActionListeners() {
        lf.loginAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login(e);
            }

            private void login(ActionEvent e) {
                try {
                    String username = lf.getUsernameTxt().getText().trim();
                    String password = String.valueOf(lf.getPassField().getPassword());

                    Communication.getInstance().connection();
                    Kozmeticar loggedIn = Communication.getInstance().login(username, password);
                    if (loggedIn == null) {
                        JOptionPane.showMessageDialog(lf, "Korisnicko ime i sifra nisu ispravni", "Greska", JOptionPane.ERROR_MESSAGE);
                    } else {
                        Coordinator.getInstance().setLoggedIn(loggedIn);
                        JOptionPane.showMessageDialog(lf, "Korisnicko ime i sifra su ispravni", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                        try {
                            Coordinator.getInstance().otvoriMainForm();
                            lf.dispose();

                        } catch (Exception ec) {
                            JOptionPane.showMessageDialog(lf, "Ne moze da se otvori glavna forma i meni", "Greska", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public void openForm() {
        lf.setVisible(true);
    }

}
