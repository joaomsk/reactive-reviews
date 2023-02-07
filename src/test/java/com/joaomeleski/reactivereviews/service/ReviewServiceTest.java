package com.joaomeleski.reactivereviews.service;

import com.joaomeleski.reactivereviews.domain.Review;
import com.joaomeleski.reactivereviews.infrastructure.mapper.ReviewMapper;
import com.joaomeleski.reactivereviews.infrastructure.repository.ReviewRepository;
import com.joaomeleski.reactivereviews.service.dto.request.ReviewRequest;
import com.joaomeleski.reactivereviews.service.dto.response.ReviewResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Spy
    private ReviewMapper reviewMapper;

    @InjectMocks
    private ReviewService reviewService;

    @BeforeEach
    public void setUp() {
        reviewService = new ReviewService(reviewRepository, reviewMapper);
    }

    @Test
    public void createReview_givenValidReview_shouldReturnReviewResponse() {
        // Arrange
        ReviewRequest reviewRequest = ReviewRequest.builder()
                .title("Test")
                .description("Test Description")
                .evaluation(5)
                .build();
        Mono<ReviewRequest> request = Mono.just(reviewRequest);

        Review review = Review.builder()
                .title("Test")
                .description("Test Description")
                .evaluation(5)
                .build();
        when(reviewMapper.mapToDomain(reviewRequest)).thenReturn(review);
        when(reviewRepository.save(review)).thenReturn(Mono.just(review));


        ReviewResponse expected = ReviewResponse.builder()
                .id("1")
                .title("Test")
                .description("Test Description")
                .evaluation(5)
                .build();
        when(reviewMapper.mapFromDomain(review)).thenReturn(expected);

        // Act
        Mono<ReviewResponse> actual = reviewService.createReview(request);

        // Assert
        StepVerifier
                .create(actual)
                .expectNext(expected)
                .verifyComplete();
    }

    @Test
    public void findReviewById_givenValidId_shouldReturnReviewResponse() {
        // Arrange
        String id = "1";
        Mono<String> request = Mono.just(id);
        Review review = Review.builder()
                .id("1")
                .title("Test")
                .description("Test Description")
                .evaluation(5)
                .build();
        when(reviewRepository.findById(request)).thenReturn(Mono.just(review));

        ReviewResponse expected = ReviewResponse.builder()
                .id("1")
                .title("Test")
                .description("Test Description")
                .evaluation(5)
                .build();
        when(reviewMapper.mapFromDomain(review)).thenReturn(expected);

        // Act
        Mono<ReviewResponse> actual = reviewService.findReviewById(request);

        // Assert
        StepVerifier
                .create(actual)
                .expectNext(expected)
                .verifyComplete();
    }

    @Test
    public void findAllReviews_shouldReturnReviewResponseFlux() {
        // Arrange
        Review review = Review.builder()
                .id("1")
                .title("Test")
                .description("Test Description")
                .evaluation(5)
                .build();
        Review review2 = Review.builder()
                .id("2")
                .title("Test2")
                .description("Test Description2")
                .evaluation(4)
                .build();
        Flux<Review> reviews = Flux.just(review, review2);
        when(reviewRepository.findAll()).thenReturn(reviews);

        ReviewResponse expected1 = ReviewResponse.builder()
                .id("1")
                .title("Test")
                .description("Test Description")
                .evaluation(5)
                .build();
        ReviewResponse expected2 = ReviewResponse.builder()
                .id("2")
                .title("Test2")
                .description("Test Description2")
                .evaluation(4)
                .build();

        when(reviewMapper.mapFromDomain(review)).thenReturn(expected1);
        when(reviewMapper.mapFromDomain(review2)).thenReturn(expected2);

        // Act
        Flux<ReviewResponse> actual = reviewService.findAllReviews();

        // Assert
        StepVerifier
                .create(actual)
                .expectNext(expected1, expected2)
                .verifyComplete();
    }
}
