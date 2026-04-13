package com.wigell.cinema.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.wigell.cinema.entity.Room;
import com.wigell.cinema.repository.RoomRepository;

@Service
public class RoomService {
    
    private static final Logger logger = Logger.getLogger(RoomService.class.getName());
    
    private final RoomRepository roomRepository;
    public RoomService(RoomRepository repo){
        this.roomRepository = repo;
    }

    public List<Room> getAllRooms(){
        return roomRepository.findAll();
    }

    public Room getRoomById(Long id){
        return roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found" + id));
    }

    public Room addRoom(Room room){
        Room saved = roomRepository.save(room);
        logger.info("Admin added room" + saved.getName());
        return saved;
    }

    public Room updateRoom(Long id, Room updatedRoom){
        Room existing = getRoomById(id);
        existing.setName(updatedRoom.getName());
        existing.setMaxGuests(updatedRoom.getMaxGuests());
        existing.setEquipments(updatedRoom.getEquipments());
        Room saved = roomRepository.save(existing);
        logger.info("Admin updated room" + saved.getName());
        return saved;
    }

    
}
