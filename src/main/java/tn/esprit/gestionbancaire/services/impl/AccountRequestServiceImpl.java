package tn.esprit.gestionbancaire.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.gestionbancaire.exception.ErrorCodes;
import tn.esprit.gestionbancaire.exception.InvalidEntityException;
import tn.esprit.gestionbancaire.model.AccountRequest;
import tn.esprit.gestionbancaire.model.AccountTemplate;
import tn.esprit.gestionbancaire.repository.AccountRequestRepository;
import tn.esprit.gestionbancaire.repository.ClientRepository;
import tn.esprit.gestionbancaire.services.AccountRequestService;
import tn.esprit.gestionbancaire.validator.ProductsValidator;

import java.util.List;

@Service
@Slf4j
public class AccountRequestServiceImpl implements AccountRequestService {

    private AccountRequestRepository accountRequestRepository;
    private ClientRepository clientRepository;

    @Autowired
    public AccountRequestServiceImpl(AccountRequestRepository accountRequestRepository, ClientRepository clientRepository) {
        this.accountRequestRepository = accountRequestRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public AccountRequest save(AccountRequest accountRequest, boolean newClient) {
        if (newClient) {
            clientRepository.save(accountRequest.getClient());
        }
        return accountRequestRepository.save(accountRequest);
    }

    @Override
    public AccountRequest findById(long id) {
        return null;
    }

    @Override
    public List<AccountRequest> findAll() {
        return null;
    }

    @Override
    public AccountRequest update(AccountRequest accountRequest) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
