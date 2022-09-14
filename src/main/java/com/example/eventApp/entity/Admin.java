package com.example.eventApp.entity;

import com.example.eventApp.model.request.UserRequest;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table (name = "admins")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "id")
    private Long adminId;

    @Column (
            name = "username",
            nullable = false
    )
    private String username;

    @Column (
            name = "password",
            nullable = false
    )
    private String password;

    public static Admin from(UserRequest adminRequest){
        Admin admin = new Admin();

        if(adminRequest.getId() != null){
            admin.setAdminId(adminRequest.getId());
        }
        admin.setUsername(adminRequest.getUsername());
        admin.setPassword(adminRequest.getPassword());
        return admin;
    }
}
