package tn.esprit.gestionbancaire.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.gestionbancaire.enums.OperationStatus;
import tn.esprit.gestionbancaire.model.Operation;
import tn.esprit.gestionbancaire.repository.OperationRepository;
import tn.esprit.gestionbancaire.services.IOperationService;

import java.util.List;
import java.util.stream.Collectors;
@Service
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
    public List<Operation> getAllOperationByAccount(long accountNumber) {
        // TODO
        return operationRepository.findAll().stream()
                .filter(x->x.getAccount().getId()==accountNumber)
                .collect(Collectors.toList());
    }

    @Override
    public List<Operation> getAllOperationByAccountAndStatus(long accountNumber, OperationStatus operationStatus) {
        // TODO
        return null;
    }
}
