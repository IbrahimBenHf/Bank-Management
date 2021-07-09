package tn.esprit.gestionbancaire.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.gestionbancaire.exception.EntityNotFoundException;
import tn.esprit.gestionbancaire.exception.ErrorCodes;
import tn.esprit.gestionbancaire.exception.InvalidEntityException;
import tn.esprit.gestionbancaire.model.AccountTemplate;
import tn.esprit.gestionbancaire.repository.AccountTemplateRepository;
import tn.esprit.gestionbancaire.services.AccountTemplateService;
import tn.esprit.gestionbancaire.validator.ProductsValidator;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AccountTemplateServiceImpl implements AccountTemplateService {

    private AccountTemplateRepository accountTemplateRepository;

    @Autowired
    public AccountTemplateServiceImpl(AccountTemplateRepository accountTemplateRepository) {
        this.accountTemplateRepository = accountTemplateRepository;
    }

    @Override
    public AccountTemplate save(AccountTemplate accountTemplate) throws InvalidEntityException {
        List<String> validate = ProductsValidator.validate(accountTemplate, AccountTemplate.getNullableAttributes());
        if (!validate.isEmpty()) {
            log.error("The account template you want to add is not valid", validate);
            throw new InvalidEntityException("The account template you want to add is not valid",
                    ErrorCodes.ACCOUNT_TEMPLATE_NOT_VALID, validate);
        }
        return accountTemplateRepository.save(accountTemplate);
    }

    @Override
    public AccountTemplate findById(long id) {
        return accountTemplateRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("There is no account template with the ID: " + id,
                        ErrorCodes.ACCOUNT_TEMPLATE_NOT_FOUND)
        );
    }

    @Override
    public List<AccountTemplate> findAll() {
        return accountTemplateRepository.findAll();
    }

    @Override
    public AccountTemplate update(AccountTemplate accountTemplate) {
        return null;
    }

    @Override
    public void delete(long id) {
        Optional<AccountTemplate> byId = accountTemplateRepository.findById(id);
        if(byId.isPresent()){
            accountTemplateRepository.deleteById(id);
        }else {
            log.error("There is no account template with the ID: " + id);
            throw new EntityNotFoundException("There is no account template with the ID: " + id,
                    ErrorCodes.ACCOUNT_TEMPLATE_NOT_FOUND);
        }
    }
}
