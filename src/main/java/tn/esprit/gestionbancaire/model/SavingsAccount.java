package tn.esprit.gestionbancaire.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class SavingsAccount extends Account {
    private static final long serialVersionUID = -8485522286606930927L;

    private float bonusRate;
}
