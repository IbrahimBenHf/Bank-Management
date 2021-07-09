package tn.esprit.gestionbancaire.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.gestionbancaire.enums.TransactionType;
import tn.esprit.gestionbancaire.exception.EntityNotFoundException;
import tn.esprit.gestionbancaire.exception.ErrorCodes;
import tn.esprit.gestionbancaire.exception.InvalidEntityException;
import tn.esprit.gestionbancaire.exception.InvalidOperationException;
import tn.esprit.gestionbancaire.model.Operation;
import tn.esprit.gestionbancaire.model.Transaction;
import tn.esprit.gestionbancaire.repository.TransactionRepository;
import tn.esprit.gestionbancaire.services.IOperationService;
import tn.esprit.gestionbancaire.services.ITransactionService;
import tn.esprit.gestionbancaire.services.IUserService;
import tn.esprit.gestionbancaire.validator.TransactionValidator;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TransactionServiceImpl implements ITransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    IOperationService operationService;
    IUserService userService;


    @Override
    public Transaction save(Transaction transaction) {
        List<String> errors = TransactionValidator.validate(transaction);
        if (!errors.isEmpty()) {
            log.error("transaction is not valid {}", transaction);
            throw new InvalidEntityException("Operation is not valid", ErrorCodes.OPERATION_NOT_VALID, errors);
        }
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction getTransactionById(Integer id) {
        return transactionRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(
                        "There is no Transaction found with ID = " + id,
                        ErrorCodes.TX_NOT_FOUND)
        );
    }

    @Override
    public List<Transaction> getTransactionByType(TransactionType transactionType) {
        return transactionRepository.findAll().stream()
                .filter(x -> x.getTransactionType().equals(transactionType)).collect(Collectors.toList());
    }

    @Override
    public List<Transaction> getTransactionByOperation(long idOperation, Boolean isNegative) {
        return transactionRepository.findAll().stream().filter(x -> x.getOperation().getId() == idOperation).collect(Collectors.toList());
    }

    @Override
    public BigDecimal getYearlyNegBalanceByClient(long idUser, int year) {

        List<Operation> OpByUser = operationService.getAllOperationByClient(idUser)
                .stream().filter(x -> x.getDate().getYear() == year).collect(Collectors.toList());
        BigDecimal a = BigDecimal.ZERO;
        OpByUser.stream().forEach(operation -> this.getTransactionByOperation(operation.getId(), true)
                .stream().forEach((Consumer<? super Transaction>) a.add(operation.getAmount())));
        return a;
    }

    @Override
    public Integer countNegativeTransactionBalanceByAccount(long idAccount) {
       return null;
    }

    @Override
    public Integer countNegativeBalanceByUser(long idUser) {
        return null;
    }

    @Override
    public Double getAllNegativeBalance() {
        return null;
    }

    @Override
    public List<Transaction> getMonthlyTransactions(Date date) {
        return  transactionRepository.findAll().stream().filter(x -> x.getDate().getMonth() == (date.getMonth())).collect(Collectors.toList());
    }

    @Override
    public List<Transaction> getMonthlyTransactionsByClient(Date date) {
        return null;
    }

    // TODO
    @Override
    public Transaction RevertTransaction(Integer id) {
        checkIdTransaction(id);
        Transaction t = this.getTransactionById(id);
        t.setIsRevertedTransaction(true);
        Operation o = operationService.findOperationById(id);
        operationService.revertOperation(t.getOperation(), t.getIsNegativeTx(), !t.getTransactionType().equals(TransactionType.CREDIT));
        return t;

    }


    private void checkIdTransaction(Integer idTxt) {
        if (idTxt == null) {
            log.error("Transaction ID is NULL");
            throw new InvalidOperationException("ID is null",
                    ErrorCodes.TX_IS_NULL);
        }
    }


}
