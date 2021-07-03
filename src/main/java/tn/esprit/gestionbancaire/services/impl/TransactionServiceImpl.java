package tn.esprit.gestionbancaire.services.impl;

import tn.esprit.gestionbancaire.enums.TransactionType;
import tn.esprit.gestionbancaire.model.Transaction;
import tn.esprit.gestionbancaire.services.ITransactionService;

import java.util.Date;
import java.util.List;
// TODO
public class TransactionServiceImpl implements ITransactionService {
    @Override
    public Transaction save(Transaction transaction) {
        return null;
    }

    @Override
    public List<Transaction> getTransactionByType(TransactionType transactionType) {
        return null;
    }

    @Override
    public List<Transaction> getYearlyNegBalanceByUser(int idUser, int year) {
        return null;
    }

    @Override
    public Integer countNegativeTransactionBalanceByUser(int idUser) {
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
