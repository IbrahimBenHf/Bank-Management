package tn.esprit.gestionbancaire.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.gestionbancaire.model.Credit;
import tn.esprit.gestionbancaire.model.EtatCredit;

import java.util.List;
import java.util.Optional;

public interface CreditRepository extends JpaRepository<Credit, Integer> {

    Optional<Credit> findCreditByCodeCredit(String codeCredit);

    List<Credit> findAllByEtatCredit(EtatCredit etatcredit);
}
