package tn.esprit.gestionbancaire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.gestionbancaire.controller.api.OperationAPI;
import tn.esprit.gestionbancaire.model.Currency;
import tn.esprit.gestionbancaire.model.Operation;
import tn.esprit.gestionbancaire.services.ICurrencyService;
import tn.esprit.gestionbancaire.services.IOperationService;

@RestController
public class OperationController implements OperationAPI {
    @Autowired
    IOperationService operationService;
    @Override
    public ResponseEntity<Operation> save(Operation operation) {
        return ResponseEntity.ok(operationService.save(operation));
    }
}
