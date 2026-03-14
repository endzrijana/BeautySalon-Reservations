/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Ana
 */
public class Configuration {
    private static Configuration instanca;
    private Properties configuration;
    private Configuration(){
        try {
            configuration = new Properties();
            configuration.load(new FileInputStream("config\\config.properties"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static synchronized Configuration getInstanca(){
        if(instanca == null){
            instanca = new Configuration();
        }
        return instanca;
    }

    public String getProperty(String key){
        return configuration.getProperty(key, "n/a");
    }
    
    public void setProperty(String key, String value){
        configuration.setProperty(key, value);
    }
    
    public void sacuvajIzmene(){
        try {
            configuration.store(new FileOutputStream("config\\config.properties"), null);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
