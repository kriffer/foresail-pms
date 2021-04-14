package fi.foresail.pms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;
import javax.persistence.*;
import lombok.*;


@Entity
@Table(name = "account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account extends BaseEntity implements Serializable {

	@Column(name = "balance", nullable = false)
	private BigDecimal balance;
	@Column(name = "charge", nullable = false)
	private BigDecimal charge;
	@Column(name = "time_zone", nullable = true, length = 45)
	private String timeZone;
	@Column(name = "created", nullable = false)
	private Timestamp created;
	@Column(name = "updated", nullable = true)
	private Timestamp updated;
	@Column(name = "active", nullable = true)
	private Short active;


	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private Set<Property> properties;

	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private Set<User> users;


}
