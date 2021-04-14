package fi.foresail.pms.service;

import fi.foresail.pms.model.Booking;
import fi.foresail.pms.model.BookingStatus;
import fi.foresail.pms.model.Property;
import fi.foresail.pms.repository.BookingRepository;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public void deleteById(Long id) {
        bookingRepository.deleteById(id);
    }

    public Booking update(Long id, Booking booking) {
        return bookingRepository.findById(id).map(b -> {
            b.setBookingDate(booking.getBookingDate());
            b.setCheckIn(booking.getCheckIn());
            b.setCheckOut(booking.getCheckOut());
            b.setCustomer(booking.getCustomer());
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
