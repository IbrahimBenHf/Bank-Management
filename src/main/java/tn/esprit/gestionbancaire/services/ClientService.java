package tn.esprit.gestionbancaire.services;

import tn.esprit.gestionbancaire.model.Client;

public interface ClientService {

    Client findById(long id);

    Client changeStatusToValidated(long idClient);

}
