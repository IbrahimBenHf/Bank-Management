package tn.esprit.gestionbancaire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.gestionbancaire.controller.api.CurrencyAPI;
import tn.esprit.gestionbancaire.model.Currency;
import tn.esprit.gestionbancaire.services.ICurrencyService;

import java.util.List;
// TODO
@RestController
public class CurrencyController implements CurrencyAPI {

    @Autowired
    ICurrencyService currencyService;

    @Override
    public ResponseEntity<Currency> save(Currency currency) {
        return null;
    }

    @Override
    public ResponseEntity<Currency> updateCurrency(Integer idCCurrency, Currency currency) {
        return null;
    }

    @Override
    public List<Currency> getAll() {
        return null;
    }

    @Override
    public List<Currency> getAvailableToExchange() {
        return null;
    }

    @Override
    public List<Currency> getAllCryptoCurrency() {
        return null;
    }

    @Override
    public void deleteCurrency(Integer idCurrency) {

    }
}
