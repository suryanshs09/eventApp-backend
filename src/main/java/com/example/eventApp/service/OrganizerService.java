package com.example.eventApp.service;

import com.example.eventApp.model.request.UserRequest;
import com.example.eventApp.model.response.OrganizerResponse;

import java.util.List;

public interface OrganizerService {
    OrganizerResponse addOrganizer(UserRequest userModel);

    OrganizerResponse getOrganizerById(Long id);

    OrganizerResponse updateOrganizerById(Long id, UserRequest userModel);

    Boolean deleteOrganizerById(Long id);

    List<OrganizerResponse> getAllOrganizers();

}
