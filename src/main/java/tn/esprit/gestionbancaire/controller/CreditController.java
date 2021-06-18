package tn.esprit.gestionbancaire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.gestionbancaire.controller.api.CreditApi;
import tn.esprit.gestionbancaire.model.Credit;
import tn.esprit.gestionbancaire.model.CreditStatus;
import tn.esprit.gestionbancaire.services.CreditService;

import java.util.List;


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
    public ResponseEntity<Credit> save(Credit credit) {
        return ResponseEntity.ok(creditService.save(credit));
    }

    @Override
    public ResponseEntity<Credit> updateCreditStatus(Integer idCredit, CreditStatus creditStatus) {
        return ResponseEntity.ok(creditService.updateCreditStatus(idCredit, creditStatus));
    }

    @Override
    public ResponseEntity<Credit> updateCredit(Integer idCredit) {
        return null;
    }

    @Override
    public void deleteCredit(Integer idCredit) {
        creditService.delete(idCredit);
    }

    @Override
    public List<Credit> findAllByArchived(Boolean archived) {
        return  creditService.findAllNotArchived(archived);
    }

    @Override
    public List<Credit> findAll() {
        return creditService.findAll();
    }
}
