package com.example.eventApp.entity;

import com.example.eventApp.model.request.UserRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table (name = "guests")
@NoArgsConstructor
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long guestId;

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

    @Column (name = "dob")
    private LocalDate dob;

    @Transient
    private Period age;

    @ManyToMany (mappedBy = "guests")
    private List<Event> events;

    @OneToMany (mappedBy = "guest")
    private List<Review> reviews;

    public static Guest from(UserRequest userModel) {
        Guest guest = new Guest();

        if(userModel.getId() != null){
            guest.setGuestId(userModel.getId());
        }
        guest.setUsername(userModel.getUsername());
        guest.setPassword(userModel.getPassword());
        guest.setEmail(userModel.getEmail());
        guest.setContact(userModel.getContact());
        guest.setDob(userModel.getDob());
        guest.setAge(Period.between(userModel.getDob(), LocalDate.now()));

        return guest;
    }

    public void addEvent(Event event){
        if(events == null){
            events = new ArrayList<>();
        }
        events.add(event);
    }

}
