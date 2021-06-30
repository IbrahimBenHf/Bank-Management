package tn.esprit.gestionbancaire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.gestionbancaire.controller.api.CreditSimilateurApi;
import tn.esprit.gestionbancaire.services.CreditSimulateurService;

import java.util.Map;
@RestController
public class CreditSimilateurController implements CreditSimilateurApi {

    private CreditSimulateurService creditSimulateurService;

    @Autowired
    public CreditSimilateurController(
            CreditSimulateurService creditSimulateurService
    ) {
        this.creditSimulateurService = creditSimulateurService;
    }
    @Override
    public Map<Integer, Double> creditVehicle(double vehicleAmout, Integer vehicleFiscalPower, double selfFinancing, Integer repaymentPeriod) {
        return creditSimulateurService.creditVehicle(vehicleAmout,vehicleFiscalPower,selfFinancing,repaymentPeriod);
    }


}
