package tn.esprit.gestionbancaire.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tn.esprit.gestionbancaire.enums.AccountType;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class AccountTemplate extends Product {
    private static final long serialVersionUID = -3957551848546275772L;

    private boolean hasCard;

    private boolean isResident;

    private boolean hasCheque;

    private AccountType accountType;

    private boolean forBusiness;

    @ManyToOne
    private Currency currency;
}
