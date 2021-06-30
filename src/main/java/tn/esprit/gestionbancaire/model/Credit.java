package tn.esprit.gestionbancaire.model;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import tn.esprit.gestionbancaire.enums.CreditStatus;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "credit")
public class Credit extends AbstractEntity {

    @Column(name = "creditstatus")
    @Enumerated(EnumType.STRING)
    private CreditStatus creditStatus;

    @Column(name = "notes")
    private String notes;

    @JsonIgnore
    @Column(name = "archived", nullable = false, columnDefinition = "bit default 0")
    private boolean archived;

    @OneToMany(mappedBy = "credit", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //@JsonManagedReference
    private List<AdministrativeDocument> administrativeDocuments;
/*
    @Column(name = "idUser")
    private int idUser;*/

    @ManyToOne
    //@JsonBackReference
    @JsonIgnore
    @JoinColumn(name = "iduser")
    private User user;



/*
    @Column(name = "etatcredit")
    @Enumerated(EnumType.STRING)
    private EtatCredit etatCredit;

    @ManyToOne
    @JoinColumn(name = "iduser")
    private User idUser;

    @OneToOne
    @JoinColumn(name = "creditTemplate", referencedColumnName = "code")
    private CreditTemplate creditTemplate;

    @Column(name = "datecredit")
    private Instant dateCredit;
*/

    public boolean isCreditClosed() {
        return (CreditStatus.ACCEPTED.equals(this.creditStatus) || CreditStatus.REFUSED.equals(this.creditStatus));
    }
}

