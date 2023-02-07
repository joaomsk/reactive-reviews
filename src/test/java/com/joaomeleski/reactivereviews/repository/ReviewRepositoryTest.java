package com.joaomeleski.reactivereviews.repository;

import com.joaomeleski.reactivereviews.domain.Review;
import com.joaomeleski.reactivereviews.infrastructure.repository.ReviewRepository;
import com.joaomeleski.reactivereviews.stubs.ReviewStubs;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@DataMongoTest
public class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    void mustSaveReviewSuccessfully() {
        Mono<Review> review = ReviewStubs.getMonoReview();

        StepVerifier.create(reviewRepository.saveAll(review).then())
                .expectSubscription()
                .verifyComplete();
    }

    @Test
    void mustDeleteAReviewSuccessfully() {
        Mono<Review> review = ReviewStubs.getMonoReview();

        StepVerifier.create(reviewRepository.saveAll(review).then())
                .expectSubscription()
                .verifyComplete();

        StepVerifier.create(reviewRepository.deleteAll())
                .expectSubscription()
                .verifyComplete();
    }

    @Test
    void mustUpdateAReviewSuccessfully() {
        Mono<Review> review = ReviewStubs.getMonoReview();

        StepVerifier.create(reviewRepository.saveAll(review).then())
                .expectSubscription()
                .verifyComplete();

        Review reviewUpdated = ReviewStubs.getReview();
        reviewUpdated.setTitle("Title Updated");

        StepVerifier.create(reviewRepository.save(reviewUpdated))
                .expectSubscription()
                .expectNextMatches(review1 -> review1.getTitle().equals("Title Updated"))
                .verifyComplete();
    }
}
