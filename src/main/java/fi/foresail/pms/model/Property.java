package fi.foresail.pms.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;
import javax.persistence.*;
import lombok.*;


@Entity
@Table(name = "property")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Property extends BaseEntity implements Serializable {


	@Column(name = "name", nullable = false, length = 255)
	private String name;

	@Column(name = "created", nullable = false)
	private Timestamp created;

	@Column(name = "updated", nullable = true)
	private Timestamp updated;

	@Column(name = "deleted", nullable = true)
	private Timestamp deleted;

	@Column(name = "active", nullable = true)
	private Short active;

	@Column(name = "currency", nullable = true, length = 45)
	private String currency;

	@Column(name = "address", nullable = true, length = 255)
	private String address;

	@Column(name = "city", nullable = true, length = 45)
	private String city;

	@Column(name = "state", nullable = true, length = 45)
	private String state;

	@Column(name = "country", nullable = true, length = 45)
	private String country;

	@Column(name = "postcode", nullable = true, length = 45)
	private String postcode;

	@Column(name = "latitude", nullable = true, length = 45)
	private String latitude;

	@Column(name = "longitude", nullable = true, length = 45)
	private String longitude;

	@Column(name = "phone", nullable = true, length = 45)
	private String phone;

	@Column(name = "mobile", nullable = true, length = 45)
	private String mobile;

	@Column(name = "email", nullable = true, length = 45)
	private String email;

	@Column(name = "web", nullable = true, length = 45)
	private String web;

	@Column(name = "contact_first_name", nullable = true, length = 45)
	private String contactFirstName;

	@Column(name = "contact_last_name", nullable = true, length = 45)
	private String contactLastName;

	@Column(name = "cut_off_hour", nullable = true, length = 45)
	private String cutOffHour;

	@Column(name = "vat_rate", nullable = true)
	private BigDecimal vatRate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
	private Account account;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type_id", referencedColumnName = "id", nullable = false)
	private PropertyType propertyType;

	@OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
	private Set<Room> rooms;

	@OneToMany(mappedBy = "property",cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Rate> rates;
}
