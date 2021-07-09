package tn.esprit.gestionbancaire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.gestionbancaire.controller.api.AccountRequestApi;
import tn.esprit.gestionbancaire.enums.AccountRequestStatus;
import tn.esprit.gestionbancaire.exception.EntityNotFoundException;
import tn.esprit.gestionbancaire.exception.InvalidEntityException;
import tn.esprit.gestionbancaire.model.AccountRequest;
import tn.esprit.gestionbancaire.model.AccountTemplate;
import tn.esprit.gestionbancaire.services.AccountRequestService;
import tn.esprit.gestionbancaire.services.AccountTemplateService;

@RestController
public class AccountRequestController implements AccountRequestApi {

    private AccountRequestService accountRequestService;
    private AccountTemplateService accountTemplateService;

    @Autowired
    public AccountRequestController(AccountRequestService accountRequestService,
                                    AccountTemplateService accountTemplateService) {
        this.accountRequestService = accountRequestService;
        this.accountTemplateService = accountTemplateService;
    }

    @Override
    public ResponseEntity<Object> saveForNewClient(AccountRequest accountRequest, long idTemplate) {
        try {
            AccountTemplate accountTemplate = accountTemplateService.findById(idTemplate);
            accountRequest.setAccountRequestStatus(AccountRequestStatus.OPEN);
            accountRequest.setAccountTemplate(accountTemplate);
            accountRequest.getClient().setValidated(false);
            return ResponseEntity.ok(accountRequestService.save(accountRequest, true));
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getErrorCode(), HttpStatus.BAD_REQUEST);
        }
    }
}
