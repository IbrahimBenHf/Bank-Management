package tn.esprit.gestionbancaire.services;

import tn.esprit.gestionbancaire.enums.OperationStatus;
import tn.esprit.gestionbancaire.model.Operation;

import java.util.List;

public interface IOperationService {

    Operation save(Operation operation);

    Operation findOperationById(Integer id);

    List<Operation> findOperationByAccount(long IdAccount);

    Operation updateOperationStatus(Integer idOperation, OperationStatus operationStatus);

    List<Operation> getArchivedOperation(boolean inArchived);

    List<Operation> getAllOperationByClient(long accountNumber);

    List<Operation> getAllOperationByClientAndStatus(long accountNumber, OperationStatus operationStatus);

    Operation revertOperation(Integer idOperation);
    // map (key value ) ==> create bills
}
