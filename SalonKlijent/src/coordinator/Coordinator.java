/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coordinator;

import communication.Communication;
import communication.FormState;
import controllers.UpdateClientController;
import controllers.ClientViewController;
import controllers.LoginController;
import controllers.MainFormController;
import controllers.PotvrdaRezervacijeController;
import controllers.ServicesViewController;
import controllers.PromeniPotvrdaController;
import controllers.SertifikatController;
import controllers.SviKozmeticariController;
import controllers.UpdateServiceController;
import domain.Klijent;
import domain.Kozmeticar;
import domain.PotvrdaRezervacije;
import forms.UpdateClientForm;
import forms.ClientViewForm;
import forms.LoginForm;
import forms.MainForm;
import forms.PotvrdaRezervacijeForm;
import forms.ServicesViewForm;
import forms.PromeniPotvrdaForm;
import forms.SertifikatForm;
import forms.SviKozmeticariForm;
import forms.UpdateServiceForm;
import forms.model.ClientTableModel;
import forms.model.ReservationsTableModel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ana
 */
public class Coordinator {

    private static Coordinator instance;
    private LoginController loginController;
    private MainFormController mainController;
    private ClientViewController clientController;
    private UpdateClientController addClientController;
    private PotvrdaRezervacijeController rezervacijeController;
    private PromeniPotvrdaController promeniPotvrdaController;
    private ServicesViewController serviceController;
    private UpdateServiceController updateServiceController;
    private SviKozmeticariController sviKozmController;
    private SertifikatController sertifikatController;

    private static Kozmeticar loggedIn;

    private Map<String, Object> params;

    private Coordinator() {
        params = new HashMap<>();
    }

    public static Coordinator getInstance() {
        if (instance == null) {
            instance = new Coordinator();
        }
        return instance;
    }

    public void otvoriLoginFormu() {
        loginController = new LoginController(new LoginForm());
        loginController.openForm();
    }

    public void otvoriMainForm() {
        mainController = new MainFormController(new MainForm());
        mainController.openForm();
    }

    public void openClientViewForm() {
        clientController = new ClientViewController(new ClientViewForm());
        clientController.openForm();
    }

    public void openAddClientForm() {
        addClientController = new UpdateClientController(new UpdateClientForm());
        addClientController.openForm(FormState.ADD);
    }

    public void otvoriUpdateClientForm() {
        addClientController = new UpdateClientController(new UpdateClientForm());
        addClientController.openForm(FormState.UPDATE);
    }

    public void openPotvrdaRezervacijeForm() {
        rezervacijeController = new PotvrdaRezervacijeController(new PotvrdaRezervacijeForm());
        rezervacijeController.openForm();
    }

    public void otvoriPromeniPotvrdaForm(PotvrdaRezervacije pr) {
        promeniPotvrdaController = new PromeniPotvrdaController(new PromeniPotvrdaForm());
        promeniPotvrdaController.setPr(pr);
        promeniPotvrdaController.openForm(FormState.UPDATE);
    }

    public void openServicesViewForm() {
        serviceController = new ServicesViewController(new ServicesViewForm());
        serviceController.openForm();
    }

    public void otvoriUpdateServiceForm(FormState ADD) {
        updateServiceController = new UpdateServiceController(new UpdateServiceForm());
        updateServiceController.openForm(FormState.ADD);
    }

    public void openUpdateServiceForm() {
        updateServiceController = new UpdateServiceController(new UpdateServiceForm());
        updateServiceController.openForm(FormState.UPDATE);
    }

    public void otvoriSviKozmeticariForm() {
        sviKozmController = new SviKozmeticariController(new SviKozmeticariForm());
        sviKozmController.openForm();
    }
    
    public void otvoriSertifikatForm() {
        sertifikatController = new SertifikatController(new SertifikatForm());
        sertifikatController.openForm();
    }

    public static Kozmeticar getLoggedIn() {
        return loggedIn;
    }

    public static void setLoggedIn(Kozmeticar loggedIn) {
        Coordinator.loggedIn = loggedIn;
    }

    public void osveziTabelu() {
        List<Klijent> clients = Communication.getInstance().loadClients();
        ClientTableModel model = (ClientTableModel) clientController.getCvf().getClientsTable().getModel();
        model.setClients(clients);
    }

    public void dodajParams(String s, Object o) {
        params.put(s, o);
    }

    public Object vratiParams(String s) {
        return params.get(s);
    }


  
}
