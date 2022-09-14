package com.example.eventApp.model.response;

import com.example.eventApp.entity.Admin;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class AdminResponse {
    private Long id;
    private String username;
    private String password;

    public static AdminResponse from(Admin admin){
        AdminResponse resObj = new AdminResponse();

        resObj.setId(admin.getAdminId());
        resObj.setUsername(admin.getUsername());
        resObj.setPassword(admin.getPassword());

        return resObj;
    }
}
