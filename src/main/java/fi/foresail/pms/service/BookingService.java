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
package fi.foresail.pms.service;

import fi.foresail.pms.model.Booking;
import fi.foresail.pms.model.BookingStatus;
import fi.foresail.pms.model.Property;
import fi.foresail.pms.repository.BookingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@Slf4j
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> findAllByProperty(Property property) {
        List<Booking> bookings = bookingRepository.findAllBookingsByProperty(property);
        return bookings;
    }

    public Booking create(Booking booking) {
        booking.setBookingDate(new Timestamp(new java.util.Date().getTime()));
        booking.setCheckIn(LocalDate.now());
        booking.setCheckOut(LocalDate.now().plusDays(1));
        booking.setStatus(BookingStatus.NEW);
        return bookingRepository.save(booking);

    }

    public void deleteById(Integer id) {
        bookingRepository.deleteById(id);
    }

    public Booking update(Integer id, Booking booking) {
        return bookingRepository.findById(id).map(b -> {
            b.setBookingDate(booking.getBookingDate());
            b.setCheckIn(booking.getCheckIn());
            b.setCheckOut(booking.getCheckOut());
            b.setGuest(booking.getGuest());
            b.setPrice(booking.getPrice());
            b.setProperty(booking.getProperty());

            b.setRoom(booking.getRoom());
            b.setReferrer(booking.getReferrer());
            b.setStatus(booking.getStatus());
            return bookingRepository.save(b);
        }).orElseGet(() -> {
            booking.setId(id);
            return bookingRepository.save(booking);
        });

    }
}
