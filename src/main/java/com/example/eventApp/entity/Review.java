package com.example.eventApp.entity;

import com.example.eventApp.model.request.ReviewRequest;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table (name = "reviews")
public class Review {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (name = "id")
    private Long reviewId;

    @ManyToOne
    @JoinColumn (
            name = "event_id",
            foreignKey = @ForeignKey (name = "event_review_fk")
    )
    private Event event;

    @ManyToOne
    @JoinColumn (
            name = "guest_id",
            foreignKey = @ForeignKey (name = "guest_review_fk")
    )
    private Guest guest;

    @Column (
            name = "review",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String review;

    public static Review from(ReviewRequest reviewModel) {
        Review review = new Review();
        if(reviewModel.getId() != null) {
            review.setReviewId(reviewModel.getId());
        }
        review.setReview(reviewModel.getReview());
        return review;
    }
}
