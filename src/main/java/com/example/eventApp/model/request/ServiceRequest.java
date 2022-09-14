package com.example.eventApp.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ServiceRequest {
    private Long id;
    private String name;
    private Integer rating;
    private String contact;
    private String description;
}
