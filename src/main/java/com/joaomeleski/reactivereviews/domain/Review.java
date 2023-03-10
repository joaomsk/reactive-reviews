package com.joaomeleski.reactivereviews.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Review {

    @Id
    private String id;
    private String title;
    private String description;
    private Integer evaluation;
    private String[] images;
}
