package fi.foresail.pms.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "rate")
@ToString
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rate extends BaseEntity implements Serializable {


	@Column(name = "name", nullable = false, length = 45)
	private String name;

	@Column(name = "first_night", nullable = false)
	private Date firstNight;

	@Column(name = "last_night", nullable = false)
	private Date lastNight;

	@Column(name = "min_nights", nullable = false)
	private Integer minNights;

	@Column(name = "max_nights", nullable = false)
	private Integer maxNights;

	@Column(name = "room_price", nullable = false)
	private BigDecimal roomPrice;

	@Column(name = "extra_person_price", nullable = true)
	private BigDecimal extraPersonPrice;

	@Column(name = "bed_price", nullable = true)
	private BigDecimal bedPrice;

	@OneToMany(mappedBy = "rate")
	private Set<Room> rooms;

	@ManyToOne
	@JoinColumn(name = "property_id", referencedColumnName = "id", nullable = false)
	private Property property;

}