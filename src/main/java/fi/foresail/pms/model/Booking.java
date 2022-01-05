/*
 * MIT License
 *
 * Copyright (c) 2021 Anton Kravets (kriffer.io)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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
	@JoinColumn(name = "guest_id", referencedColumnName = "id", nullable = true)
	private Guest guest;

	@ManyToOne 
	@JoinColumn(name = "property_id", referencedColumnName = "id", nullable = false)
	private Property property;

	@ManyToOne 
	@JoinColumn(name = "room_id", referencedColumnName = "id", nullable = false)
	private Room room;

}
