package fi.foresail.pms.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.*;


@Entity
@Table(name = "customer")
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends BaseEntity implements Serializable {

	@Column(name = "first_name", nullable = true, length = 45)
	private String firstName;

	@Column(name = "last_name", nullable = true, length = 45)
	private String lastName;

	@Column(name = "email", nullable = true, length = 45)
	private String email;

	@Column(name = "phone", nullable = true, length = 45)
	private String phone;

	@Column(name = "company", nullable = true, length = 45)
	private String company;

	@Column(name = "address", nullable = true, length = 255)
	private String address;

	@Column(name = "city", nullable = true, length = 45)
	private String city;

	@Column(name = "state", nullable = true, length = 45)
	private String state;

	@Column(name = "postcode", nullable = true, length = 45)
	private String postcode;

	@Column(name = "country", nullable = true, length = 45)
	private String country;

	@Column(name = "notes", nullable = true, length = 255)
	private String notes;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Booking> bookings;

}
