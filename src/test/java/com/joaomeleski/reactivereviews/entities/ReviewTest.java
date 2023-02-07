package com.joaomeleski.reactivereviews.entities;

import com.joaomeleski.reactivereviews.domain.Review;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReviewTest {

    @Test
    void mustCreateAInstanceOfReviewSuccessfully() {
        Review review = Review.builder()
                .id("1")
                .title("Title")
                .description("Description")
                .evaluation(5)
                .build();

        assert review.getId().equals("1");
        assert review.getTitle().equals("Title");
        assert review.getDescription().equals("Description");
        assert review.getEvaluation().equals(5);
    }

    @Test
    void mustCreateAInstanceOfReviewSuccessfullyWithoutBuilder() {
        Review review = new Review("1", "Title", "Description", 5, new String[]{});

        assert review.getId().equals("1");
        assert review.getTitle().equals("Title");
        assert review.getDescription().equals("Description");
        assert review.getEvaluation().equals(5);
    }
}
