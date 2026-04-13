package com.wigell.cinema.service;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.wigell.cinema.entity.Event;
import com.wigell.cinema.repository.EventRepository;

@Service
public class EventService {
    private static final Logger logger = Logger.getLogger(EventService.class.getName());

    private final EventRepository eventRepository;
    public EventService(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }

    public List<Event> getEventByMovieAndDate(Long movieId, LocalDate date){
        return eventRepository.findAll().stream()
                .filter(e -> e.getMovie().getId().equals(movieId))
                .filter(e -> e.getDateTime().toLocalDate().equals(date))
                .collect(Collectors.toList());
    }

    public Event addEvent(Event event) {
        Event saved = eventRepository.save(event);
        logger.info("Admin added event" + saved.getMovie().getTitle());
        return saved;
    }

    public void deleteEvent(Long id){
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found" + id));
        eventRepository.delete(event);
        logger.info("Admin deleted event for movie" + event.getMovie().getTitle());
    }


}
