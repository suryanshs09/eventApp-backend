package com.example.eventApp.service;

import com.example.eventApp.entity.Guest;
import com.example.eventApp.model.request.UserRequest;
import com.example.eventApp.model.response.GuestResponse;
import com.example.eventApp.repository.EventRepository;
import com.example.eventApp.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GuestServiceImpl implements GuestService{
    private final GuestRepository guestRepository;
    private final EventRepository eventRepository;

    @Autowired
    public GuestServiceImpl(GuestRepository guestRepository, EventRepository eventRepository) {
        this.guestRepository = guestRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public GuestResponse addGuest(UserRequest userModel){
        Guest guest = Guest.from(userModel);
        return GuestResponse.from(guestRepository.save(guest));
    }

    @Override
    public GuestResponse getGuestById(Long id) {
        Optional<Guest> guest = guestRepository.findById(id);
        if(guest.isPresent()){
            return GuestResponse.from(guest.get());
        }
        return null;
    }

    @Override
    public GuestResponse updateGuestById(Long id, UserRequest userModel) {
        Optional<Guest> guestToUpdate = guestRepository.findById(id);
        if(guestToUpdate.isPresent()){
            Guest guest = guestToUpdate.get();
            guest.setUsername(userModel.getUsername());
            guest.setPassword(userModel.getPassword());
            guest.setDob(userModel.getDob());
            guest.setAge(Period.between(guest.getDob(), LocalDate.now()));
            guest.setEmail(userModel.getEmail());
            guest.setContact(userModel.getContact());
            return GuestResponse.from(guestRepository.save(guest));
        }
        return null;
    }

    @Override
    public Boolean deleteGuestById(Long id) {
        Optional<Guest> user = guestRepository.findById(id);
        if(user.isPresent()){
            guestRepository.delete(user.get());
            return true;
        }
        return false;
    }

    @Override
    public List<GuestResponse> getAllGuests() {
        List<Guest> guests = guestRepository.findAll();
        List<GuestResponse> users = new ArrayList<>();
        for (Guest guest: guests) {
            users.add(GuestResponse.from(guest));
        }
        return users;
    }

    @Override
    public List<GuestResponse> getAllGuestsByEventId(Long id) {
        List<Guest> guests = eventRepository.findById(id).get().getGuests();
        List<GuestResponse> users = new ArrayList<>();
        for (Guest guest: guests) {
            users.add(GuestResponse.from(guest));
        }
        return users;
    }

}
