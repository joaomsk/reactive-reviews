package com.joaomeleski.reactivereviews.api;

import com.joaomeleski.reactivereviews.service.ReviewService;
import com.joaomeleski.reactivereviews.service.dto.request.ReviewRequest;
import com.joaomeleski.reactivereviews.service.dto.response.ReviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/{id}")
    public Mono<ReviewResponse> handleGetReviewByIdRequest(@PathVariable String id) {
        return reviewService.findReviewById(Mono.just(id));
    }

    @GetMapping
    public Flux<ReviewResponse> handleGetAllReviewsRequest() {
        return reviewService.findAllReviews();
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<ReviewResponse> handlePostReviewRequest(@RequestPart ReviewRequest reviewRequest) {
        return reviewService.createReview(Mono.just(reviewRequest));
    }
}
