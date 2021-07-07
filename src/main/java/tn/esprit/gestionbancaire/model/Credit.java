package tn.esprit.gestionbancaire.model;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
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

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "notes", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "note")
    private List<String> notes;

    @JsonIgnore
    @Column(name = "archived", nullable = false, columnDefinition = "bit default 0")
    private boolean archived;

    @OneToMany(mappedBy = "credit", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    @JsonManagedReference
    private List<AdministrativeDocument> administrativeDocuments;

    @ManyToOne
    @JsonBackReference
    //@JsonIgnore
    @JoinColumn(name = "user")
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

