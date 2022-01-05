/*
 * MIT License
 *
 * Copyright (c) 2021 Foresail Consulting (www.foresail.fi)
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

package io.kriffer.pms.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
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

//	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
//	private Set<Unit> units;

	@Column(name = "price", nullable = true)
	private BigDecimal price;

}
