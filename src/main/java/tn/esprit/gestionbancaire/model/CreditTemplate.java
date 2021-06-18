package tn.esprit.gestionbancaire.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class CreditTemplate extends Product {
    private static final long serialVersionUID = 2312601340596660509L;
}
