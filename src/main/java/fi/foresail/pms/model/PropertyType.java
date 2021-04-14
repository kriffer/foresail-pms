package fi.foresail.pms.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "property_type", schema = "public", catalog = "foresail_pms")
@ToString
@EqualsAndHashCode
@Setter
@Getter
public class PropertyType extends BaseEntity implements Serializable {

	@Column(name = "name", nullable = false, length = 45)
	private String name;
	@OneToMany(mappedBy = "propertyType")
	private Set<Property> properties;

}
