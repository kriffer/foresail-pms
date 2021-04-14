package fi.foresail.pms.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;
import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "room")
@ToString
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Room extends BaseEntity implements Serializable {

	@Column(name = "name",  nullable = false, length = 128)
	private String name;
	@Column(name = "max_guests", nullable = false)
	private Integer maxGuests;

	@Column(name = "created", nullable = false)
	private Timestamp created;

	@Column(name = "updated", nullable = true)
	private Timestamp updated;

	@Column(name = "quantity", nullable = false)
	private Integer quantity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "property_id", referencedColumnName = "id", nullable = false)
	private Property property;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rate_id", referencedColumnName = "id", nullable = false)
	private Rate rate;

	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
	private Set<Unit> units;

	@Column(name = "price", nullable = true)
	private BigDecimal price;

}
