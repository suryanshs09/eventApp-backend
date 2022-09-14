package com.example.eventApp.service;

import com.example.eventApp.model.request.UserRequest;
import com.example.eventApp.model.response.GuestResponse;

import java.util.List;

public interface GuestService {
    GuestResponse addGuest(UserRequest userModel);

    GuestResponse getGuestById(Long id);

    GuestResponse updateGuestById(Long id, UserRequest userModel);

    Boolean deleteGuestById(Long id);

    List<GuestResponse> getAllGuests();

    List<GuestResponse> getAllGuestsByEventId(Long id);
}
