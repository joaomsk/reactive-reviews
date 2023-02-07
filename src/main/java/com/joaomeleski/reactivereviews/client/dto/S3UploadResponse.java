package com.joaomeleski.reactivereviews.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class S3UploadResponse {

    private static final String URI_FORMAT = "https://%s.s3.%s.amazonaws.com/%s";
    public String bucketName;
    public String region;
    public String key;

    public String getFullFileUrl() {
        return String.format(URI_FORMAT, bucketName, region, key);
    }
}
