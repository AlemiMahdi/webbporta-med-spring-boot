package com.wigell.cinema.controller;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.wigell.cinema.dto.EventRequest;
import com.wigell.cinema.entity.Event;
import com.wigell.cinema.service.EventService;

@RestController
@RequestMapping("/api/v1/screenings")
public class EventController {
    
    private final EventService eventService;
    public EventController(EventService eventService){
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<List<Event>> getEvents(
        @RequestParam(required = false) Long movieId,
        @RequestParam(required = false) String date
    ){
        if (movieId != null && date != null) {
            LocalDate localDate = LocalDate.parse(date);
            return ResponseEntity.ok(eventService.getEventByMovieAndDate(movieId, localDate));

        }
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @PostMapping
public ResponseEntity<Event> addEvent(@RequestBody EventRequest request){

    Event saved = eventService.addEvent(request);

    URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(saved.getId())
            .toUri();

    return ResponseEntity.created(location).body(saved);
}

    @DeleteMapping("/{screeningId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long screeningId){
        eventService.deleteEvent(screeningId);
        return ResponseEntity.noContent().build();
    }
}
