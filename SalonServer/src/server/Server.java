package server;

import java.util.List;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import threads.ClientHandler;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Ana
 */
public class Server extends Thread {

    boolean kraj = false;
    ServerSocket serverSocket;
    List<ClientHandler> clients = new ArrayList<>();

    public Server() {
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(9000);

            while (!kraj) {
                Socket s = serverSocket.accept();
                System.out.println("Klijent je povezan");

                ClientHandler ch = new ClientHandler(s);
                clients.add(ch);
                ch.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void zaustaviServer() {
        kraj = true;
        try {
            for (ClientHandler client : clients) {
                client.prekini();
            }
            serverSocket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
