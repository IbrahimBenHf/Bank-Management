package tn.esprit.gestionbancaire.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.gestionbancaire.enums.AccountType;
import tn.esprit.gestionbancaire.enums.OperationSubType;
import tn.esprit.gestionbancaire.enums.OperationType;
import tn.esprit.gestionbancaire.model.*;
import tn.esprit.gestionbancaire.repository.CardRepository;
import tn.esprit.gestionbancaire.services.CardService;
import tn.esprit.gestionbancaire.utils.AccountNumberGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Service
@Slf4j
public class CardServiceImpl implements CardService {

    private CardRepository cardRepository;
    private OperationServiceImpl operationService;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository, OperationServiceImpl operationService) {
        this.cardRepository = cardRepository;
        this.operationService = operationService;
    }

    public Card findByCardNumber(String cardNumber) {
        Optional<Card> card = cardRepository.findByCardNumber(cardNumber);
        if (card.isPresent()) {
            return card.get();
        }
        return null;
    }

    public Card generateCard(CardRequest cardRequest) {
        Card card = new Card();

        Card byCardNumber;
        String generateCardNumber;
        do {
            generateCardNumber = AccountNumberGenerator.generate();
            byCardNumber = findByCardNumber(generateCardNumber);
        } while (byCardNumber != null);
        card.setAccount(cardRequest.getAccount());
        card.setCardNumber(generateCardNumber);
        card.setBlocked(false);
        card.setCardTemplate(cardRequest.getCardTemplate());

        return cardRepository.save(card);
    }


    public Map<AccountType, Integer> subtractCardFees(){
        Map<AccountType, Integer> accountsUpdated = new HashMap<>();
        List<Card> allCards = cardRepository.findAll();
        int countCurrent = 0;
        int countSavings = 0;
        for (Card card : allCards) {
            Operation operation = new Operation();
            operation.setAmount(new BigDecimal(card.getCardTemplate().getCardFees()));
            operation.setDate(LocalDate.now());
            operation.setOperationtype(OperationType.RETRIEVE);
            operation.setOperationSubType(OperationSubType.Regluement_Tax);
            operationService.save(operation, card.getAccount().getAccountNumber());
            if (card.getAccount().getAccountTemplate().getAccountType().equals(AccountType.SAVINGS)){
                countSavings++;
                accountsUpdated.put(AccountType.SAVINGS, countSavings);
            } else {
                countCurrent++;
                accountsUpdated.put(AccountType.CURRENT, countCurrent);
            }
        }
        return accountsUpdated;
    }
}
