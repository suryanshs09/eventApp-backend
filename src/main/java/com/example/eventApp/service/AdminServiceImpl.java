package com.example.eventApp.service;

import com.example.eventApp.entity.Admin;
import com.example.eventApp.model.request.UserRequest;
import com.example.eventApp.model.response.AdminResponse;
import com.example.eventApp.repository.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    private AdminRepository adminRepository;
    @Override
    public List<AdminResponse> getAllAdmins() {
        List<Admin> admins = adminRepository.findAll();
        List<AdminResponse> adminResponses = new ArrayList<>();
        for (Admin admin : admins) {
            adminResponses.add(AdminResponse.from(admin));
        }
        return adminResponses;
    }

    @Override
    public AdminResponse getAdminById(Long id) {
        Optional<Admin> admin = adminRepository.findById(id);
        if (admin.isPresent()) {
            return AdminResponse.from(admin.get());
        }
        return null;
    }

    @Override
    public AdminResponse updateAdminById(Long id, UserRequest adminModel) {
        Optional<Admin> adminToUpdate = adminRepository.findById(id);
        if (adminToUpdate.isPresent()) {
            Admin admin = adminToUpdate.get();
            admin.setUsername(adminModel.getUsername());
            admin.setPassword(adminModel.getPassword());
            return AdminResponse.from(adminRepository.save(admin));
        }
        return null;
    }

    @Override
    public Boolean deleteAdminById(Long id) {
        Optional<Admin> adminToDelete = adminRepository.findById(id);
        if (adminToDelete.isPresent()) {
            adminRepository.delete(adminToDelete.get());
            return true;
        }
        return false;
    }

    @Override
    public AdminResponse addAdmin(UserRequest adminModel) {
        Admin admin = Admin.from(adminModel);
        return AdminResponse.from(adminRepository.save(admin));
    }
}
