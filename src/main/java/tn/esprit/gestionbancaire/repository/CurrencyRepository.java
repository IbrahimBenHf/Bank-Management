package tn.esprit.gestionbancaire.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.gestionbancaire.model.Currency;

public interface CurrencyRepository extends JpaRepository<Currency,Integer> {
}