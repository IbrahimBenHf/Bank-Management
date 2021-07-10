package tn.esprit.gestionbancaire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.gestionbancaire.controller.api.BatchApi;
import tn.esprit.gestionbancaire.enums.AccountType;
import tn.esprit.gestionbancaire.model.Credit;
import tn.esprit.gestionbancaire.services.CardService;
import tn.esprit.gestionbancaire.services.CreditService;

import java.util.List;
import java.util.Map;

@RestController
public class BatchController  implements BatchApi {
    private CreditService creditService;
    private CardService cardService;

    @Autowired
    public BatchController(
            CreditService creditService,
            CardService cardService
    ) {
        this.creditService = creditService;
        this.cardService = cardService;
    }
    @Override
    @Scheduled(cron ="00 00 20 */1 * *" )
    public List<Credit> autoValidatorBatch() {
        return creditService.autoValidate();
    }


    @Override
    @Scheduled(cron = "00 00 20 28 */1 *")
    public Map<AccountType, Integer> autoSubtractCardFees(){
        return cardService.subtractCardFees();
    }

}
