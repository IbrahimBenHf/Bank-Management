package tn.esprit.gestionbancaire.model;

import static java.util.Calendar.DATE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "user")
public class User extends AbstractEntity{

	@Column(name = "username")
	private String userName;

	@Column(name = "password")
	private String password;

	@Column(name = "active")
	private Boolean active;


    @OneToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "client_id", nullable = true)

	private Client client;

	@Column(name = "roles")
	private String roles;

	// getters and setters

	public int getAge() {
		Calendar a = getCalendar(client.getBirthDate());
		Calendar b = getCalendar(new Date());
		int diff = b.get(YEAR) - a.get(YEAR);
		if (a.get(MONTH) > b.get(MONTH) || (a.get(MONTH) == b.get(MONTH) && a.get(DATE) > b.get(DATE))) {
			diff--;
		}
		return diff;

	}

	public static Calendar getCalendar(Date date) {
		Calendar cal = Calendar.getInstance(Locale.US);
		cal.setTime(date);
		return cal;
	}




}
