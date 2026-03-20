/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import coordinator.Coordinator;
import javax.swing.UIManager;

/**
 *
 * @author Ana
 */
public class Main {

    public static void main(String[] args) {

        UIManager.put("Panel.background", new java.awt.Color(255, 150, 200));
        UIManager.put("OptionPane.background", new java.awt.Color(255, 150, 200));
        UIManager.put("Button.background", new java.awt.Color(173, 216, 255));
        UIManager.put("Button.foreground", java.awt.Color.WHITE);
        UIManager.put("Table.background", new java.awt.Color(255, 160, 210));
        UIManager.put("Table.gridColor", new java.awt.Color(173, 216, 255));
        UIManager.put("Table.selectionBackground", new java.awt.Color(173, 216, 255));
        UIManager.put("TableHeader.background", new java.awt.Color(255, 20, 147));
        UIManager.put("TableHeader.foreground", java.awt.Color.WHITE);
        UIManager.put("TextField.background", new java.awt.Color(255, 160, 210));
        UIManager.put("TextArea.background", new java.awt.Color(255, 160, 210));
        UIManager.put("PasswordField.background", new java.awt.Color(255, 160, 210));
        UIManager.put("ComboBox.background", new java.awt.Color(255, 150, 200));
        UIManager.put("ScrollPane.background", new java.awt.Color(255, 150, 200));
        UIManager.put("Label.foreground", java.awt.Color.WHITE);
        UIManager.put("CheckBox.background", new java.awt.Color(255, 150, 200));
        UIManager.put("RadioButton.background", new java.awt.Color(255, 150, 200));
        Coordinator.getInstance().otvoriLoginFormu();
    }

}
