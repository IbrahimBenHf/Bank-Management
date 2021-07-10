package tn.esprit.gestionbancaire.services;

import tn.esprit.gestionbancaire.enums.OperationStatus;
import tn.esprit.gestionbancaire.enums.OperationSubType;
import tn.esprit.gestionbancaire.enums.OperationType;
import tn.esprit.gestionbancaire.model.Operation;

import java.math.BigDecimal;
import java.util.List;

public interface IOperationService {

    Operation save(Operation operation, BigDecimal v, boolean b, OperationType retrieve, OperationSubType regluement_Credit, OperationStatus toBeExecuted);

    Operation findOperationById(Integer id);

    List<Operation> findOperationByAccount(long IdAccount);

    Operation updateOperationStatus(Integer idOperation, OperationStatus operationStatus);

    List<Operation> getArchivedOperation(boolean inArchived);

    List<Operation> getAllOperationByClient(long accountNumber);

    List<Operation> getAllOperationByClientAndStatus(long accountNumber, OperationStatus operationStatus);

    Operation revertOperation(Integer idOperation);
    // map (key value ) ==> create bills
}
