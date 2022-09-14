package com.example.eventApp.controller;

import com.example.eventApp.model.request.UserRequest;
import com.example.eventApp.model.response.EventResponse;
import com.example.eventApp.model.response.OrganizerResponse;
import com.example.eventApp.service.EventServiceImpl;
import com.example.eventApp.service.OrganizerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/organizers")
public class OrganizerController {

    private final OrganizerServiceImpl organizerService;
    private final EventServiceImpl eventService;

    @PostMapping("/add")
    public ResponseEntity<OrganizerResponse> addOrganizer(@RequestBody UserRequest userModel){
        OrganizerResponse body = organizerService.addOrganizer(userModel);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrganizerResponse>> getAllOrganizers(){
        List<OrganizerResponse> body = organizerService.getAllOrganizers();
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrganizerResponse> getOrganizerById(@PathVariable("id") Long id){
        OrganizerResponse body = organizerService.getOrganizerById(id);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrganizerResponse> updateOrganizerById(@PathVariable("id") Long id, @RequestBody UserRequest userModel){
        OrganizerResponse body = organizerService.updateOrganizerById(id, userModel);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrganizerById(@PathVariable("id") Long id){
        Boolean isDeleted = organizerService.deleteOrganizerById(id);
        if(isDeleted){
            return new ResponseEntity<>("Deleted Guest with id: " + id, HttpStatus.OK);
        }
        return null;
    }

    @GetMapping("/{id}/events")
    public ResponseEntity<List<EventResponse>> getAllEventsByOrganizerId(@PathVariable("id") Long id){
        return new ResponseEntity<>(eventService.getAllEventsByOrganizerId(id), HttpStatus.OK);
    }
}
