package tn.esprit.gestionbancaire.model;

import tn.esprit.gestionbancaire.enums.ReclamationStatus;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "reclamation")
public class Reclamation extends AbstractEntity {

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ReclamationStatus status;

    @Column(name = "reclamation_title")
    private String reclamationTitle;

    @Column(name = "reclamation_description")
    private String reclamationDescription;

    @Column(name = "assignee_name")
    private String assigneeName;

    @Column(name = "user_id")
    private Long userId;

    @OneToMany(mappedBy = "reclamation", fetch = FetchType.LAZY)
    private List<ReclamationComment> reclamationComments;

}
