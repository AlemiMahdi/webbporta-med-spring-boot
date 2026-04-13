package com.wigell.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.wigell.cinema.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository <Address, Long>{
    
}
