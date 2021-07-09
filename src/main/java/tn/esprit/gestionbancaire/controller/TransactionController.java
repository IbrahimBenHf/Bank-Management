package tn.esprit.gestionbancaire.controller;

import org.springframework.http.ResponseEntity;
import tn.esprit.gestionbancaire.controller.api.TransactionAPI;
import tn.esprit.gestionbancaire.model.Transaction;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class TransactionController implements TransactionAPI {
    @Override
    public ResponseEntity<Transaction> save(Transaction transaction) {
        return null;
    }

    @Override
    public Transaction getTransactionById(Integer id) {
        return null;
    }

    @Override
    public List<Transaction> getTransactionByOperation(Integer idOperation) {
        return null;
    }

    @Override
    public BigDecimal getYearlyNegBalanceByUser(Integer id, int year) {
        return null;
    }

    @Override
    public Integer countNegativeTransactionBalanceByAccount(Integer id) {
        return null;
    }

    @Override
    public Integer countNegativeTransactionBalanceByUser(Integer idUser) {
        return null;
    }

    @Override
    public Integer getAllNegativeBalance() {
        return null;
    }

    @Override
    public List<Transaction> getMonthlyTransactions(Date date) {
        return null;
    }

    @Override
    public List<Transaction> getMonthlyTransactionsByClient(Integer idUser, Date date) {
        return null;
    }

    // TODO RevertTransaction
}
