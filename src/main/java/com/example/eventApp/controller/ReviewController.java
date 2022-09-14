package com.example.eventApp.controller;

import com.example.eventApp.model.request.ReviewRequest;
import com.example.eventApp.model.response.ReviewResponse;
import com.example.eventApp.service.ReviewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewServiceImpl reviewService;

    @Autowired
    public ReviewController(ReviewServiceImpl reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/add")
    public ResponseEntity<ReviewResponse> addReview(@RequestBody ReviewRequest reviewModel){
        ReviewResponse body = reviewService.addReview(reviewModel);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ReviewResponse>> getAllReviews(){
        List<ReviewResponse> body = reviewService.getAllReviews();
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponse> getReviewById(@PathVariable("id") Long id){
        ReviewResponse body = reviewService.getReviewById(id);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewResponse> updateReviewById(@PathVariable("id") Long id, @RequestBody ReviewRequest reviewModel){
        ReviewResponse body = reviewService.updateReviewById(id, reviewModel);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReviewById(@PathVariable("id") Long id){
        Boolean isDeleted = reviewService.deleteReviewById(id);
        if(isDeleted){
            return new ResponseEntity<>("Deleted Review with id: " + id, HttpStatus.OK);
        }
        return null;
    }

}
