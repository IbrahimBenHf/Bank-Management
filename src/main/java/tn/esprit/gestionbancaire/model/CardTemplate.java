package tn.esprit.gestionbancaire.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class CardTemplate extends Product {
    private static final long serialVersionUID = -1803522953297566209L;

    private long cap;

    private boolean onlinePurchase;

    private boolean otherBankWithdraw;

    private boolean forBusiness;

}
