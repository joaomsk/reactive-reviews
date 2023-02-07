package com.joaomeleski.reactivereviews.service;

import com.joaomeleski.reactivereviews.infrastructure.mapper.ReviewMapper;
import com.joaomeleski.reactivereviews.infrastructure.repository.ReviewRepository;
import com.joaomeleski.reactivereviews.service.dto.request.ReviewRequest;
import com.joaomeleski.reactivereviews.service.dto.response.ReviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    public Mono<ReviewResponse> createReview(Mono<ReviewRequest> request) {
        return request.map(reviewMapper::mapToDomain)
                .flatMap(reviewRepository::save)
                .map(reviewMapper::mapFromDomain);
    }

    public Mono<ReviewResponse> findReviewById(Mono<String> id) {
        return reviewRepository.findById(id).map(reviewMapper::mapFromDomain);
    }

    public Flux<ReviewResponse> findAllReviews() {
        return reviewRepository.findAll().map(reviewMapper::mapFromDomain);
    }
}
