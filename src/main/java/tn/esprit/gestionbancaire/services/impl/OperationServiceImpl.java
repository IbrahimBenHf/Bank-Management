package tn.esprit.gestionbancaire.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tn.esprit.gestionbancaire.enums.OperationStatus;
import tn.esprit.gestionbancaire.exception.EntityNotFoundException;
import tn.esprit.gestionbancaire.exception.ErrorCodes;
import tn.esprit.gestionbancaire.exception.InvalidEntityException;
import tn.esprit.gestionbancaire.exception.InvalidOperationException;
import tn.esprit.gestionbancaire.model.Operation;
import tn.esprit.gestionbancaire.repository.OperationRepository;
import tn.esprit.gestionbancaire.services.IOperationService;
import tn.esprit.gestionbancaire.validator.OperationValidator;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
@Service
@Slf4j
public class OperationServiceImpl implements IOperationService {
    @Autowired
    OperationRepository operationRepository;
    @Override
    public Operation save(Operation operation) {
        List<String> errors = OperationValidator.validate(operation);
        if (!errors.isEmpty()) {
            log.error("Operation is not valid {}", operation);
            throw new InvalidEntityException("Operation is not valid", ErrorCodes.OPERATION_NOT_VALID, errors);
        }
        operation.setOperationStatus(OperationStatus.TO_BE_EXECUTED);
        return operationRepository.save(operation);
    }

    @Override
        public Operation findOperationById(Integer id) {
        return operationRepository.findById(id).orElseThrow(
                () ->
        new EntityNotFoundException(
                "There is no Operation found with ID = " + id,
                ErrorCodes.OPERATION_NOT_FOUND
        ));
    }

    @Override
    public Operation updateOperationStatus(Integer IdOperation, OperationStatus operationStatus) {
        checkIdOperation(IdOperation);
        checkStatus(operationStatus);
        if(operationStatus.equals(OperationStatus.EXECUTED) || operationStatus.equals(OperationStatus.CANCELLED)){
            Operation o = operationRepository.getById(IdOperation);
            o.setOperationStatus(operationStatus);
            return o;
        }
        return null;

    }

    @Override
    public List<Operation> getArchivedOperation(boolean isArchived) {
        return operationRepository.findAll().stream().filter(x->x.getIsArchived().equals(isArchived)).collect(Collectors.toList());
    }

    @Override
    public List<Operation> getAllOperationByAccount(long accountNumber) {
        return operationRepository.findAll().stream()
                .filter(x->x.getAccount().getId()==accountNumber)
                .collect(Collectors.toList());
    }

    @Override
    public List<Operation> getAllOperationByAccountAndStatus(long accountNumber, OperationStatus operationStatus) {
        Collection<Operation> operations = getAllOperationByAccount(accountNumber);
        return operations.stream().filter(x-> x.getOperationStatus().equals(operationStatus)).collect(Collectors.toList());
    }

    private void checkIdOperation(Integer idopt) {
        if (idopt == null) {
            log.error("Operation ID is NULL");
            throw new InvalidOperationException("ID is null",
                    ErrorCodes.OPERATION_IS_NULL);
        }
    }

    private void checkStatus(OperationStatus operationStatus ){
        if (!StringUtils.hasLength(String.valueOf(operationStatus))) {
            log.error("Operation status is NULL");
            throw new InvalidOperationException("IS not allowed to chagne status to null",
                    ErrorCodes.CREDIT_NON_MODIFIABLE);
        }

    }
}
