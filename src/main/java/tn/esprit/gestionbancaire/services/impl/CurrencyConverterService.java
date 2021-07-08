package tn.esprit.gestionbancaire.services.impl;

import org.javamoney.moneta.Money;
import tn.esprit.gestionbancaire.repository.CurrencyConverterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.convert.CurrencyConversion;
import javax.money.convert.ExchangeRateProvider;
import javax.money.convert.MonetaryConversions;
import java.math.BigDecimal;
import java.util.List;

@Service
public class CurrencyConverterService {

    @Autowired
    private CurrencyConverterRepository accountPromotionsRespository;

    public MonetaryAmount convert(String currentCurrency, String targetCurrency, BigDecimal amount){

        CurrencyUnit currentCurrencyUnit = Monetary.getCurrency(currentCurrency);
        CurrencyUnit targetCurrencyUnit = Monetary.getCurrency(targetCurrency);

        MonetaryAmount current = Monetary.getDefaultAmountFactory().setCurrency(currentCurrencyUnit)
                .setNumber(amount).create();
        CurrencyConversion conversion = MonetaryConversions.getConversion(targetCurrencyUnit);
        MonetaryAmount convertedAmount = current.with(conversion);
        return convertedAmount;

    }
}
