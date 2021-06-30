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
import tn.esprit.gestionbancaire.enums.CreditStatus;
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
        credit.setCreditStatus(CreditStatus.OPEN);
        return  creditRepository.save(credit);
    }

    @Override
    public Credit updateCreditStatus(Integer idCredit, CreditStatus creditStatus) {
        checkIdCredit(idCredit);
        if (!StringUtils.hasLength(String.valueOf(creditStatus))) {
            log.error("Credit status is NULL");
            throw new InvalidOperationException("IS not allowed to chagne status to null",
                    ErrorCodes.CREDIT_NON_MODIFIABLE);
        }
        Credit credit = checkCreditStatus(idCredit);
        credit.setCreditStatus(creditStatus);

        return creditRepository.save(credit);
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
    public List<Credit> findAll() {
        return creditRepository.findAll().stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<Credit> findAllNotArchived(Boolean archived) {
        return creditRepository.findAllByArchived(archived).stream()
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
        if (credit.get().getCreditStatus().equals(CreditStatus.OPEN) || credit.get().getCreditStatus().equals(CreditStatus.IN_PROGRESS) || credit.get().getCreditStatus().equals(CreditStatus.WAITING)) {
            throw new InvalidOperationException("Faild to delete credit, Credit must be in 'ACCEPTED' or 'REFUSED'", ErrorCodes.CREDIT_IS_NOT_CLOSED);
        }

        creditRepository.deleteById(id);

    }

    private void checkIdCredit(Integer idCredit) {
        if (idCredit == null) {
            log.error("Credit ID is NULL");
            throw new InvalidOperationException("ID is null",
                    ErrorCodes.CREDIT_IS_NULL);
        }
    }

    private Credit checkCreditStatus(Integer idCredit) {
        Credit credit = findById(idCredit);
        if (credit.isCreditClosed()) {
            throw new InvalidOperationException("Is not allowed to change Credit status when he's done", ErrorCodes.CREDIT_NON_MODIFIABLE);
        }
        return credit;
    }
}
