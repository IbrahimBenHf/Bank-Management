package tn.esprit.gestionbancaire.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.gestionbancaire.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
}
