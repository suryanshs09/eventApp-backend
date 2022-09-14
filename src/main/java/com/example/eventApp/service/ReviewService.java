package com.example.eventApp.service;

import com.example.eventApp.model.request.ReviewRequest;
import com.example.eventApp.model.response.ReviewResponse;

import java.util.List;

public interface ReviewService {
    List<ReviewResponse> getAllReviews();

    ReviewResponse getReviewById(Long id);

    ReviewResponse updateReviewById(Long id, ReviewRequest reviewModel);

    Boolean deleteReviewById(Long id);

    ReviewResponse addReview(ReviewRequest reviewModel);
}
