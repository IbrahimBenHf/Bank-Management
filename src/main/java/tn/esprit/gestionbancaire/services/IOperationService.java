package tn.esprit.gestionbancaire.services;

import tn.esprit.gestionbancaire.enums.OperationStatus;
import tn.esprit.gestionbancaire.model.Operation;

import java.util.List;

public interface IOperationService {

    Operation save(Operation operation);

    Operation updateOperationStatus(Integer idOperation, OperationStatus operationStatus);

    List<Operation> getArchivedOperation(boolean inArchived);

    List<Operation> getAllOperationByAccount(int accountNumber);

    List<Operation> getAllOperationByAccountAndStatus(int accountNumber, OperationStatus operationStatus);
}
