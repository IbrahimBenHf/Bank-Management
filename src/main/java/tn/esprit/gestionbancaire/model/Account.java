package tn.esprit.gestionbancaire.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
public abstract class Account implements Serializable {

    private static final long serialVersionUID = -4704591442705123116L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String accountNumber;

    @CreatedDate
    @Column(name = "creationDate", nullable = false, updatable = false)
    private Date creationDate;

    private float balance;

    @ManyToOne
    private AccountTemplate accountTemplate;

    @OneToMany(mappedBy = "account")
    private List<Card> cards;

    @ManyToOne
    private Client client;

}
