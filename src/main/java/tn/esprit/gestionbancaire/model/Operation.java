package tn.esprit.gestionbancaire.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name="TYPE_OP",discriminatorType=DiscriminatorType.STRING,length=1)
public class Operation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long numero;
    private Date dateOperation;
    private double montant;

    /*
    @ManyToOne
@JoinColumn(name="CODE_CPTE")
private Compte compte;
     */
}
