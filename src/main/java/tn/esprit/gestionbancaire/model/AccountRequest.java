package tn.esprit.gestionbancaire.model;

import lombok.*;
import tn.esprit.gestionbancaire.enums.AccountRequestStatus;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AccountRequest implements Serializable {
    private static final long serialVersionUID = 7464451239676337813L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Client client;

    @ManyToOne
    private AccountTemplate accountTemplate;

    private AccountRequestStatus accountRequestStatus;

}
