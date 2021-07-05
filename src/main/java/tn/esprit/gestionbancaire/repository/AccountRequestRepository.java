package tn.esprit.gestionbancaire.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.gestionbancaire.model.AccountRequest;

public interface AccountRequestRepository extends JpaRepository<AccountRequest, Long> {
}
