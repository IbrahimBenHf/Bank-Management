package tn.esprit.gestionbancaire.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.gestionbancaire.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
