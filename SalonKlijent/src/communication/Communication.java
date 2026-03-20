/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import domain.AbstractDomainObject;
import domain.Klijent;
import domain.Kozmeticar;
import domain.PotvrdaRezervacije;
import domain.Sertifikat;
import domain.StavkaRezervacije;
import domain.TipKlijenta;
import domain.Usluga;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Ana
 */
public class Communication {

    private Socket s;
    private Sender sender;
    private Receiver receiver;
    private static Communication instance;

    private Communication() {

    }

    public static Communication getInstance() {
        if (instance == null) {
            instance = new Communication();
        }
        return instance;
    }

    public void connection() throws Exception {
        try {
            s = new Socket("localhost", 9000);
            sender = new Sender(s);
            receiver = new Receiver(s);
        } catch (IOException ex) {
            System.out.println("Server nije povezan");
        }
    }

    public Kozmeticar login(String username, String password) {
        Kozmeticar k = new Kozmeticar();
        k.setKorisnickoIme(username);
        k.setSifra(password);
        Request request = new Request(Operation.LOGIN, k);
        sender.send(request);

        Response resp = (Response) receiver.receive();

        k = (Kozmeticar) resp.getResponse();
        return k;
    }

    public List<Klijent> loadClients() {
        List<Klijent> clients;
        Request request = new Request(Operation.LOAD_CLIENTS, null);
        sender.send(request);

        Response response = (Response) receiver.receive();
        clients = (List<Klijent>) response.getResponse();
        return clients;
    }

    public boolean obrisiKlijenta(Klijent k) throws Exception {
        Request request = new Request(Operation.DELETE_CLIENT, k);
        sender.send(request);
        Response resp = (Response) receiver.receive();
        if (resp.getResponse() == null) {
            return true;
        } else {
            Object response = resp.getResponse();
            if (response instanceof Exception) {
                throw new Exception("Klijent ima vezane zapise i ne moze biti obrisan.");
            } else {
                throw new Exception(response.toString());
            }
        }
    }

    public List<TipKlijenta> loadType() {
        Request request = new Request(Operation.LOAD_TYPE, null);
        sender.send(request);
        Response resp = (Response) receiver.receive();
        return (List<TipKlijenta>) resp.getResponse();
    }

    public boolean zapamtiKlijenta(Klijent k) throws Exception {
        Request request = new Request(Operation.ADD_CLIENT, k);
        sender.send(request);
        Response resp = (Response) receiver.receive();
        if (resp.getResponse() == null) {
            return true;
        } else {
            throw new Exception((String) resp.getResponse());
        }
    }

    public Klijent kreirajKlijenta() {
        Request request = new Request(Operation.CREATE_CLIENT, null);
        sender.send(request);
        Response resp = (Response) receiver.receive();
        return (Klijent) resp.getResponse();
    }

    public List<PotvrdaRezervacije> ucitajPotvrdeRezervacije() {
        Request request = new Request(Operation.LOAD_RESERVATIONS, null);
        sender.send(request);
        Response resp = (Response) receiver.receive();
        return (List<PotvrdaRezervacije>) resp.getResponse();
    }


    

    public boolean obrisiPotvrduRezervacije(PotvrdaRezervacije pr) throws Exception {
        Request request = new Request(Operation.DELETE_CONFIRMATION, pr);
        sender.send(request);
        Response resp = (Response) receiver.receive();
        if (resp.getResponse() == null) {
            return true;
        } else {
            throw new Exception((String) resp.getResponse());
        }
    }

    public List<Usluga> ucitajUsluge() {
        Request request = new Request(Operation.LOAD_SERVICES, null);
        sender.send(request);
        Response resp = (Response) receiver.receive();
        return (List<Usluga>) resp.getResponse();
    }

    public List<Kozmeticar> ucitajKozmeticare() {
        Request request = new Request(Operation.LOAD_COSMETICIAN, null);
        sender.send(request);
        Response resp = (Response) receiver.receive();
        return (List<Kozmeticar>) resp.getResponse();
    }

    public boolean kreirajUslugu(Usluga u) throws Exception {
        Request request = new Request(Operation.ADD_SERVICE, u);
        sender.send(request);
        Response resp = (Response) receiver.receive();
        if (resp.getResponse() == null) {
            return true;
        } else {
            throw new Exception((String) resp.getResponse());
        }
    }

