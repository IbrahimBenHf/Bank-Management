package tn.esprit.gestionbancaire.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import tn.esprit.gestionbancaire.enums.AdministrativeDocumentType;

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
    @JsonBackReference
    //@JsonIgnore
    @JoinColumn(name = "credit")
    private Credit credit;


}
