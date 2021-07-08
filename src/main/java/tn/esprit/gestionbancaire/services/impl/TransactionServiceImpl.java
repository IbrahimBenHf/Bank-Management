package tn.esprit.gestionbancaire.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.gestionbancaire.enums.TransactionType;
import tn.esprit.gestionbancaire.model.Operation;
import tn.esprit.gestionbancaire.model.Transaction;
import tn.esprit.gestionbancaire.repository.TransactionRepository;
import tn.esprit.gestionbancaire.services.IOperationService;
import tn.esprit.gestionbancaire.services.ITransactionService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

// TODO
public class TransactionServiceImpl implements ITransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    IOperationService operationService;

    @Override
    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getTransactionByType(TransactionType transactionType) {
        return transactionRepository.findAll().stream()
                .filter(x->x.getTransactionType().equals(transactionType)).collect(Collectors.toList());
    }

    @Override
    public List<Transaction> getTransactionByOperation(long idOperation, Boolean isNegative) {
        return transactionRepository.findAll().stream().filter(x->x.getOperation().getId()==idOperation).collect(Collectors.toList());
    }

    @Override
    public BigDecimal getYearlyNegBalanceByUser(long idUser, int year) {

        List<Operation> OpByUser = operationService.getAllOperationByAccount(idUser)
                .stream().filter(x->x.getDate().getYear()== year).collect(Collectors.toList());
        BigDecimal a = BigDecimal.ZERO;
        OpByUser.stream().forEach( operation -> this.getTransactionByOperation(operation.getId(), true)
                .stream().forEach((Consumer<? super Transaction>) a.add(operation.getAmount())));
        return a;
    }

    @Override
    public Integer countNegativeTransactionBalanceByUser(long idUser) {
        //TODO
    return null;
    }

    @Override
    public Double getAllNegativeBalance() {
        return null;
    }

    @Override
    public List<Transaction> getMonthlyTransactions(Date date) {
        return null;
    }



}
