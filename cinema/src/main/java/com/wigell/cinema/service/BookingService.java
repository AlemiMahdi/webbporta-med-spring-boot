package com.wigell.cinema.service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.wigell.cinema.dto.BookingDTO;
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

    public BookingService(BookingRepository bookingRepository,
                          CustomerRepository customerRepository,
                          EventRepository eventRepository) {
        this.bookingRepository = bookingRepository;
        this.customerRepository = customerRepository;
        this.eventRepository = eventRepository;
    }

    public List<Booking> getBookingsByCustomerId(Long customerId) {
        return bookingRepository.findAll().stream()
                .filter(b -> b.getCustomer().getId().equals(customerId))
                .collect(Collectors.toList());
    }

    public Booking addBooking(BookingDTO request) {

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Event event = eventRepository.findById(request.getEventId())
                .orElseThrow(() -> new RuntimeException("Event not found"));

        Booking booking = new Booking();
        booking.setCustomer(customer);
        booking.setEvent(event);
        booking.setNumberOfGuests(request.getNumberOfGuests());
        booking.setDate(request.getDate());
        booking.setEquipment(request.getEquipment());

        double priceSek = request.getNumberOfGuests() * PRICE_PER_GUEST;
        booking.setTotalPriceSek(priceSek);
        booking.setTotalPriceUsd(priceSek * USD_RATE);

        Booking saved = bookingRepository.save(booking);

        logger.info("Booking created with id: " + saved.getId());

        return saved;
    }

    public Booking updateBooking(Long id, BookingDTO request) {

        Booking existing = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found: " + id));

        if (request.getDate() != null) {
            existing.setDate(request.getDate());
        }

        if (request.getEquipment() != null) {
            existing.setEquipment(request.getEquipment());
        }

        if (request.getNumberOfGuests() > 0) {
            existing.setNumberOfGuests(request.getNumberOfGuests());
        }

        Booking saved = bookingRepository.save(existing);

        logger.info("Booking updated with id: " + saved.getId());

        return saved;
    }
}