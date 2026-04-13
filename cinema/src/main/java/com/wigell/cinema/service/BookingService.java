package com.wigell.cinema.service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.wigell.cinema.entity.Booking;
import com.wigell.cinema.entity.Customer;
import com.wigell.cinema.entity.Event;
import com.wigell.cinema.repository.BookingRepository;
import com.wigell.cinema.repository.CustomerRepository;
import com.wigell.cinema.repository.EventRepository;

@Service
public class BookingService {
    private static final Logger logger = Logger.getLogger(BookingService.class.getName());

    private static final double USD_RATE = 0.095;
    private static final double PRICE_PER_GUEST = 150.0;

    private final BookingRepository bookingRepository;
    private final CustomerRepository customerRepository;
    private final EventRepository eventRepository;

    public BookingService( BookingRepository bookingRepository, CustomerRepository customerRepository, EventRepository eventRepository){
        this.bookingRepository = bookingRepository;
        this.customerRepository = customerRepository;
        this.eventRepository = eventRepository;
    }

    public List<Booking> getBookingsByCustomerId(Long customerId){
        return bookingRepository.findAll().stream()
                .filter(b -> b.getCustomer().getId().equals(customerId))
                .collect(Collectors.toList());
    }

    public Booking addBooking(Booking booking){
        Customer customer = customerRepository.findById(booking.getCustomer().getId())
                .orElseThrow(() -> new RuntimeException("Customer was not found"));
        Event event = eventRepository.findById(booking.getEvent().getId())
                .orElseThrow(() -> new RuntimeException("Even not fouind"));
        
        booking.setCustomer(customer);
        booking.setEvent(event);

        double priceSek = booking.getNumberOfGuests() * PRICE_PER_GUEST;
        booking.setTotalPriceSek(priceSek);
        booking.setTotalPriceUsd(priceSek * USD_RATE);

        Booking saved = bookingRepository.save(booking);
        logger.info("Customer created booking with id: " + saved.getId());
        return saved;
    }

    public Booking updateBooking(Long id, Booking updatedBooking){
        Booking existing = bookingRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Booking was not found" + id));

        if (updatedBooking.getDate() != null) {
            existing.setDate(updatedBooking.getDate());
        }

        if (updatedBooking.getEquipment() != null) {
            existing.setEquipment(updatedBooking.getEquipment());
        }

        Booking saved = bookingRepository.save(existing);
        logger.info("Admin updated booking" + saved.getId());
        return saved;
    }


}
