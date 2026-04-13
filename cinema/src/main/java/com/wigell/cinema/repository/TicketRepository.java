package com.wigell.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.wigell.cinema.entity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>{
    
}
