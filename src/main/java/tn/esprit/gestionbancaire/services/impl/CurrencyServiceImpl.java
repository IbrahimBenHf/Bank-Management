package tn.esprit.gestionbancaire.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.gestionbancaire.exception.ErrorCodes;
import tn.esprit.gestionbancaire.exception.InvalidOperationException;
import tn.esprit.gestionbancaire.model.Currency;
import tn.esprit.gestionbancaire.repository.CurrencyRepository;
import tn.esprit.gestionbancaire.services.ICurrencyService;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CurrencyServiceImpl implements ICurrencyService {

    @Autowired
    CurrencyRepository currencyRepository;

    @Override
    public Currency save(Currency currency) {
        return currencyRepository.save(currency);
    }

    @Override
    public Currency updateCurrency(Integer idCurrency) {
        // TODO
        return null;
    }

    @Override
    public Currency findBySymbol(String symbol) {

        return currencyRepository.findAll().stream()
                .filter(x -> x.getSymbol().equalsIgnoreCase(symbol)).findFirst().get();
    }

    @Override
    public List<Currency> findAll() {
        return currencyRepository.findAll();
    }

    @Override
    public List<Currency> findAvailableToExchange() {
        return currencyRepository.findAll().stream()
                .filter(x-> x.getSellValue().compareTo(BigDecimal.ZERO)!=0)
                .collect(Collectors.toList());
    }

    @Override
    public List<Currency> findAllCryptoCurrency() {
        return currencyRepository.findAll().stream()
                .filter(Currency::isCryptoCurrency).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        currencyRepository.deleteById(id);

    }


    private void checkSymbolCurrency(String SymbolCurrency) {
        if (SymbolCurrency == null) {
            log.error("Currency Symbol is NULL");
            throw new InvalidOperationException("Symbol is null",
                    ErrorCodes.CREDIT_IS_NULL);
        }
    }

}