    public boolean azurirajUslugu(Usluga u) throws Exception {
        Request request = new Request(Operation.UPDATE_SERVICE, u);
        sender.send(request);
        Response resp = (Response) receiver.receive();
        if (resp.getResponse() == null) {
            return true;
        } else {
            throw new Exception((String) resp.getResponse());
        }
    }

    public boolean obrisiUslugu(Usluga u) throws Exception {
        Request request = new Request(Operation.DELETE_SERVICE, u);
        sender.send(request);
        Response resp = (Response) receiver.receive();
        if (resp.getResponse() == null) {
            return true;
        } else {
            throw new Exception((String) resp.getResponse());
        }
    }

    public List<Sertifikat> ucitajSertifikate() {
        Request request = new Request(Operation.LOAD_CERTIFICATE, null);
        sender.send(request);
        Response resp = (Response) receiver.receive();
        return (List<Sertifikat>) resp.getResponse();
    }

    public boolean obrisiKozmeticara(Kozmeticar kozm) throws Exception {
        Request request = new Request(Operation.DELETE_COSMETICIAN, kozm);
        sender.send(request);
        Response resp = (Response) receiver.receive();
        if (resp.getResponse() == null) {
            return true;
        } else {
            throw new Exception((String) resp.getResponse());
        }
    }

    public boolean zapamtiPotvrduRezervacije(PotvrdaRezervacije pr) throws Exception {
        Request request = new Request(Operation.UPDATE_CONFIRMATION, pr);
        sender.send(request);
        Response resp = (Response) receiver.receive();
        if (resp.getResponse() == null) {
            return true;
        } else {
            throw new Exception((String) resp.getResponse());
        }
    }

    public PotvrdaRezervacije kreirajPotvrdaRezervacije() {
        Request request = new Request(Operation.CREATE_CONFIRMATION, null);
        sender.send(request);
        Response resp = (Response) receiver.receive();
        return (PotvrdaRezervacije) resp.getResponse();
    }

    public List<PotvrdaRezervacije> ucitajPotvrde(AbstractDomainObject ado) {
        Request request = new Request(Operation.LOAD_RESERVATIONS_PARAM, ado);
        sender.send(request);
        Response resp = (Response) receiver.receive();
        if (resp.getResponse() instanceof String) {
            System.out.println("Greska sa servera: " + resp.getResponse());
            return new ArrayList<>();
        }
        return (List<PotvrdaRezervacije>) resp.getResponse();
    }

    public List<Klijent> vratiKlijenteKrit(AbstractDomainObject ado) {
        Request request = new Request(Operation.LOAD_CLIENTS_PARAM, ado);
        sender.send(request);
        Response resp = (Response) receiver.receive();
        if (resp.getResponse() instanceof String) {
            System.out.println("Greska sa servera: " + resp.getResponse());
            return new ArrayList<>();
        }
        return (List<Klijent>) resp.getResponse();
    }

    public boolean zapamtiSertifikat(Sertifikat s) throws Exception {
        Request request = new Request(Operation.ADD_CERTIFICATE, s);
        sender.send(request);
        Response resp = (Response) receiver.receive();
        if (resp.getResponse() == null) {
            return true;
        } else {
            throw new Exception((String) resp.getResponse());
        }
    }

    public Klijent pretraziKlijenta(Klijent kli) {
        Request request = new Request(Operation.LOAD_CLIENT, kli);
        sender.send(request);
        Response resp = (Response) receiver.receive();
        if (resp.getResponse() instanceof String) {
            System.out.println("Greska sa servera: " + resp.getResponse());
            return null;
        }
        return (Klijent) resp.getResponse();
    }

    public PotvrdaRezervacije pretraziPrez(PotvrdaRezervacije prez) {
        Request request = new Request(Operation.LOAD_CONFIRMATION, prez);
        sender.send(request);
        Response resp = (Response) receiver.receive();
        if (resp.getResponse() instanceof String) {
            System.out.println("Greska sa servera: " + resp.getResponse());
            return null;
        }
        return (PotvrdaRezervacije) resp.getResponse();
    }

}
