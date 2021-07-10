package tn.esprit.gestionbancaire.services;

import tn.esprit.gestionbancaire.enums.ReclamationStatus;
import tn.esprit.gestionbancaire.model.Reclamation;

import java.util.List;

public interface MailService {

    void sendMail(String title);

    void notify(ReclamationStatus reclamationStatus, String title);

    void sendStats(List<Reclamation> reclamations);
}
