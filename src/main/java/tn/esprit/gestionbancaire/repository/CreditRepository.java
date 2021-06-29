package tn.esprit.gestionbancaire.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.gestionbancaire.model.Credit;
import tn.esprit.gestionbancaire.model.CreditStatus;

import java.util.List;

public interface CreditRepository extends JpaRepository<Credit, Integer> {

    List<Credit> findAllByCreditStatus(CreditStatus creditStatus);
    List<Credit> findAllByArchived(boolean archived);

}
