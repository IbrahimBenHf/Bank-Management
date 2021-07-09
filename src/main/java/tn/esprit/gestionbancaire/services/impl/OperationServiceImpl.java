package tn.esprit.gestionbancaire.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.gestionbancaire.enums.OperationStatus;
import tn.esprit.gestionbancaire.model.Operation;
import tn.esprit.gestionbancaire.repository.OperationRepository;
import tn.esprit.gestionbancaire.services.IOperationService;

import java.util.List;
@Slf4j
public class OperationServiceImpl implements IOperationService {
    @Autowired
    OperationRepository operationRepository;
    @Override
    public Operation save(Operation operation) {
        return operationRepository.save(operation);
    }

    @Override
    public Operation updateOperationStatus(Integer IdOperation, OperationStatus operationStatus) {
        // TODO
        return null;
    }

    @Override
    public List<Operation> getArchivedOperation(boolean inArchived) {
        // TODO
        return null;
    }

    @Override
    public List<Operation> getAllOperationByAccount(int accountNumber) {
        // TODO
        return null;
    }

    @Override
    public List<Operation> getAllOperationByAccountAndStatus(int accountNumber, OperationStatus operationStatus) {
        // TODO
        return null;
    }
}
