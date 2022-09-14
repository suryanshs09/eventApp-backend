package com.example.eventApp.service;

import com.example.eventApp.model.request.EventRequest;
import com.example.eventApp.model.request.ServiceRequest;
import com.example.eventApp.model.request.UserRequest;
import com.example.eventApp.model.response.EventResponse;
import com.example.eventApp.model.response.ReviewResponse;
import com.example.eventApp.model.response.ServiceResponse;

import java.util.List;

public interface EventService {
    EventResponse addEvent(EventRequest eventModel);

    List<EventResponse> getAllEventsByOrganizerId(Long id);

    List<EventResponse> getAllEvents();

    EventResponse getEventById(Long id);

    EventResponse updateEvent(Long id, EventRequest eventModel);

    Boolean deleteEvent(Long id);

    Boolean registerGuest(Long eid, UserRequest guestRequest);

    List<EventResponse> getGuestEvents(Long id);

    Boolean addService(Long eid, ServiceRequest services);

    List<ServiceResponse> getAllServicesByEventId(Long eid);

    List<ReviewResponse> getAllReviewsByEventId(Long id);
}
