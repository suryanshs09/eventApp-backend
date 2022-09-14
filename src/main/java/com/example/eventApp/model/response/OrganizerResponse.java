package com.example.eventApp.model.response;

import com.example.eventApp.entity.Organizer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrganizerResponse {
    private Long id;
    private String username;
    private String password;
    private String contact;
    private String email;

    public static OrganizerResponse from(Organizer organizer){
        OrganizerResponse resObj = new OrganizerResponse();

        resObj.setId(organizer.getOrganizerId());
        resObj.setPassword(organizer.getPassword());
        resObj.setUsername(organizer.getUsername());
        resObj.setEmail(organizer.getEmail());
        resObj.setContact(organizer.getContact());

        return resObj;
    }
}
