package tn.esprit.gestionbancaire.services;

import tn.esprit.gestionbancaire.enums.TransactionType;
import tn.esprit.gestionbancaire.model.Transaction;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface ITransactionService {

    Transaction save(Transaction transaction);

    List<Transaction> getTransactionByType(TransactionType transactionType);

    List<Transaction> getTransactionByOperation(long  idOperation, Boolean isNegative);

    BigDecimal getYearlyNegBalanceByUser(long idUser, int year);

    Integer countNegativeTransactionBalanceByUser(long idUser);

    Double getAllNegativeBalance();

    List<Transaction> getMonthlyTransactions(Date date);



}
