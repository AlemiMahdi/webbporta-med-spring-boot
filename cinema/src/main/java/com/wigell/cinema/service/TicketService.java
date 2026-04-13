package com.wigell.cinema.service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.wigell.cinema.entity.Booking;
import com.wigell.cinema.entity.Customer;
import com.wigell.cinema.entity.Ticket;
import com.wigell.cinema.repository.BookingRepository;
import com.wigell.cinema.repository.CustomerRepository;
import com.wigell.cinema.repository.TicketRepository;

@Service
public class TicketService {
    private static final Logger logger = Logger.getLogger(TicketService.class.getName());

    private static final double USD_RATE = 0.095;
    private static final double TICKET_PRICE_SEK = 150.0;

    private final TicketRepository ticketRepository;
    private final CustomerRepository customerRepository;
    private final BookingRepository bookingRepository;

    public TicketService(TicketRepository ticketRepository, CustomerRepository customerRepository, BookingRepository bookingRepository){
        this.ticketRepository = ticketRepository;
        this.customerRepository = customerRepository;
        this.bookingRepository = bookingRepository;
    }

    public List<Ticket> getTicketsByCustomerId(Long customerId){
        return ticketRepository.findAll().stream()
                .filter(t -> t.getCustomer().getId().equals(customerId))
                .collect(Collectors.toList());
    }

    public Ticket addTicket(Ticket ticket){
        Customer customer = customerRepository.findById(ticket.getCustomer().getId())
                .orElseThrow(() -> new RuntimeException("Customer was not found"));
        Booking booking = bookingRepository.findById(ticket.getBooking().getId())
                .orElseThrow(() -> new RuntimeException("Booking was not found"));
        
        ticket.setCustomer(customer);
        ticket.setBooking(booking);
        ticket.setPriceSek(TICKET_PRICE_SEK);
        ticket.setPriceUsd(TICKET_PRICE_SEK * USD_RATE);

        Ticket saved = ticketRepository.save(ticket);
        logger.info("Customer bought ticket with id: " + saved.getId());
        return saved;
    }
}
