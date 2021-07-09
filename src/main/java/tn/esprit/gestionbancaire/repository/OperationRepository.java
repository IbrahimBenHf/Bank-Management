package tn.esprit.gestionbancaire.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.gestionbancaire.model.Operation;
@Repository
public interface OperationRepository extends JpaRepository<Operation,Integer> {
}
