package tn.esprit.gestionbancaire.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.gestionbancaire.model.Account;
import tn.esprit.gestionbancaire.model.CurrentAccount;
import tn.esprit.gestionbancaire.model.User;

public interface CurrentAccountRepository extends JpaRepository<CurrentAccount, Long> {

    Account findByAccountNumber(long accountNumber);
    Account findByUser(User user);
}
