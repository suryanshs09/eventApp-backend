package com.example.eventApp.entity;

import com.example.eventApp.model.request.EventRequest;
import com.example.eventApp.model.request.ServiceRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table (name = "events")
public class Event {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (name = "id")
    private Long eventId;

    @Column (
            name = "name",
            columnDefinition = "VARCHAR(50)",
            nullable = false
    )
    private String name;

    @Column (
            name = "description",
            columnDefinition = "TEXT",
            nullable = false
    )
    private String description;

    @Column (name = "fees")
    private Integer fees;

    @Column (
            name = "location",
            columnDefinition = "TEXT"
    )
    private String location;

    @Column (
            name = "start_date",
            nullable = false
    )
    private LocalDateTime startDate;

    @Column (
            name = "end_date",
            nullable = false
    )
    private LocalDateTime endDate;

    @Transient
    private Duration duration;

    @ManyToOne (
            cascade = CascadeType.ALL
    )
    @JoinColumn (
            name = "organizer_id",
            foreignKey = @ForeignKey (name = "event_organizer_fk")
    )
    private Organizer organizer;

    @ManyToMany
    @JoinTable (
            name = "event_services",
            joinColumns = {
                    @JoinColumn (
                            name = "event_id",
                            foreignKey = @ForeignKey (name = "event_service_fk")
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn (
                            name = "service_id",
                            foreignKey = @ForeignKey (name = "service_event_fk")
                    )
            }
    )
    private List<EService> services;

    @ManyToMany
    @JoinTable (
            name = "event_registrations",
            joinColumns = {
                    @JoinColumn (
                            name = "event_id",
                            foreignKey = @ForeignKey (name = "event_guest_fk")
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn (name = "guest_id",
                            foreignKey = @ForeignKey (name = "guest_event_fk")
                    )
            }
    )
    private List<Guest> guests;

    @OneToMany (
            mappedBy = "event"
    )
    private List<Review> reviews;

    public static Event from(EventRequest event) {
        Event eventObj = new Event();

        if(event.getId() != null){
            eventObj.setEventId(event.getId());
        }
        eventObj.setName(event.getName());
        eventObj.setDescription(event.getDescription());
        eventObj.setEndDate(event.getEndDate());
        eventObj.setStartDate(event.getStartDate());
        eventObj.setLocation(event.getLocation());
        eventObj.setFees(event.getFees());
        eventObj.setDuration(Duration.between(event.getStartDate(), event.getEndDate()));

        return eventObj;
    }

    public void addGuests(Guest guest){
        if(guests == null){
            guests = new ArrayList<>();
        }
        this.guests.add(guest);
        guest.addEvent(this);
    }

    public void addService(EService service){
        if(services == null){
            services = new ArrayList<>();
        }
        this.services.add(service);
        service.addEvent(this);
    }

}
