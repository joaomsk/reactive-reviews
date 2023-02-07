package com.joaomeleski.reactivereviews.mapper;

import com.joaomeleski.reactivereviews.domain.Review;
import com.joaomeleski.reactivereviews.infrastructure.mapper.ReviewMapper;
import com.joaomeleski.reactivereviews.service.dto.request.ReviewRequest;
import com.joaomeleski.reactivereviews.service.dto.response.ReviewResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.Matchers.instanceOf;

@SpringBootTest
public class ReviewMapperTest {

    private final ReviewMapper reviewMapper = new ReviewMapper();

    @Test
    void mustMapFromDomainToResponse() {
        Review review = Review.builder()
                .id("1")
                .title("Title")
                .description("Description")
                .evaluation(5)
                .build();

        ReviewResponse convertedReview = reviewMapper.mapFromDomain(review);

        assert instanceOf(ReviewResponse.class).matches(convertedReview);
        assert convertedReview.getId().equals("1");
        assert convertedReview.getTitle().equals("Title");
        assert convertedReview.getDescription().equals("Description");
        assert convertedReview.getEvaluation().equals(5);
    }

    @Test
    void mustMapFromRequestToDomain() {
        ReviewRequest review = ReviewRequest.builder()
                .title("Title")
                .description("Description")
                .evaluation(5)
                .build();

        Review convertedReview = reviewMapper.mapToDomain(review);

        assert instanceOf(Review.class).matches(convertedReview);
        assert convertedReview.getTitle().equals("Title");
        assert convertedReview.getDescription().equals("Description");
        assert convertedReview.getEvaluation().equals(5);
    }
}
