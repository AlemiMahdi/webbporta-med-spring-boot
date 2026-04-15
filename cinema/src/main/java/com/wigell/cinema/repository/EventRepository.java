package com.wigell.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.wigell.cinema.entity.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>{

    void deleteAllByMovieId(Long id);
    
}
