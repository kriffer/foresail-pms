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

package io.kriffer.pms.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.*;


@Entity
@Table(name = "guest")
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Guest extends BaseEntity implements Serializable {

	@Column(name = "first_name", nullable = true, length = 45)
	private String firstName;

	@Column(name = "last_name", nullable = true, length = 45)
	private String lastName;

	@Column(name = "email", nullable = true, length = 45)
	private String email;

	@Column(name = "phone", nullable = true, length = 45)
	private String phone;

	@Column(name = "company", nullable = true, length = 45)
	private String company;

	@Column(name = "address", nullable = true, length = 255)
	private String address;

	@Column(name = "city", nullable = true, length = 45)
	private String city;

	@Column(name = "state", nullable = true, length = 45)
	private String state;

	@Column(name = "postcode", nullable = true, length = 45)
	private String postcode;

	@Column(name = "country", nullable = true, length = 45)
	private String country;

	@Column(name = "notes", nullable = true, length = 255)
	private String notes;

	@OneToMany(mappedBy = "guest", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Booking> bookings;

}
