package tn.esprit.gestionbancaire.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class AccountTemplate extends Product {
    private static final long serialVersionUID = -3957551848546275772L;
}
