package tn.esprit.gestionbancaire.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Account implements Serializable {

    private static final long serialVersionUID = -4704591442705123116L;

    private int accountNumber;

    private Date creationDate;

    private float balance;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private AccountTemplate accountTemplate;

    @OneToMany(mappedBy = "account")
    private List<Card> cards;

}
