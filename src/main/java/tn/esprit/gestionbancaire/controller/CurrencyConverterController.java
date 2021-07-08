package tn.esprit.gestionbancaire.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tn.esprit.gestionbancaire.services.impl.CurrencyConverterService;

import javax.money.MonetaryAmount;
import java.math.BigDecimal;

public class CurrencyConverterController {

    @Autowired
    CurrencyConverterService currencyConverterService;
    @PostMapping(value ="/convert")
    @ResponseBody
    public MonetaryAmount convert(@RequestParam String currentCurrency, String targetCurrency, BigDecimal amount) {
        return   currencyConverterService.convert(currentCurrency,targetCurrency,amount);
    }
}
