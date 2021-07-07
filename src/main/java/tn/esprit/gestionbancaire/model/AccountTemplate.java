package tn.esprit.gestionbancaire.model;

import lombok.*;
import tn.esprit.gestionbancaire.enums.AccountType;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@ToString(callSuper = true)
public class AccountTemplate extends Product {
    private static final long serialVersionUID = -3957551848546275772L;

    private boolean hasCard;

    private boolean isResident = true;

    private boolean hasCheque;

    private AccountType accountType;

    private boolean forBusiness;

    private long managementFees;

    private long bonusRate;

    @ManyToOne
    private Currency currency;

    public static List<String> getNullableAttributes(){
        return new ArrayList<>(List.of("advantages", "currency"));
    }
}
