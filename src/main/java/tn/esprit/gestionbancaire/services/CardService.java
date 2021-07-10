package tn.esprit.gestionbancaire.services;


import tn.esprit.gestionbancaire.enums.AccountType;
import tn.esprit.gestionbancaire.model.Card;
import tn.esprit.gestionbancaire.model.CardRequest;

import java.util.Map;

public interface CardService {

    Card findByCardNumber(String cardNumber);
    Card generateCard(CardRequest cardRequest);
    Map<AccountType, Integer> subtractCardFees();

}
