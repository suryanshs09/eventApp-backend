package com.example.eventApp.service;

import com.example.eventApp.entity.EService;
import com.example.eventApp.entity.Event;
import com.example.eventApp.entity.Guest;
import com.example.eventApp.exception.DataNotFoundException;
import com.example.eventApp.model.request.EventRequest;
import com.example.eventApp.model.request.ServiceRequest;
import com.example.eventApp.model.request.UserRequest;
import com.example.eventApp.model.response.EventResponse;
import com.example.eventApp.model.response.ReviewResponse;
import com.example.eventApp.model.response.ServiceResponse;
import com.example.eventApp.repository.EventRepository;
import com.example.eventApp.repository.GuestRepository;
import com.example.eventApp.repository.OrganizerRepository;
import com.example.eventApp.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService{

    private final EventRepository eventRepository;
    private final OrganizerRepository organizerRepository;
    private final GuestRepository guestRepository;
    private final ServiceRepository serviceRepository;

    @Override
    public EventResponse addEvent(EventRequest eventModel ) {
        Event event = Event.from(eventModel);
        event.setOrganizer(organizerRepository.findById(eventModel.getOrganizerId()).get());
        return EventResponse.from(eventRepository.save(event));
    }

    @Override
    public List<EventResponse> getAllEvents() {
        List<EventResponse> eventModels = new ArrayList<>();
        eventRepository.findAll().forEach(event -> eventModels.add(EventResponse.from(event)));
        return eventModels;
    }

    @Override
    public EventResponse getEventById(Long id) {
        return EventResponse.from(eventRepository.findById(id).get());
    }

    @Override
    public EventResponse updateEvent(Long id, EventRequest eventModel) {
        Event eventToUpdate = eventRepository.findById(id).get();
        if(eventToUpdate != null){
            eventToUpdate.setName(eventModel.getName());
            eventToUpdate.setDescription(eventModel.getDescription());
            eventToUpdate.setStartDate(eventModel.getStartDate());
            eventToUpdate.setEndDate(eventModel.getEndDate());
            eventToUpdate.setLocation(eventModel.getLocation());
            eventToUpdate.setDuration(Duration.between(eventModel.getStartDate(), eventModel.getEndDate()));
            eventToUpdate.setOrganizer(organizerRepository.findById(eventModel.getOrganizerId()).get());

            return EventResponse.from(eventRepository.save(eventToUpdate));
        }
        return null;
    }

    @Override
    public Boolean deleteEvent(Long id) {
        Event event = eventRepository.findById(id).get();
        if(event != null){
            event.setOrganizer(null);
            eventRepository.delete(event);
            return true;
        }
        return false;
    }

    @Override
    public Boolean registerGuest(Long eid, UserRequest guestRequest) {
        Event event = eventRepository.findById(eid).get();
        if(event != null){
            Guest guest = guestRepository.findById(guestRequest.getId()).get();
            event.addGuests(guest);
            eventRepository.save(event);
            guestRepository.save(guest);
            return true;
        }
        return false;
    }

    @Override
    public List<EventResponse> getGuestEvents(Long id) {
        List<EventResponse> eventModels = new ArrayList<>();
        guestRepository.findById(id).get().getEvents().forEach(event -> eventModels.add(EventResponse.from(event)));
        return eventModels;
    }

    @Override
    public Boolean addService(Long eid, ServiceRequest service) {
        Event event = eventRepository.findById(eid).get();
        if(event != null){
            event.addService(EService.from(service));
            eventRepository.save(event);
            serviceRepository.save(EService.from(service));
            return true;
        }

        return false;
    }

    @Override
    public List<ServiceResponse> getAllServicesByEventId(Long eid) {
        List<ServiceResponse> serviceModels = new ArrayList<>();
        eventRepository.findById(eid).get().getServices().forEach(service -> serviceModels.add(ServiceResponse.from(service)));
        return serviceModels;
    }

    @Override
    public List<ReviewResponse> getAllReviewsByEventId(Long id) {
        List<ReviewResponse> reviewModels = new ArrayList<>();
        eventRepository.findById(id).get().getReviews().forEach(review -> reviewModels.add(ReviewResponse.from(review)));
        return reviewModels;
    }

    @Override
    public List<EventResponse> getAllEventsByOrganizerId(Long id) {
        List<Event> events = eventRepository.findEventsByOrganizerOrganizerId(id);
        List<EventResponse> eventModels = new ArrayList<>();
        for(Event event: events){
            eventModels.add(EventResponse.from(event));
        }
        return eventModels;
    }

}
