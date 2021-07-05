package tn.esprit.gestionbancaire.services;

import tn.esprit.gestionbancaire.model.AccountRequest;

import java.util.List;

public interface AccountRequestService {

    AccountRequest save(AccountRequest accountRequest,boolean newClient);

    AccountRequest findById(long id);

    List<AccountRequest> findAll();

    AccountRequest update(AccountRequest accountRequest);

    void delete(long id);
}
