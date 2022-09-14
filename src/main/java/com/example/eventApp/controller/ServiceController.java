package com.example.eventApp.controller;

import com.example.eventApp.entity.Event;
import com.example.eventApp.model.request.ServiceRequest;
import com.example.eventApp.model.response.EventResponse;
import com.example.eventApp.model.response.ServiceResponse;
import com.example.eventApp.service.ServiceServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/services")
public class ServiceController {

    private final ServiceServiceImpl serviceService;

    @Autowired
    public ServiceController(ServiceServiceImpl serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ServiceResponse>> getAllServices(){
        List<ServiceResponse> body = serviceService.getAllServices();
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ServiceResponse> addService(@RequestBody ServiceRequest serviceModel){
        ServiceResponse body = serviceService.addService(serviceModel);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceResponse> getServiceById(@PathVariable("id") Long id){
        ServiceResponse body = serviceService.getServiceById(id);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceResponse> updateServiceById(@PathVariable("id") Long id, @RequestBody ServiceRequest serviceModel){
        ServiceResponse body = serviceService.updateServiceById(id, serviceModel);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteServiceById(@PathVariable("id") Long id){
        Boolean isDeleted = serviceService.deleteServiceById(id);
        if(isDeleted){
            return new ResponseEntity<>("Deleted EService with id: " + id, HttpStatus.OK);
        }
        return null;
    }

    @GetMapping("/{id}/events")
    public ResponseEntity<List<EventResponse>> getEventsByServiceId(@PathVariable("id") Long id){
        List<EventResponse> body = serviceService.getEventsByServiceId(id);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

}
