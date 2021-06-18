package tn.esprit.gestionbancaire.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class CurrentAccount extends Account {
    private static final long serialVersionUID = -5924254086339718650L;
}
