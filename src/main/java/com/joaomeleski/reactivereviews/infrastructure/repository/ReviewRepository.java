package com.joaomeleski.reactivereviews.infrastructure.repository;

import com.joaomeleski.reactivereviews.domain.Review;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ReviewRepository extends ReactiveMongoRepository<Review, String> {
}
