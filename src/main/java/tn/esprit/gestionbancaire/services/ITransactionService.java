package tn.esprit.gestionbancaire.services;

import tn.esprit.gestionbancaire.enums.TransactionType;
import tn.esprit.gestionbancaire.model.Transaction;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface ITransactionService {

    Transaction save(Transaction transaction);

    Transaction getTransactionById(Integer id);

    List<Transaction> getTransactionByType(TransactionType transactionType);

    List<Transaction> getTransactionByOperation(long  idOperation, Boolean isNegative);

    BigDecimal getYearlyNegBalanceByUser(long idUser, int year);

    Integer countNegativeTransactionBalanceByClient(long idUser);

    // gdech men mara jbed fi el rouge

    Integer countNegativeBalanceByClient(long idUser);

    Double getAllNegativeBalance();

    List<Transaction> getMonthlyTransactions(Date date);

    List<Transaction> getMonthlyTransactionsByClient(Date date);

    Transaction RevertTransaction(Integer id);



}
