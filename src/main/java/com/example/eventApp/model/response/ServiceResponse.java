package com.example.eventApp.model.response;

import com.example.eventApp.entity.EService;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ServiceResponse {
    private Long id;
    private String name;
    private Integer rating;
    private String contact;
    private String description;

    public static ServiceResponse from(EService serviceObj){
        ServiceResponse resObj = new ServiceResponse();

        resObj.setId(serviceObj.getServiceId());
        resObj.setName(serviceObj.getName());
        resObj.setRating(serviceObj.getRating());
        resObj.setContact(serviceObj.getContact());
        resObj.setDescription(serviceObj.getDescription());

        return resObj;
    }
}
