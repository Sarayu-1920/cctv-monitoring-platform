package com.cctv.monitoring.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "devices")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String locationName;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false)
    private String status;  // ONLINE / OFFLINE

    private LocalDateTime lastHeartbeat;

    private LocalDateTime createdAt;


    public Device() {
    }

    public Device(String name, String locationName, Double latitude, Double longitude, String status) {
        this.name = name;
        this.locationName = locationName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.status = status;
        this.createdAt = LocalDateTime.now();
    }



    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocationName() {
        return locationName;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getLastHeartbeat() {
        return lastHeartbeat;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setLastHeartbeat(LocalDateTime lastHeartbeat) {
        this.lastHeartbeat = lastHeartbeat;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
