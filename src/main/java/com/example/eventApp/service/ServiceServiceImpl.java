package com.example.eventApp.service;

import com.example.eventApp.entity.EService;
import com.example.eventApp.model.request.ServiceRequest;
import com.example.eventApp.model.response.EventResponse;
import com.example.eventApp.model.response.ServiceResponse;
import com.example.eventApp.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceServiceImpl implements ServiceService {

    private ServiceRepository serviceRepository;

    @Autowired
    public ServiceServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public List<ServiceResponse> getAllServices() {
        List<EService> eServices = serviceRepository.findAll();
        List<ServiceResponse> serviceResponses = new ArrayList<>();
        for (EService eService : eServices) {
            serviceResponses.add(ServiceResponse.from(eService));
        }
        return serviceResponses;
    }

    @Override
    public ServiceResponse addService(ServiceRequest serviceModel) {
        EService eService = EService.from(serviceModel);
        return ServiceResponse.from(serviceRepository.save(eService));
    }

    @Override
    public ServiceResponse getServiceById(Long id) {
        Optional<EService> eService = serviceRepository.findById(id);
        if (eService.isPresent()) {
            return ServiceResponse.from(eService.get());
        }
        return null;
    }

    @Override
    public ServiceResponse updateServiceById(Long id, ServiceRequest serviceModel) {
        Optional<EService> eServiceToUpdate = serviceRepository.findById(id);
        if (eServiceToUpdate.isPresent()) {
            EService eService = eServiceToUpdate.get();
            eService.setName(serviceModel.getName());
            eService.setDescription(serviceModel.getDescription());
            eService.setRating(serviceModel.getRating());
            eService.setContact(serviceModel.getContact());
            return ServiceResponse.from(serviceRepository.save(eService));
        }
        return null;
    }

    @Override
    public Boolean deleteServiceById(Long id) {
        Optional<EService> eService = serviceRepository.findById(id);
        if (eService.isPresent()) {
            serviceRepository.delete(eService.get());
            return true;
        }
        return false;
    }

    @Override
    public List<EventResponse> getEventsByServiceId(Long id) {
        List<EventResponse> eventModels = new ArrayList<>();
        serviceRepository.findById(id).get().getEvents().forEach(event -> eventModels.add(EventResponse.from(event)));
        return eventModels;
    }
}


