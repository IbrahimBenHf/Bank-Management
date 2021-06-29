package tn.esprit.gestionbancaire.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
public class Currency implements Serializable {

    private static final long serialVersionUID = 2948915794988044361L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
}
