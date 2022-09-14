package com.example.eventApp.controller;

import com.example.eventApp.model.request.EventRequest;
import com.example.eventApp.model.request.ServiceRequest;
import com.example.eventApp.model.request.UserRequest;
import com.example.eventApp.model.response.EventResponse;
import com.example.eventApp.model.response.GuestResponse;
import com.example.eventApp.model.response.ReviewResponse;
import com.example.eventApp.model.response.ServiceResponse;
import com.example.eventApp.service.EventServiceImpl;
import com.example.eventApp.service.GuestServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping (value = "/api/v1/events")
public class EventController {

    private final EventServiceImpl eventService;
    private final GuestServiceImpl guestService;

    @Autowired
    public EventController(EventServiceImpl eventService, GuestServiceImpl guestService) {
        this.eventService = eventService;
        this.guestService = guestService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<EventResponse>> getAllEvents(){
        List<EventResponse> body = eventService.getAllEvents();
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResponse> getEventById(@PathVariable("id") Long id){
        EventResponse body = eventService.getEventById(id);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<EventResponse> addEvent(@RequestBody EventRequest eventModel){
        EventResponse body = eventService.addEvent(eventModel);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventResponse> updateEvent(@PathVariable("id") Long id, @RequestBody EventRequest eventModel){
        EventResponse body = eventService.updateEvent(id, eventModel);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable("id") Long id){
        Boolean isDeleted = eventService.deleteEvent(id);
        if(isDeleted){
            return new ResponseEntity<>("Deleted Event with id: " + id, HttpStatus.OK);
        }
        return null;
    }

    @GetMapping("/{id}/guests")
    public ResponseEntity<List<GuestResponse>> getAllGuestsByEventId(@PathVariable("id") Long id){
        return new ResponseEntity<>(guestService.getAllGuestsByEventId(id), HttpStatus.OK);
    }

    @PostMapping("/{eid}/addGuest")
    public ResponseEntity<String> RegisterGuest(@PathVariable("eid") Long eid, @RequestBody UserRequest guestRequest){
        Boolean register = eventService.registerGuest(eid, guestRequest);
        if(register){
            return new ResponseEntity<>("Registered Guest", HttpStatus.OK);
        }
        return new ResponseEntity<>("Error in Registration", HttpStatus.OK);
    }

    @PostMapping("/{eid}/addServices")
    public ResponseEntity<String> addService(@PathVariable("eid") Long eid, @RequestBody ServiceRequest services){
        Boolean register = eventService.addService(eid, services);
        if(register){
            return new ResponseEntity<>("Added Services", HttpStatus.OK);
        }
        return new ResponseEntity<>("Error in Adding Service", HttpStatus.OK);
    }

    @GetMapping("/{eid}/services")
    public ResponseEntity<List<ServiceResponse>> getAllServicesByEventId(@PathVariable("eid") Long eid){
        return new ResponseEntity<>(eventService.getAllServicesByEventId(eid), HttpStatus.OK);
    }

    @GetMapping("/{id}/reviews")
    public ResponseEntity<List<ReviewResponse>> getAllReviewsByEventId(@PathVariable("id") Long id){
        return new ResponseEntity<>(eventService.getAllReviewsByEventId(id), HttpStatus.OK);
    }

}
