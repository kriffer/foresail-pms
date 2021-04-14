package fi.foresail.pms.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "booking")
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking extends BaseEntity implements Serializable {


	@Column(name = "status", nullable = true, length = 45)
	private BookingStatus status;

	@Column(name = "check_in", nullable = false)
	private LocalDate checkIn;

	@Column(name = "check_out", nullable = false)
	private LocalDate checkOut;

	@Column(name = "booking_date", nullable = false)
	private Timestamp bookingDate;

	@Column(name = "referrer", nullable = true, length = 45)
	private String referrer;

	@Column(name = "price", nullable = true)
	private BigDecimal price;

	@ManyToOne 
	@JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = true)
	private Customer customer;

	@ManyToOne 
	@JoinColumn(name = "property_id", referencedColumnName = "id", nullable = false)
	private Property property;

	@ManyToOne 
	@JoinColumn(name = "room_id", referencedColumnName = "id", nullable = false)
	private Room room;

}
