package tn.esprit.gestionbancaire.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "reclamation_comment")
public class ReclamationComment extends AbstractEntity {

    @ManyToOne(optional = false)
    @JsonIgnore
    @JoinColumn(name = "id_reclamation")
    private Reclamation reclamation;

    @Column(name = "comment")
    private String Comment;
}
