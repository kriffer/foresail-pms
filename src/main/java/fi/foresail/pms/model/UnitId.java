package fi.foresail.pms.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.io.Serializable;
import lombok.EqualsAndHashCode;



@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UnitId implements Serializable {

	private Integer id;

	private Long room;


}