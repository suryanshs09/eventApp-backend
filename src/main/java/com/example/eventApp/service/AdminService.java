package com.example.eventApp.service;

import com.example.eventApp.model.request.UserRequest;
import com.example.eventApp.model.response.AdminResponse;

import java.util.List;

public interface AdminService {
    List<AdminResponse> getAllAdmins();

    AdminResponse getAdminById(Long id);

    AdminResponse updateAdminById(Long id, UserRequest adminModel);

    Boolean deleteAdminById(Long id);

    AdminResponse addAdmin(UserRequest adminModel);
}
