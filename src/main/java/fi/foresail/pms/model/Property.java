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

package fi.foresail.pms.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;
import javax.persistence.*;
import lombok.*;


@Entity
@Table(name = "property")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Property extends BaseEntity implements Serializable {


	@Column(name = "name", nullable = false, length = 255)
	private String name;

	@Column(name = "created", nullable = false)
	private Timestamp created;

	@Column(name = "updated", nullable = true)
	private Timestamp updated;

	@Column(name = "deleted", nullable = true)
	private Timestamp deleted;

	@Column(name = "active", nullable = true)
	private Short active;

	@Column(name = "currency", nullable = true, length = 45)
	private String currency;

	@Column(name = "address", nullable = true, length = 255)
	private String address;

	@Column(name = "city", nullable = true, length = 45)
	private String city;

	@Column(name = "state", nullable = true, length = 45)
	private String state;

	@Column(name = "country", nullable = true, length = 45)
	private String country;

	@Column(name = "postcode", nullable = true, length = 45)
	private String postcode;

	@Column(name = "lattitude", nullable = true, length = 45)
	private String latitude;

	@Column(name = "longitude", nullable = true, length = 45)
	private String longitude;

	@Column(name = "phone", nullable = true, length = 45)
	private String phone;

	@Column(name = "mobile", nullable = true, length = 45)
	private String mobile;

	@Column(name = "email", nullable = true, length = 45)
	private String email;

	@Column(name = "web", nullable = true, length = 45)
	private String web;

	@Column(name = "contact_first_name", nullable = true, length = 45)
	private String contactFirstName;

	@Column(name = "contact_last_name", nullable = true, length = 45)
	private String contactLastName;

	@Column(name = "cut_off_hour", nullable = true, length = 45)
	private String cutOffHour;

	@Column(name = "vat_rate", nullable = true)
	private BigDecimal vatRate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
	private Account account;

	@OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
	private Set<Room> rooms;

	@OneToMany(mappedBy = "property",cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Rate> rates;
}
