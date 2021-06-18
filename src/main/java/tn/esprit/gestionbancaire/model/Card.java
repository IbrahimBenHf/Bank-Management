package tn.esprit.gestionbancaire.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card implements Serializable {

    private static final long serialVersionUID = -4001423874330476267L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private CardTemplate cardTemplate;

    @ManyToOne
    @JoinColumn(name = "Account_Id")
    private Account account;

}
