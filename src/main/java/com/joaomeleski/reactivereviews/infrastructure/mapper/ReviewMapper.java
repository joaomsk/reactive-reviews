package com.joaomeleski.reactivereviews.infrastructure.mapper;

import com.joaomeleski.reactivereviews.domain.Review;
import com.joaomeleski.reactivereviews.service.dto.request.ReviewRequest;
import com.joaomeleski.reactivereviews.service.dto.response.ReviewResponse;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {
    public Review mapToDomain(ReviewRequest source) {
        return Review.builder()
                .title(source.getTitle())
                .description(source.getDescription())
                .evaluation(source.getEvaluation())
                .build();
    }

    public ReviewResponse mapFromDomain(Review source) {
        return ReviewResponse.builder()
                .id(source.getId())
                .title(source.getTitle())
                .description(source.getDescription())
                .evaluation(source.getEvaluation())
                .images(source.getImages())
                .build();
    }
}
