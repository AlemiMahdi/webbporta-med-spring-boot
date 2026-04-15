package com.wigell.cinema.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "event_id")
    private Event event;

    private int numberOfGuests;
    private double totalPriceSek;
    private double totalPriceUsd;
    private LocalDate date;
    private String equipment;

    public Booking(){}

    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public Event getEvent() {
        return event;
    }
    public void setEvent(Event event) {
        this.event = event;
    }
    public int getNumberOfGuests() {
        return numberOfGuests;
    }
    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }
    public double getTotalPriceSek() {
        return totalPriceSek;
    }
    public void setTotalPriceSek(double totalPriceSek) {
        this.totalPriceSek = totalPriceSek;
    }
    public double getTotalPriceUsd() {
        return totalPriceUsd;
    }
    public void setTotalPriceUsd(double totalPriceUsd) {
        this.totalPriceUsd = totalPriceUsd;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public String getEquipment() {
        return equipment;
    }
    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }


    
}
