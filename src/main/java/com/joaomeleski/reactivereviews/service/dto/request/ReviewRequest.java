package com.joaomeleski.reactivereviews.service.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "A model for the review request body")
public final class ReviewRequest {
    private String title;
    private String description;
    private Integer evaluation;
}
