package com.example.eventApp.controller;


import com.example.eventApp.model.request.UserRequest;
import com.example.eventApp.model.response.AdminResponse;
import com.example.eventApp.service.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/admins")
public class AdminController {
    private final AdminServiceImpl adminService;

    @Autowired
    public AdminController(AdminServiceImpl adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/add")
    public ResponseEntity<AdminResponse> addAdmin(@RequestBody UserRequest adminModel) {
        AdminResponse body = adminService.addAdmin(adminModel);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AdminResponse>> getAllAdmins() {
        List<AdminResponse> body = adminService.getAllAdmins();
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminResponse> getAdminById(@PathVariable Long id) {
        AdminResponse body = adminService.getAdminById(id);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminResponse> updateAdminById(@PathVariable Long id, @RequestBody UserRequest adminModel) {
        AdminResponse body = adminService.updateAdminById(id, adminModel);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteAdminById(@PathVariable Long id) {
        Boolean body = adminService.deleteAdminById(id);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

}
