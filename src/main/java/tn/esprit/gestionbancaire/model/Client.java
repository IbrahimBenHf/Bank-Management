package tn.esprit.gestionbancaire.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nID;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String phoneNumber;
    private int age;
    private String job;
    private boolean validated;

}
