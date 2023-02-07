package com.joaomeleski.reactivereviews.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public final class ReviewResponse {
    private String id;
    private String title;
    private String description;
    private Integer evaluation;
    private String[] images;
}