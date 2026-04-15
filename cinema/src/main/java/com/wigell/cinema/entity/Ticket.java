package com.wigell.cinema.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tickets")
public class Ticket{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "booking_id")
    private Booking booking;

    private double priceSek;
    private double priceUsd;

    public Ticket(){}
    
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
    public Booking getBooking() {
        return booking;
    }
    public void setBooking(Booking booking) {
        this.booking = booking;
    }
    public double getPriceSek() {
        return priceSek;
    }
    public void setPriceSek(double priceSek) {
        this.priceSek = priceSek;
    }
    public double getPriceUsd() {
        return priceUsd;
    }
    public void setPriceUsd(double priceUsd) {
        this.priceUsd = priceUsd;
    }



}