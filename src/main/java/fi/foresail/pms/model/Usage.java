package fi.foresail.pms.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "usage")
@ToString
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usage extends BaseEntity implements Serializable {

	@Column(name = "num_properties", nullable = true)
	private Integer numProperties;

	@Column(name = "num_rooms", nullable = true)
	private Integer numRooms;

	@Column(name = "num_activities", nullable = true)
	private Integer numActivities;

	@Column(name = "num_links", nullable = true)
	private Integer numLinks;

	@OneToOne
	@JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
	private Account account;
}
