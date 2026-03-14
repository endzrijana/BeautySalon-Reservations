/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domain.Klijent;
import domain.Kozmeticar;
import domain.PotvrdaRezervacije;
import domain.Sertifikat;
import domain.StavkaRezervacije;
import domain.TipKlijenta;
import domain.Usluga;
import java.util.List;
import operation.certificate.VratiListuSertifikatSO;
import operation.certificate.UbaciSertifikatSO;
import operation.clients.KreirajKlijentSO;
import operation.clients.PromeniKlijentSO;
import operation.clients.ObrisiKlijentSO;
import operation.clients.PretraziKlijentSO;
import operation.clients.VratiListuKlijentSO;
import operation.clients.UcitajTipoveKlijenataSO;
import operation.clients.VratiListuKlijentKritSO;
import operation.kozmeticar.ObrisiKozmeticarSO;
import operation.kozmeticar.VratiListuKozmeticarSO;
import operation.login.PrijaviKozmeticarSO;
import operation.potvrdaRezervacije.KreirajPotvrdaRezervacijaSO;
import operation.potvrdaRezervacije.PromeniPotvrdaRezervacijeSO;
import operation.potvrdaRezervacije.ObrisiPotvrduRezervacijeSO;
import operation.potvrdaRezervacije.PretraziPotvrdaRezervacijeSO;
import operation.potvrdaRezervacije.VratiListuPotvrdaRezervacijeKritSO;
import operation.potvrdaRezervacije.VratiListuPotvrdaRezervacijeSO;
import operation.services.KreirajUslugaSO;
import operation.services.ObrisiUslugaSO;
import operation.services.PromeniUslugaSO;
import operation.services.UcitajUslugeSO;

/**
 *
 * @author Ana
 */
public class Controller {

    private static Controller instance;

    private Controller() {
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public Kozmeticar login(Kozmeticar k) throws Exception {
        PrijaviKozmeticarSO operation = new PrijaviKozmeticarSO();
        operation.execute(k, null);
        System.out.println("Class Controller: " + operation.getKozm());
        return operation.getKozm();
    }

    public List<Klijent> loadClients() throws Exception {
        VratiListuKlijentSO operation = new VratiListuKlijentSO();
        operation.execute(null, null);
        System.out.println("Class controller: " + operation.getClients());
        return operation.getClients();
    }

    public void obrisiKlijenta(Klijent kl) throws Exception {
        ObrisiKlijentSO operation = new ObrisiKlijentSO();
        operation.execute(kl, null);
    }

    public List<TipKlijenta> loadTypes() throws Exception {
        UcitajTipoveKlijenataSO operation = new UcitajTipoveKlijenataSO();
        operation.execute(null, null);
        System.out.println("Class Controller:" + operation.getTypes());
        return operation.getTypes();
    }

    public Klijent kreirajKlijenta() throws Exception {
        KreirajKlijentSO operation = new KreirajKlijentSO();
        operation.execute(new Klijent(), null);
        return operation.getResult();
    }

    public void zapamtiKlijenta(Klijent kli) throws Exception {
        PromeniKlijentSO operation = new PromeniKlijentSO();
        operation.execute(kli, null);
    }

    public List<PotvrdaRezervacije> ucitajRezervacije() throws Exception {
        VratiListuPotvrdaRezervacijeSO operation = new VratiListuPotvrdaRezervacijeSO();
        operation.execute(null, null);
        System.out.println("Class Controller:" + operation.getRezervacije());
        return operation.getRezervacije();
    }

    

    public void obrisiPotvrduRezervacije(PotvrdaRezervacije pre) throws Exception {
        ObrisiPotvrduRezervacijeSO operation = new ObrisiPotvrduRezervacijeSO();
        operation.execute(pre, null);
    }

    public List<Usluga> ucitajUsluge() throws Exception {
        UcitajUslugeSO operation = new UcitajUslugeSO();
        operation.execute(null, null);
        System.out.println("Class Controller:" + operation.getUsluge());
        return operation.getUsluge();
    }

    public List<Kozmeticar> ucitajKozmeticare() throws Exception {
        VratiListuKozmeticarSO operation = new VratiListuKozmeticarSO();
        operation.execute(null, null);
        System.out.println("Class Controller:" + operation.getKozmeticari());
        return operation.getKozmeticari();
    }

    public void promeniPotvrdu(PotvrdaRezervacije prez) throws Exception {
        PromeniPotvrdaRezervacijeSO operation = new PromeniPotvrdaRezervacijeSO();
        operation.execute(prez, null);
    }

    public void obrisiStavkuRezervacije(StavkaRezervacije str) throws Exception {
        PromeniPotvrdaRezervacijeSO operation = new PromeniPotvrdaRezervacijeSO();
        operation.execute(str, null);
    }

    public void dodajUslugu(Usluga u) throws Exception {
        KreirajUslugaSO operation = new KreirajUslugaSO();
        operation.execute(u, null);
    }

    public void azurirajUslugu(Usluga u) throws Exception {
        PromeniUslugaSO operation = new PromeniUslugaSO();
        operation.execute(u, null);
    }

    public void obrisiUslugu(Usluga u) throws Exception {
        ObrisiUslugaSO operation = new ObrisiUslugaSO();
        operation.execute(u, null);
    }

    public List<Sertifikat> ucitajSertifikate() throws Exception {
        VratiListuSertifikatSO operation = new VratiListuSertifikatSO();
        operation.execute(null, null);
        return operation.getSertifikati();
    }

    public void obrisiKozmeticara(Kozmeticar kozm) throws Exception {
        ObrisiKozmeticarSO operation = new ObrisiKozmeticarSO();
        operation.execute(kozm, null);
    }

    public PotvrdaRezervacije kreirajPotvrdu() throws Exception {
        KreirajPotvrdaRezervacijaSO operation = new KreirajPotvrdaRezervacijaSO();
        operation.execute(new PotvrdaRezervacije(), null);
        return operation.getResult();
    }

    public List<PotvrdaRezervacije> ucitajPotvrde(Object param) throws Exception {
        VratiListuPotvrdaRezervacijeKritSO operation = new VratiListuPotvrdaRezervacijeKritSO();
        operation.execute(param, null);
        return operation.getLista();
    }

    public List<Klijent> ucitajKlijenteKrit(Object param) throws Exception {
        VratiListuKlijentKritSO operation = new VratiListuKlijentKritSO();
        operation.execute(param, null);
        return operation.getLista();
    }

    public void dodajSertifikat(Sertifikat s) throws Exception {
        UbaciSertifikatSO operation = new UbaciSertifikatSO();
        operation.execute(s,null);
    }

    public Klijent ucitajKlijenta(Klijent klijent) throws Exception {
        PretraziKlijentSO operation = new PretraziKlijentSO();
        operation.execute(klijent, null);
        return operation.getKl();
    }

    public PotvrdaRezervacije ucitajPotvrdu(PotvrdaRezervacije potvrdaRezervacije) throws Exception {
        PretraziPotvrdaRezervacijeSO operation = new PretraziPotvrdaRezervacijeSO();
        operation.execute(potvrdaRezervacije, null);
        return operation.getPr();
    }
}
