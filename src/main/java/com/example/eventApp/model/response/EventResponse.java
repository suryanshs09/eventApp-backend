package com.example.eventApp.model.response;

import com.example.eventApp.entity.Event;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
public class EventResponse {
    private Long id;
    private String name;
    private String description;
    private Integer fees;
    private String location;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Duration duration;
    private Long organizerId;
    private OrganizerResponse organizerDetails;

    public static EventResponse from(Event event) {
        EventResponse eventObj = new EventResponse();

        eventObj.setId(event.getEventId());
        eventObj.setName(event.getName());
        eventObj.setDescription(event.getDescription());
        eventObj.setLocation(event.getLocation());
        eventObj.setFees(event.getFees());
        eventObj.setStartDate(event.getStartDate());
        eventObj.setEndDate(event.getEndDate());
        eventObj.setOrganizerId(event.getOrganizer().getOrganizerId());
        eventObj.setDuration(Duration.between(event.getStartDate(), event.getEndDate()));
        eventObj.setOrganizerDetails(OrganizerResponse.from(event.getOrganizer()));

        return eventObj;
    }
}
