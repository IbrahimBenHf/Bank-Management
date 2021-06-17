package tn.esprit.gestionbancaire.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tn.esprit.gestionbancaire.exception.EntityNotFoundException;
import tn.esprit.gestionbancaire.exception.ErrorCodes;
import tn.esprit.gestionbancaire.exception.InvalidEntityException;
import tn.esprit.gestionbancaire.exception.InvalidOperationException;
import tn.esprit.gestionbancaire.model.Credit;
import tn.esprit.gestionbancaire.model.EtatCredit;
import tn.esprit.gestionbancaire.repository.CreditRepository;
import tn.esprit.gestionbancaire.services.CreditService;
import tn.esprit.gestionbancaire.validator.CreditValidator;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CreditServiceImpl implements CreditService {


    private CreditRepository creditRepository;
    @Autowired
    public CreditServiceImpl(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }

    @Override
    public Credit save(Credit credit) {
        List<String> errors = CreditValidator.validate(credit);
        if (!errors.isEmpty()) {
            log.error("Credit is not valid {}", credit);
            throw new InvalidEntityException("Credit is not valid", ErrorCodes.CREDIT_NOT_VALID, errors);
        }
        credit.setEtatCredit(EtatCredit.OPEN);
        return  creditRepository.save(credit);
    }

    @Override
    public Credit findById(Integer id) {
        if (id == null) {
            log.error("Credit ID is null");
            return null;
        }

        return creditRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(
                        "There is no credit found with ID = " + id,
                        ErrorCodes.CREDIT_NOT_FOUND)
        );
    }

    @Override
    public Credit findByCodeCredit(String codeCredit) {
        if (!StringUtils.hasLength(codeCredit)) {
            log.error("Credit CODE is null");
            return null;
        }

        return creditRepository.findCreditByCodeCredit(codeCredit)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "There is no credit found with CODE = " + codeCredit ,
                                ErrorCodes.CREDIT_NOT_FOUND)
                );
    }

    @Override
    public List<Credit> findAll() {
        return creditRepository.findAll().stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<Credit> findCreditHistory(Integer id) {
        return null;
    }

    @Override
    public void delete(Integer id) {

        if (id == null) {
            log.error("Credit ID is null");
            return;
        }
        Optional<Credit> credit = creditRepository.findById(id);
        if (credit.get().getEtatCredit()!= EtatCredit.REFUSED || credit.get().getEtatCredit()!= EtatCredit.ACCEPTED) {
            throw new InvalidOperationException("Faild to delete credit, Credit must be in 'ACCEPTED' or 'REFUSED'", ErrorCodes.CREDIT_IS_NOT_CLOSED);
        }

        creditRepository.deleteById(id);

    }
}
