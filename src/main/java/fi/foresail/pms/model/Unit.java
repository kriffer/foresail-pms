package fi.foresail.pms.model;

import java.io.Serializable;
import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "units")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(UnitId.class)
public class Unit implements Serializable {


	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "name", nullable = false, length = 45)
	private String name;

	@Column(name = "info")
	private String info;

	@Id
	@ManyToOne
	@JoinColumn(name = "room_id")
	private Room room;

}
