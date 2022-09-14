package com.example.eventApp.model.response;

import com.example.eventApp.entity.Guest;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

@Data
@NoArgsConstructor
public class GuestResponse {
    private Long id;
    private String username;
    private String password;
    private String contact;
    private String email;
    private LocalDate dob;
    private Integer age;

    public static GuestResponse from(Guest guest){
        GuestResponse resObj = new GuestResponse();

        resObj.setId(guest.getGuestId());
        resObj.setPassword(guest.getPassword());
        resObj.setUsername(guest.getUsername());
        resObj.setEmail(guest.getEmail());
        resObj.setContact(guest.getContact());
        resObj.setDob(guest.getDob());
        resObj.setAge(Period.between(resObj.getDob(), LocalDate.now()).getYears());

        return resObj;
    }
}
