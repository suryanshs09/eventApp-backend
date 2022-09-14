package com.example.eventApp.entity;

import com.example.eventApp.model.request.ServiceRequest;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Data
@Entity
@Table (name = "services")
public class EService {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "id")
    private Long serviceId;

    @Column (name = "name")
    private String name;

    @Column (name = "rating")
    private Integer rating;

    @Column (name = "contact")
    private String contact;

    @Column (name = "description")
    private String description;

    @ManyToMany (mappedBy = "services")
    private List<Event> events;

    public static EService from(ServiceRequest serviceModel) {
        EService service = new EService();
        if(serviceModel.getId() != null) {
            service.setServiceId(serviceModel.getId());
        }
        service.setName(serviceModel.getName());
        service.setRating(serviceModel.getRating());
        service.setContact(serviceModel.getContact());
        service.setDescription(serviceModel.getDescription());
        return service;
    }

    public void addEvent(Event event) {
        if(this.events == null) {
            this.events = new ArrayList<>();
        }
        this.events.add(event);
    }
}
