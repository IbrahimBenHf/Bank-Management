package tn.esprit.gestionbancaire.services;

import tn.esprit.gestionbancaire.enums.ReclamationStatus;

public interface MailService {

    void sendMail(String title);

    void notify(ReclamationStatus reclamationStatus, String title);
}
