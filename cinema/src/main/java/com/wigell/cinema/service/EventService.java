package com.wigell.cinema.service;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import com.wigell.cinema.dto.EventRequest;
import com.wigell.cinema.entity.Event;
import com.wigell.cinema.entity.Movie;
import com.wigell.cinema.entity.Room;
import com.wigell.cinema.repository.EventRepository;
import com.wigell.cinema.repository.MovieRepository;
import com.wigell.cinema.repository.RoomRepository;

@Service
public class EventService {
    private static final Logger logger = Logger.getLogger(EventService.class.getName());

    private final EventRepository eventRepository;
    private final RoomRepository roomRepository;
    private final MovieRepository movieRepository;
    public EventService(EventRepository eventRepository, MovieRepository movieRepository, RoomRepository roomRepository){
        this.eventRepository = eventRepository;
        this.movieRepository = movieRepository;
        this.roomRepository = roomRepository;
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

    public Event addEvent(EventRequest request) {

    Movie movie = movieRepository.findById(request.getMovieId())
            .orElseThrow(() -> new RuntimeException("Movie not found"));

    Room room = roomRepository.findById(request.getRoomId())
            .orElseThrow(() -> new RuntimeException("Room not found"));

    Event event = new Event();
    event.setMovie(movie);
    event.setRoom(room);
    event.setDateTime(request.getDateTime());

    Event saved = eventRepository.save(event);

    logger.info("Admin added event: " + saved.getMovie().getTitle());

    return saved;
}

    public void deleteEvent(Long id){
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found" + id));
        eventRepository.delete(event);
        logger.info("Admin deleted event for movie" + event.getMovie().getTitle());
    }


}
