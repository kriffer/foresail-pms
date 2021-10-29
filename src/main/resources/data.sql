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

CREATE TABLE account
(
    balance   decimal,
    charge    decimal,
    time_zone varchar(255),
    created   timestamp with time zone DEFAULT now() NOT NULL,
    updated   timestamp with time zone,
    active    smallint                 DEFAULT 1     NOT NULL,
    id        int auto_increment primary key
);

CREATE TABLE booking
(
    id           int auto_increment primary key,
    status       varchar(128),
    check_in     date,
    check_out    date,
    booking_date timestamp with time zone DEFAULT now(),
    referrer     varchar,
    price        decimal,
    guest_id     int NOT NULL,
    property_id  int NOT NULL,
    room_id      int NOT NULL
);


CREATE TABLE guest
(
    id         int auto_increment primary key,
    first_name varchar(128),
    last_name  varchar(128),
    email      varchar(128),
    phone      varchar(128),
    company    varchar(128),
    address    varchar(128),
    city       varchar(128),
    state      varchar(128),
    postcode   varchar(128),
    country    varchar(128),
    notes      text
);



CREATE TABLE property
(
    id                 int auto_increment primary key,
    name               varchar(255),
    updated            timestamp with time zone,
    created            timestamp with time zone DEFAULT now() NOT NULL,
    deleted            timestamp with time zone,
    active             smallint                 DEFAULT 1     NOT NULL,
    currency           varchar(128),
    address            text,
    city               varchar(128),
    state              varchar(128),
    country            varchar(128),
    postcode           varchar(128),
    lattitude          varchar(255),
    longitude          varchar(255),
    phone              varchar(128),
    mobile             varchar(128),
    email              varchar(128),
    web                varchar(128),
    contact_first_name varchar(128),
    contact_last_name  varchar(255),
    cut_off_hour       varchar(128),
    vat_rate           decimal,
    account_id         int                                NOT NULL
);



CREATE TABLE rate
(
    id                 int auto_increment primary key,
    name               varchar(255),
    first_night        date,
    last_night         date,
    min_nights         int,
    max_nights         int,
    room_price         decimal,
    extra_person_price decimal,
    bed_price          decimal,
    property_id        int NOT NULL
);


CREATE TABLE role
(
    id   int auto_increment primary key,
    name varchar(255) NOT NULL
);



CREATE TABLE room
(
    id          int auto_increment primary key,
    name        varchar(255),
    max_guests  int,
    created     timestamp with time zone DEFAULT now() NOT NULL,
    updated     timestamp with time zone,
    quantity    int,
    property_id int                                NOT NULL,
    rate_id     int,
    price       decimal
);



CREATE TABLE users
(
    id         int auto_increment primary key,
    name       varchar(255)                           NOT NULL,
    password   text                                   NOT NULL,
    email      varchar(255),
    active     smallint                 DEFAULT 1     NOT NULL,
    created    timestamp with time zone DEFAULT now() NOT NULL,
    updated    timestamp with time zone,
    account_id int                                NOT NULL
);



CREATE TABLE user_role
(
    id      int auto_increment primary key,
    user_id int,
    role_id int
);


INSERT INTO account (balance, charge, time_zone, created, updated, active, id)
VALUES (NULL, NULL, NULL, '2021-04-18 18:42:47.594797+03', NULL, 1, 1);



INSERT INTO booking (id, status, check_in, check_out, booking_date, referrer, price, guest_id, property_id, room_id)
VALUES (1, NULL, '2021-04-25', '2021-04-26', '2021-04-18 21:35:48.696616+03', 'user1', 40, 1, 1, 2);



INSERT INTO guest (id, first_name, last_name, email, phone, company, address, city, state, postcode, country, notes)
VALUES (1, 'John', 'Doe', 'jd@mail.com', '011123123', NULL, 'Broken Dreams blvd', 'New York', NULL, '111111', NULL,
        NULL);



INSERT INTO property (id, name, updated, created, deleted, active, currency, address, city, state, country, postcode,
                      lattitude, longitude, phone, mobile, email, web, contact_first_name, contact_last_name,
                      cut_off_hour, vat_rate, account_id)
VALUES (1, 'Good Hotel', NULL, '2021-04-18 21:22:32.930985+03', NULL, 1, 'EUR', 'Puistotie 25', 'Helsinki', NULL,
        'Finland', '111111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 10, 1);


INSERT INTO role (id, name)
VALUES (1, 'ROLE_ADMIN');
INSERT INTO role (id, name)
VALUES (2, 'ROLE_USER');

INSERT INTO room (id, name, max_guests, created, updated, quantity, property_id, rate_id, price)
VALUES (1, 'Double Room', 2, '2021-04-18 21:23:26.641991+03', NULL, 3, 1, NULL, 50);
INSERT INTO room (id, name, max_guests, created, updated, quantity, property_id, rate_id, price)
VALUES (2, 'Single Room', 1, '2021-04-18 21:23:57.157673+03', NULL, 2, 1, NULL, 40);


INSERT INTO user_role (id, user_id, role_id)
VALUES (1, 1, 1);


INSERT INTO users (id, name, password, email, active, created, updated, account_id)
VALUES (1, 'user1', '$2y$12$0UxFH.UEoxWqpgp4.3IkIe/5lEjxfLQHTX4sGYd0JvOk1vvXzdM3e', 'user1@mail.com', 1,
        '2021-04-18 18:43:00.718994+03', NULL, 1);


ALTER TABLE property
    ADD CONSTRAINT property_account_id_fk FOREIGN KEY (account_id) REFERENCES account (id);



ALTER TABLE rate
    ADD CONSTRAINT rate_property_id_fk FOREIGN KEY (property_id) REFERENCES property (id);



ALTER TABLE room
    ADD CONSTRAINT room_property_id_fk FOREIGN KEY (property_id) REFERENCES property (id);


ALTER TABLE room
    ADD CONSTRAINT room_rate_id_fk FOREIGN KEY (rate_id) REFERENCES rate (id);


ALTER TABLE users
    ADD CONSTRAINT user_account_id_fk FOREIGN KEY (account_id) REFERENCES account (id);


