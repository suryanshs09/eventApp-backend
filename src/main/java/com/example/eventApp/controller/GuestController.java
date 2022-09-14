package com.example.eventApp.controller;

import com.example.eventApp.model.request.UserRequest;
import com.example.eventApp.model.response.EventResponse;
import com.example.eventApp.model.response.GuestResponse;
import com.example.eventApp.service.EventServiceImpl;
import com.example.eventApp.service.GuestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/api/v1/guests")
public class GuestController {

    private final GuestServiceImpl guestService;
    private final EventServiceImpl eventService;

    @Autowired
    public GuestController(GuestServiceImpl guestService, EventServiceImpl eventService) {
        this.guestService = guestService;
        this.eventService = eventService;
    }


    @PostMapping("/add")
    public ResponseEntity<GuestResponse> addGuest(@RequestBody UserRequest userModel){
        GuestResponse body = guestService.addGuest(userModel);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<GuestResponse>> getAllGuests(){
        List<GuestResponse> body = guestService.getAllGuests();
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GuestResponse> getGuestById(@PathVariable("id") Long id){
        GuestResponse body = guestService.getGuestById(id);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GuestResponse> updateGuestById(@PathVariable("id") Long id, @RequestBody UserRequest userModel){
        GuestResponse body = guestService.updateGuestById(id, userModel);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGuestById(@PathVariable("id") Long id){
        Boolean isDeleted = guestService.deleteGuestById(id);
        if(isDeleted){
            return new ResponseEntity<>("Deleted Guest with id: " + id, HttpStatus.OK);
        }
        return null;
    }

    @GetMapping("/{id}/events")
    public ResponseEntity<List<EventResponse>> getGuestEvents(@PathVariable("id") Long id){
        List<EventResponse> body = eventService.getGuestEvents(id);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
