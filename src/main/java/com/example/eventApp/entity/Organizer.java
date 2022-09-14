package com.example.eventApp.entity;

import com.example.eventApp.model.request.UserRequest;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "organizers")
public class Organizer {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (name = "id")
    private Long organizerId;

    @Column (
            name = "username",
            nullable = false
    )
    private String username;

    @Column (
            name = "password",
            nullable = false
    )
    private String password;

    @Column (
            name = "contact",
            nullable = false
    )
    private String contact;

    @Column (
            name = "email",
            nullable = false,
            unique = true
    )
    private String email;

    @OneToMany (
            mappedBy = "organizer"
    )
    private List<Event> events;



    public static Organizer from (UserRequest userModel){
        Organizer organizer = new Organizer();
        if(userModel.getId() != null){
            organizer.setOrganizerId(userModel.getId());
        }
        organizer.setUsername(userModel.getUsername());
        organizer.setPassword(userModel.getPassword());
        organizer.setEmail(userModel.getEmail());
        organizer.setContact(userModel.getContact());

        return organizer;
    }


    public void addEvents(Event event){
        if(events == null){
            events = new ArrayList<>();
        }
        events.add(event);
    }

}
