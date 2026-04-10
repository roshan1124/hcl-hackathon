package com.hcl.hotel_booking_backend.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hotel_id", nullable = false)
    private Long hotelId;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private BigDecimal price;

    // Constructors
    public Room() {}

    public Room(Long hotelId, String type, BigDecimal price) {
        this.hotelId = hotelId;
        this.type = type;
        this.price = price;
    }

    // Getters
    public Long getId() { return id; }
    public Long getHotelId() { return hotelId; }
    public String getType() { return type; }
    public BigDecimal getPrice() { return price; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setHotelId(Long hotelId) { this.hotelId = hotelId; }
    public void setType(String type) { this.type = type; }
    public void setPrice(BigDecimal price) { this.price = price; }
}