package com.example.eventApp.model.response;

import com.example.eventApp.entity.Review;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReviewResponse {
    private Long id;
    private EventResponse event;
    private GuestResponse guest;
    private String review;

    public static ReviewResponse from(Review reviewObj){
        ReviewResponse resObj = new ReviewResponse();

        resObj.setId(reviewObj.getReviewId());
        resObj.setGuest(GuestResponse.from(reviewObj.getGuest()));
        resObj.setEvent(EventResponse.from(reviewObj.getEvent()));
        resObj.setReview(reviewObj.getReview());

        return resObj;
    }
}
