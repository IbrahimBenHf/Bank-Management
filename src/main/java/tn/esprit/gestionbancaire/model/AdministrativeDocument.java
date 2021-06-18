package tn.esprit.gestionbancaire.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "administrativedocument")
public class AdministrativeDocument extends AbstractEntity {

    @Column(name = "photo")
    private String photo;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private AdministrativeDocumentType administrativeDocumentType;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "idcredit")
    private Credit credit;
}
