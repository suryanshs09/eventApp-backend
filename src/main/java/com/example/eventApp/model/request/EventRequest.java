package com.example.eventApp.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class EventRequest {
    private Long id;
    private String name;
    private String description;
    private Integer fees;
    private String location;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long organizerId;
}
