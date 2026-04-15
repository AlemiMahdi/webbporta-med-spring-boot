package com.wigell.cinema.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.wigell.cinema.dto.BookingDTO;
import com.wigell.cinema.entity.Booking;
import com.wigell.cinema.service.BookingService;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public ResponseEntity<List<Booking>> getBookings(@RequestParam Long customerId) {
        return ResponseEntity.ok(bookingService.getBookingsByCustomerId(customerId));
    }

    @PostMapping
    public ResponseEntity<Booking> addBooking(@RequestBody BookingDTO request) {
        Booking saved = bookingService.addBooking(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(location).body(saved);
    }

    @PatchMapping("/{bookingId}")
    public ResponseEntity<Booking> updateBooking(
            @PathVariable Long bookingId,
            @RequestBody BookingDTO request) {

        Booking updated = bookingService.updateBooking(bookingId, request);
        return ResponseEntity.ok(updated);
    }
}