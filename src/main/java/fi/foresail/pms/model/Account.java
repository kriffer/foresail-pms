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

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;
import javax.persistence.*;
import lombok.*;


@Entity
@Table(name = "account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account extends BaseEntity implements Serializable {

	@Column(name = "balance", nullable = false)
	private BigDecimal balance;
	@Column(name = "charge", nullable = false)
	private BigDecimal charge;
	@Column(name = "time_zone", nullable = true, length = 45)
	private String timeZone;
	@Column(name = "created", nullable = false)
	private Timestamp created;
	@Column(name = "updated", nullable = true)
	private Timestamp updated;
	@Column(name = "active", nullable = true)
	private Short active;


	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private Set<Property> properties;

	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private Set<User> users;


}
