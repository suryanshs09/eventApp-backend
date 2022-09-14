package com.example.eventApp.service;

import com.example.eventApp.entity.Organizer;
import com.example.eventApp.model.request.UserRequest;
import com.example.eventApp.model.response.OrganizerResponse;
import com.example.eventApp.repository.OrganizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrganizerServiceImpl implements OrganizerService{

    private final OrganizerRepository organizerRepository;

    @Autowired
    public OrganizerServiceImpl(OrganizerRepository organizerRepository) {
        this.organizerRepository = organizerRepository;
    }

    @Override
    public OrganizerResponse addOrganizer(UserRequest userModel){
        Organizer organizer = Organizer.from(userModel);
        return OrganizerResponse.from(organizerRepository.save(organizer));
    }

    @Override
    public OrganizerResponse getOrganizerById(Long id) {
        Optional<Organizer> organizer = organizerRepository.findById(id);
        if(organizer.isPresent()){
            return OrganizerResponse.from(organizer.get());
        }
        return null;
    }

    @Override
    public OrganizerResponse updateOrganizerById(Long id, UserRequest userModel) {
        Optional<Organizer> organizerToUpdate = organizerRepository.findById(id);
        if(organizerToUpdate.isPresent()){
            Organizer organizer = organizerToUpdate.get();
            organizer.setUsername(userModel.getUsername());
            organizer.setPassword(userModel.getPassword());
            organizer.setEmail(userModel.getEmail());
            organizer.setContact(userModel.getContact());
            return OrganizerResponse.from(organizerRepository.save(organizer));
        }
        return null;
    }

    @Override
    public Boolean deleteOrganizerById(Long id) {
        Optional<Organizer> user = organizerRepository.findById(id);
        if(user.isPresent()){
            organizerRepository.delete(user.get());
            return true;
        }
        return false;
    }

    @Override
    public List<OrganizerResponse> getAllOrganizers() {
        List<Organizer> organizers = organizerRepository.findAll();
        List<OrganizerResponse> users = new ArrayList<>();
        for (Organizer organizer: organizers) {
            users.add(OrganizerResponse.from(organizer));
        }
        return users;
    }

}
