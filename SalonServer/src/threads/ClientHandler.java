/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threads;

import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.Sender;
import controller.Controller;
import domain.Klijent;
import domain.Kozmeticar;
import domain.PotvrdaRezervacije;
import domain.Sertifikat;
import domain.StavkaRezervacije;
import domain.TipKlijenta;
import domain.Usluga;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ana
 */
public class ClientHandler extends Thread {

    Socket socket;
    Sender sender;
    Receiver receiver;
    boolean kraj = false;

    public ClientHandler(Socket socket) {
        this.socket = socket;
        sender = new Sender(socket);
        receiver = new Receiver(socket);
    }

    @Override
    public void run() {
        while (!kraj) {
            try {
                Request request = (Request) receiver.receive();
                Response response = new Response();
                switch (request.getOperation()) {
                    case LOGIN:
                        Kozmeticar k = (Kozmeticar) request.getParam();
                        k = Controller.getInstance().login(k);
                        response.setResponse(k);
                        break;
                    case LOAD_CLIENTS:
                        List<Klijent> clients = Controller.getInstance().loadClients();
                        response.setResponse(clients);
                        break;
                    case DELETE_CLIENT:
                        try {
                        Klijent kl = (Klijent) request.getParam();
                        Controller.getInstance().obrisiKlijenta(kl);
                        response.setResponse(null);
                    } catch (Exception exc) {
                        response.setResponse(exc);
                    }
                    break;
                    case LOAD_TYPE:
                        List<TipKlijenta> types = Controller.getInstance().loadTypes();
                        response.setResponse(types);
                        break;
                    case ADD_CLIENT:
                        try {
                        Klijent kli = (Klijent) request.getParam();
                        Controller.getInstance().zapamtiKlijenta(kli);
                        response.setResponse(null);
                    } catch (Exception exc) {
                        exc.printStackTrace();
                        response.setResponse(exc.getMessage());
                    }
                    break;
                    case CREATE_CLIENT:
                        Klijent klij = Controller.getInstance().kreirajKlijenta();
                        response.setResponse(klij);
                        break;
                    case LOAD_RESERVATIONS:
                        List<PotvrdaRezervacije> rezervacije = Controller.getInstance().ucitajRezervacije();
                        response.setResponse(rezervacije);
                        break;
                    
                    case DELETE_CONFIRMATION:
                        try {
                        PotvrdaRezervacije pre = (PotvrdaRezervacije) request.getParam();
                        Controller.getInstance().obrisiPotvrduRezervacije(pre);
                        response.setResponse(null);
                    } catch (Exception exc) {
                        exc.printStackTrace();
                        response.setResponse(exc);
                    }
                    break;
                    case LOAD_SERVICES:
                        List<Usluga> usluge = Controller.getInstance().ucitajUsluge();
                        response.setResponse(usluge);
                        break;
                    case LOAD_COSMETICIAN:
                        List<Kozmeticar> kozmeticari = Controller.getInstance().ucitajKozmeticare();
                        response.setResponse(kozmeticari);
                        break;
                    case CREATE_CONFIRMATION:
                        PotvrdaRezervacije por = Controller.getInstance().kreirajPotvrdu();
                        response.setResponse(por);
                        break;
                    case UPDATE_CONFIRMATION:
                        try {
                        PotvrdaRezervacije prez = (PotvrdaRezervacije) request.getParam();
                        Controller.getInstance().promeniPotvrdu(prez);
                        response.setResponse(null);
                    } catch (Exception exc) {
                        exc.printStackTrace();
                        response.setResponse(exc.getMessage());
                    }
                    break;
                    case DELETE_ITEM:
                        try {
                        StavkaRezervacije str = (StavkaRezervacije) request.getParam();
                        Controller.getInstance().obrisiStavkuRezervacije(str);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                        response.setResponse(exception.getMessage());

                    }
                    break;
                    case ADD_SERVICE:
                         try {
                        Usluga u = (Usluga) request.getParam();
                        Controller.getInstance().dodajUslugu(u);
                        response.setResponse(null);
                    } catch (Exception exc) {
                        exc.printStackTrace();
                        response.setResponse(exc.getMessage());
                    }
                    break;
                    case UPDATE_SERVICE:
                        try {
                        Usluga u = (Usluga) request.getParam();
                        Controller.getInstance().azurirajUslugu(u);
                        response.setResponse(null);
                    } catch (Exception exc) {
                        exc.printStackTrace();
                        response.setResponse(exc.getMessage());
                    }
                    break;
                    case DELETE_SERVICE:
                        try {
                        Usluga u = (Usluga) request.getParam();
                        Controller.getInstance().obrisiUslugu(u);
                        response.setResponse(null);
                    } catch (Exception exc) {
                        response.setResponse(exc.getMessage());
                    }
                    break;
                    case LOAD_CERTIFICATE:
                        List<Sertifikat> sertifikati = Controller.getInstance().ucitajSertifikate();
                        response.setResponse(sertifikati);
                        break;
                    case DELETE_COSMETICIAN:
                        try {
                        Kozmeticar kozm = (Kozmeticar) request.getParam();
                        Controller.getInstance().obrisiKozmeticara(kozm);
                        response.setResponse(null);
                    } catch (Exception exc) {
                        response.setResponse(exc.getMessage());
                    }
                    break;
                    case LOAD_RESERVATIONS_PARAM:
                        try {
                        List<PotvrdaRezervacije> potvrde = Controller.getInstance().ucitajPotvrde(request.getParam());
                        response.setResponse(potvrde);
                    } catch (Exception excep) {
                        excep.printStackTrace();
                        response.setResponse(excep.getMessage());
                    }
                    break;
                    case LOAD_CLIENTS_PARAM:
                         try {
                        List<Klijent> klijenti = Controller.getInstance().ucitajKlijenteKrit(request.getParam());
                        response.setResponse(klijenti);
                    } catch (Exception excep) {
                        excep.printStackTrace();
                        response.setResponse(excep.getMessage());
                    }
                    break;
                    case ADD_CERTIFICATE:
                        try {
                        Sertifikat s = (Sertifikat) request.getParam();
                        Controller.getInstance().dodajSertifikat(s);
                        response.setResponse(null);
                    } catch (Exception exc) {
                        exc.printStackTrace();
                        response.setResponse(exc.getMessage());
                    }
                    break;
                    case LOAD_CLIENT:
                        try{
                            Klijent klijent = Controller.getInstance().ucitajKlijenta((Klijent) request.getParam());
                            response.setResponse(klijent);
                        }catch(Exception except){
                             except.printStackTrace();
                        response.setResponse(except.getMessage());
                            
                        }
                        break;
                    case LOAD_CONFIRMATION:
                        try{
                            PotvrdaRezervacije prez = Controller.getInstance().ucitajPotvrdu((PotvrdaRezervacije) request.getParam());
                            response.setResponse(prez);
                        }catch(Exception except){
                             except.printStackTrace();
                        response.setResponse(except.getMessage());
                            
                        }
                        break;
                    default:
                        System.out.println("Greska. Nepostojeca operacija");
                }
                sender.send(response);
            } catch (Exception ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void prekini() {
        kraj = true;
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        interrupt();
    }

}
