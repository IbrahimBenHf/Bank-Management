package tn.esprit.gestionbancaire.services;

import tn.esprit.gestionbancaire.model.Credit;

import java.util.List;

public interface CreditService {

    Credit save(Credit credit);

    Credit findById(Integer id);

    Credit findByCodeCredit(String codeCredit);

    List<Credit> findAll();

    List<Credit> findCreditHistory(Integer id);

    void delete(Integer id);
}
