package tn.esprit.gestionbancaire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.gestionbancaire.controller.api.CreditApi;
import tn.esprit.gestionbancaire.model.Credit;
import tn.esprit.gestionbancaire.model.EtatCredit;
import tn.esprit.gestionbancaire.services.CreditService;

@RestController
public class CreditController implements CreditApi {

    private CreditService creditService;

    @Autowired
    public CreditController(
            CreditService creditService
    ) {
        this.creditService = creditService;
    }
    @Override
    public Credit save(Credit credit) {
        return creditService.save(credit);
    }

    @Override
    public Credit updateEtatCredit(Integer idCommande, EtatCredit etatCredit) {
        return null;
    }

    @Override
    public Credit updateCredit(Integer idCommande) {
        return null;
    }
}
