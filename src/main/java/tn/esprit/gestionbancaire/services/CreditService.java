package tn.esprit.gestionbancaire.services;

import tn.esprit.gestionbancaire.model.Credit;
import tn.esprit.gestionbancaire.enums.CreditStatus;
import tn.esprit.gestionbancaire.model.User;

import java.util.List;

public interface CreditService {

    Credit save(Credit credit);

    Credit updateCreditStatus(Integer idCredit, CreditStatus creditStatus);

    Credit findById(Integer id);

    List<Credit> findAll();

    List<Credit> findCreditHistory(Integer id);

    List<Credit> findAllNotArchived(Boolean archived);

    void delete(Integer id);

    List<String> addNote(Integer id,String note);

    //
    long countCreditByCreditStatus(CreditStatus status);






}
