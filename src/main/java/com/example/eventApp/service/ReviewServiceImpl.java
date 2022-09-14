package com.example.eventApp.service;

import com.example.eventApp.entity.Review;
import com.example.eventApp.model.request.ReviewRequest;
import com.example.eventApp.model.response.ReviewResponse;
import com.example.eventApp.repository.EventRepository;
import com.example.eventApp.repository.GuestRepository;
import com.example.eventApp.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;
    private final EventRepository eventRepository;
    private final GuestRepository guestRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, EventRepository eventRepository, GuestRepository guestRepository) {
        this.reviewRepository = reviewRepository;
        this.eventRepository = eventRepository;
        this.guestRepository = guestRepository;
    }

    @Override
    public List<ReviewResponse> getAllReviews() {
        List<Review> reviews = reviewRepository.findAll();
        List<ReviewResponse> reviewResponses = new ArrayList<>();
        for(Review review : reviews){
            reviewResponses.add(ReviewResponse.from(review));
        }
        return reviewResponses;
    }

    @Override
    public ReviewResponse getReviewById(Long id) {
        Optional<Review> review = reviewRepository.findById(id);
        if(review.isPresent()){
            return ReviewResponse.from(review.get());
        }
        return null;
    }

    @Override
    public ReviewResponse updateReviewById(Long id, ReviewRequest reviewModel) {
        Optional<Review> reviewToUpdate = reviewRepository.findById(id);
        if(reviewToUpdate.isPresent()){
            Review review = reviewToUpdate.get();
            review.setReview(reviewModel.getReview());
            review.setGuest(guestRepository.findById(reviewModel.getUserId()).get());
            review.setEvent(eventRepository.findById(reviewModel.getEventId()).get());
            return ReviewResponse.from(reviewRepository.save(review));
        }
        return null;
    }

    @Override
    public Boolean deleteReviewById(Long id) {
        Optional<Review> review = reviewRepository.findById(id);
        if(review.isPresent()){
            reviewRepository.delete(review.get());
            return true;
        }
        return false;
    }

    @Override
    public ReviewResponse addReview(ReviewRequest reviewModel) {
        Review review = Review.from(reviewModel);
        review.setEvent(eventRepository.findById(reviewModel.getEventId()).get());
        review.setGuest(guestRepository.findById(reviewModel.getUserId()).get());
        return ReviewResponse.from(reviewRepository.save(review));
    }
}
