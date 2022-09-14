package com.example.eventApp.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReviewRequest {
    private Long id;
    private Long eventId;
    private Long userId;
    private String review;
}
