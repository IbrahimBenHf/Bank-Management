package tn.esprit.gestionbancaire.services;

import tn.esprit.gestionbancaire.model.Currency;

import java.util.List;

public interface ICurrencyService {

    Currency save(Currency currency);

    Currency updateCurrency(Integer idCurrency);

    Currency findBySymbol(String symbol);

    List<Currency> findAll();

    List<Currency> findAvailableToExchange();

    List<Currency> findAllCryptoCurrency();

    void delete(Integer id);
}
