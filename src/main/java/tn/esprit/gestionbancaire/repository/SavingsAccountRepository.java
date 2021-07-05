package tn.esprit.gestionbancaire.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.gestionbancaire.model.Account;
import tn.esprit.gestionbancaire.model.SavingsAccount;
import tn.esprit.gestionbancaire.model.User;

public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, Long> {

    Account findByAccountNumber(long accountNumber);
    Account findByUser(User user);
}
