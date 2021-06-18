package tn.esprit.gestionbancaire.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Account implements Serializable {

    private static final long serialVersionUID = -4704591442705123116L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private AccountTemplate accountTemplate;

    @OneToMany(mappedBy = "account")
    private List<Card> cards;



}
