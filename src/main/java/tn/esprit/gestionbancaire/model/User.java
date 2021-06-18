package tn.esprit.gestionbancaire.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import tn.esprit.gestionbancaire.enums.CivilState;
import tn.esprit.gestionbancaire.enums.Sexe;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email_address")
	private String emailAddress;

	@Column(name = "password")
	private String password;

	@Column(name = "cin")
	private int cin;

	@Column(name = "birth_date")
	private Date birthDate;

	@Column(name = "phone_number")
	private long phoneNumber;

	@Column(name = "sexe")
	private Sexe sexe;

	@Column(name = "civil_state")
	private CivilState civilState;

	// getters and setters
}