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
@Table(name = "reclamation_comment")
public class ReclamationComment extends AbstractEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "reclamation")
    private Reclamation reclamation;

    @Column(name = "comment")
    private String comment;

    @Column(name = "user_id")
    private Long userId;
}
