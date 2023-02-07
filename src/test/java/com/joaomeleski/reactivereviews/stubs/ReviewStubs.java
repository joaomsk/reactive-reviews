package com.joaomeleski.reactivereviews.stubs;

import com.joaomeleski.reactivereviews.domain.Review;
import com.joaomeleski.reactivereviews.service.dto.request.ReviewRequest;
import reactor.core.publisher.Mono;

public class ReviewStubs {

    public static Mono<Review> getMonoReview() {
        return Mono.just(Review.builder()
                .id("1")
                .title("Title")
                .description("Description")
                .evaluation(5)
                .build());
    }

    public static Review getReview() {
        return Review.builder()
                .id("1")
                .title("Title")
                .description("Description")
                .evaluation(5)
                .build();
    }

    public static Mono<ReviewRequest> getMonoReviewRequest() {
        return Mono.just(ReviewRequest.builder()
                .title("Title")
                .description("Description")
                .evaluation(5)
                .build());
    }

}
