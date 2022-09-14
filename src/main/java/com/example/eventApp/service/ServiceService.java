package com.example.eventApp.service;

import com.example.eventApp.model.request.ServiceRequest;
import com.example.eventApp.model.response.EventResponse;
import com.example.eventApp.model.response.ServiceResponse;

import java.util.List;

public interface ServiceService {
    List<ServiceResponse> getAllServices();

    ServiceResponse addService(ServiceRequest serviceModel);

    ServiceResponse getServiceById(Long id);

    ServiceResponse updateServiceById(Long id, ServiceRequest serviceModel);

    Boolean deleteServiceById(Long id);

    List<EventResponse> getEventsByServiceId(Long id);

}
